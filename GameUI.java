import java.awt.*;
import javax.swing.*;

public class GameUI {
    private JFrame frame;
    private GameLogic logic;
    private CardLayout cardLayout; // ตัวควบคุมการสลับหน้า
    private JPanel mainContainer; // ตู้คอนเทนเนอร์เก็บทุกหน้าจอ

    public GameUI(GameLogic logic) {
        this.logic = logic;
        initWindow();
    }

    private void initWindow() {
        // --- 1. ตั้งค่า Window หลัก (1200x800) ---
        frame = new JFrame("FirstLove - เกมจีบสาว");
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // ระบบ CardLayout สำหรับสลับหน้าจอ
        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);

        // สร้างหน้าจอต่าง ๆ
        JPanel menuPanel = createMenuPanel();
        // CharacterSelect ต้องมีไฟล์ CharacterSelect.java รองรับ
        CharacterSelect charSelectPanel = new CharacterSelect(cardLayout, mainContainer, logic); 
        JPanel gameplayPanel = createGameplayPanel();

        // เพิ่มหน้าจอลงใน CardLayout
        mainContainer.add(menuPanel, "MENU");
        mainContainer.add(charSelectPanel, "CHAR_SELECT");
        mainContainer.add(gameplayPanel, "GAMEPLAY");

        frame.add(mainContainer);
    }

    // --- 2. หน้าจอเมนูหลัก (จัดกึ่งกลางใหม่) ---
    private JPanel createMenuPanel() {
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1200, 800));

        // Background (1200x800)
        ImageIcon bgIcon = new ImageIcon("res/school_bg.jpg");
        Image scaledImg = bgIcon.getImage().getScaledInstance(1200, 800, Image.SCALE_SMOOTH);
        JLabel backgroundLabel = new JLabel(new ImageIcon(scaledImg));
        backgroundLabel.setBounds(0, 0, 1200, 800);

        // ชื่อเกม (Title) ขยายกว้าง 1200 เพื่อจัดกึ่งกลางหน้าจอ
        JLabel titleLabel = new JLabel("First Love", SwingConstants.CENTER) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                FontMetrics fm = g2.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2; // คำนวณกึ่งกลางจาก 1200
                int y = 70;
                
                g2.setColor(Color.WHITE); // วาดเงา
                g2.drawString(getText(), x + 3, y + 3);
                g2.setColor(new Color(255, 105, 180)); // วาดตัวหนังสือสีชมพู
                g2.drawString(getText(), x, y);
            }
        };
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 80));
        titleLabel.setBounds(0, 50, 1200, 150); // ปรับกว้างเป็น 1200

        // ส่วนของปุ่มกด (ใช้ GridBagLayout จัดกึ่งกลางอัตโนมัติ)
        JPanel buttonsPanel = new JPanel(new GridBagLayout());
        buttonsPanel.setOpaque(false);
        buttonsPanel.setBounds(0, 0, 1200, 800); // ปรับกว้างเป็น 1200

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER; 
        
        // ปุ่ม START
        JButton startBtn = new JButton("START GAME");
        styleButton(startBtn);
        startBtn.addActionListener(e -> {
            cardLayout.show(mainContainer, "CHAR_SELECT"); 
            logic.addScore(0);
        });
        gbc.gridy = 0;
        gbc.insets = new Insets(200, 0, 10, 0); // เว้นระยะจากด้านบนหลบ Title
        buttonsPanel.add(startBtn, gbc);

        // ปุ่ม SETTINGS
        JButton settingsBtn = new JButton("SETTINGS");
        styleButton(settingsBtn);
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 0, 10, 0); // ระยะห่างปกติ
        buttonsPanel.add(settingsBtn, gbc);

        // ปุ่ม LOAD
        JButton loadBtn = new JButton("LOAD GAME");
        styleButton(loadBtn);
        gbc.gridy = 2;
        buttonsPanel.add(loadBtn, gbc);

        // ปุ่ม EXIT
        JButton exitBtn = new JButton("EXIT");
        styleButton(exitBtn);
        exitBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(frame, "ออกจากเกม?", "Exit", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) System.exit(0);
        });
        gbc.gridy = 3;
        buttonsPanel.add(exitBtn, gbc);

        // ลำดับการวาง Layer
        layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(titleLabel, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(buttonsPanel, JLayeredPane.PALETTE_LAYER);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(layeredPane);
        return panel;
    }

    // --- 3. หน้าจอ Gameplay ---
    private JPanel createGameplayPanel() {
        JPanel panel = new JPanel(null);
        JLabel label = new JLabel("ฉากที่ 1: หน้าหอพัก (กุญแจตก)", SwingConstants.CENTER);
        label.setBounds(0, 200, 1200, 50); // ปรับกว้างเป็น 1200
        label.setFont(new Font("Tahoma", Font.BOLD, 24));
        panel.add(label);
        return panel;
    }

    private void styleButton(JButton btn) {
        btn.setPreferredSize(new Dimension(220, 55)); // ปรับขนาดปุ่มให้สมดุลขึ้น
        btn.setFont(new Font("Tahoma", Font.BOLD, 18));
        btn.setBackground(Color.WHITE);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createLineBorder(new Color(255, 105, 180), 2));
    }

    public void show() {
        frame.setVisible(true);
    }
}