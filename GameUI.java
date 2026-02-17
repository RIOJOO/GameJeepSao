import java.awt.*;
import javax.swing.*;

public class GameUI {
    private JFrame frame;
    private GameLogic logic;
    private CardLayout cardLayout; // ตัวควบคุมการสลับหน้า
    private JPanel mainContainer; // ตู้คอนเทนเนอร์เก็บทุกหน้าจอ

    public GameUI(GameLogic logic) {
        this.logic = logic; // เก็บค่า logic ไว้ใช้งานในคลาส
        initWindow();
    }

    private void initWindow() {
        // --- 1. ตั้งค่า Window หลัก ---
        frame = new JFrame("FirstLove - เกมจีบหนุ่มในโรงเรียน");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // ใช้ CardLayout เพื่อจัดการการสลับหน้าจอ Menu และ Gameplay
        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);

        // สร้างหน้าจอต่างๆ
        JPanel menuPanel = createMenuPanel();
        JPanel gameplayPanel = createGameplayPanel();

        // เพิ่มหน้าจอลงใน CardLayout โดยตั้งชื่อกำกับไว้
        mainContainer.add(menuPanel, "MENU");
        mainContainer.add(gameplayPanel, "GAMEPLAY");

        frame.add(mainContainer);
    }

    // --- 2. หน้าจอเมนู (Menu Panel) ---
    private JPanel createMenuPanel() {
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(800, 600));

        // ส่วน Background (ใช้ JLabel ใส่รูปภาพ)
        ImageIcon bgIcon = new ImageIcon("res/school_bg.jpg");
        Image img = bgIcon.getImage();
        Image scaledImg = img.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
        JLabel backgroundLabel = new JLabel(new ImageIcon(scaledImg));
        backgroundLabel.setBounds(0, 0, 800, 600);

        // ส่วนของชื่อเกม (Game Title) พร้อม Shadow
        JLabel titleLabel = new JLabel("First Love", SwingConstants.CENTER) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                FontMetrics fm = g2.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2; // คำนวณให้ชื่อเกมอยู่ตรงกลางหน้าจอ
                int y = 70;
                
                g2.setColor(Color.WHITE); // วาดเงาสีขาว
                g2.drawString(getText(), x + 3, y + 3); 
                g2.setColor(new Color(255, 105, 180)); // วาดตัวหนังสือจริงสีชมพู
                g2.drawString(getText(), x, y);
            }
        };
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 80));
        titleLabel.setBounds(0, 50, 800, 150);

        // ส่วนของปุ่มกด (Menu Buttons)
        JPanel buttonsPanel = new JPanel(new GridBagLayout());
        buttonsPanel.setOpaque(false);
        buttonsPanel.setBounds(0, 0, 800, 600);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        
        // ปุ่ม START GAME
        JButton startBtn = new JButton("START GAME");
        styleButton(startBtn);
        startBtn.addActionListener(e -> {
            cardLayout.show(mainContainer, "GAMEPLAY"); // สลับไปหน้าเล่นเกม
            logic.addScore(0);
        });
        gbc.insets = new Insets(220, 0, 10, 0); // ดันปุ่มแรกลงมาหลบชื่อเกม
        buttonsPanel.add(startBtn, gbc);

        // ปุ่ม SETTINGS
        JButton settingsBtn = new JButton("SETTINGS");
        styleButton(settingsBtn);
        settingsBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "ระบบตั้งค่ากำลังพัฒนา...");
        });
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 0, 10, 0);
        buttonsPanel.add(settingsBtn, gbc);

        // ปุ่ม EXIT
        JButton exitBtn = new JButton("EXIT");
        styleButton(exitBtn);
        exitBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(frame, "ออกจากเกม?", "Exit", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) System.exit(0);
        });
        gbc.gridy = 2;
        buttonsPanel.add(exitBtn, gbc);

        // รวมทุกลำดับ Layer เข้าด้วยกัน
        layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(titleLabel, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(buttonsPanel, JLayeredPane.PALETTE_LAYER);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(layeredPane);
        return panel;
    }

    // --- 3. หน้าจอ Gameplay (ฉากที่ 1: กุญแจตก) ---
    private JPanel createGameplayPanel() {
        JPanel panel = new JPanel(null);
        
        // ตัวอย่างข้อความในฉากแรก
        JLabel label = new JLabel("ฉากที่ 1: หน้าหอพัก (กุญแจตก)", SwingConstants.CENTER);
        label.setBounds(0, 200, 800, 50);
        label.setFont(new Font("Tahoma", Font.BOLD, 24));
        
        panel.add(label);
        return panel;
    }

    // ส่วนตกแต่งปุ่มให้ดูสวยงาม
    private void styleButton(JButton btn) {
        btn.setPreferredSize(new Dimension(200, 50));
        btn.setFont(new Font("Tahoma", Font.BOLD, 18));
        btn.setBackground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createLineBorder(new Color(255, 105, 180), 2));
    }

    public void show() {
        frame.setVisible(true);
    }
}