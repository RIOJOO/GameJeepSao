import java.awt.*;
import javax.swing.*;

public class GameUI {
    private JFrame frame;
    private GameLogic logic;

    public GameUI(GameLogic logic) {
        this.logic = logic; // รับ logic เข้ามาใช้งานในคลาส
        initWindow();
    }

    private void initWindow() {
        // --- ตั้งค่า Window หลัก ---
        frame = new JFrame("FirstLove - เกมจีบหนุ่ม");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JLayeredPane layeredPane = new JLayeredPane();

        // --- 1. Background Image (ชั้น DEFAULT_LAYER) ---
        ImageIcon bgIcon = new ImageIcon("res/school_bg.jpg");
        Image img = bgIcon.getImage();
        // ปรับขนาดรูปให้พอดีกับหน้าจอ 800x600 เสมอ
        Image scaledImg = img.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
        JLabel backgroundLabel = new JLabel(new ImageIcon(scaledImg));
        backgroundLabel.setBounds(0, 0, 800, 600);

        // --- 2. Game Title (Centered with Shadow ในชั้น PALETTE_LAYER) ---
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

        // --- 3. Menu Panel (ปุ่มกดในชั้น PALETTE_LAYER) ---
        JPanel menuPanel = new JPanel(new GridBagLayout());
        menuPanel.setOpaque(false); // ทำให้โปร่งแสงเพื่อมองเห็นพื้นหลัง
        menuPanel.setBounds(0, 0, 800, 600);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new java.awt.Insets(220, 0, 10, 0); // ดันปุ่มลงมาเพื่อหลบชื่อเกม

        JButton startBtn = new JButton("START GAME");
        JButton settingsBtn = new JButton("SETTINGS");
        JButton exitBtn = new JButton("EXIT");

        styleButton(startBtn);
        styleButton(settingsBtn);
        styleButton(exitBtn);

        // การจัดการ Action ปุ่มต่างๆ
        startBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "กำลังพาคุณไปพบกับ 'รุ่นพี่คิน'...");
            logic.addScore(0);
        });

        exitBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(frame, "ออกจากเกม?", "Exit", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) System.exit(0);
        });

        // เพิ่มปุ่มลงใน Layout
        menuPanel.add(startBtn, gbc);
        gbc.gridy = 1;
        gbc.insets = new java.awt.Insets(10, 0, 10, 0);
        menuPanel.add(settingsBtn, gbc);
        gbc.gridy = 2;
        menuPanel.add(exitBtn, gbc);

        // รวมทุกลำดับ Layer เข้าด้วยกัน
        layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(titleLabel, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(menuPanel, JLayeredPane.PALETTE_LAYER);

        frame.add(layeredPane);
    }

    // ส่วนตกแต่งปุ่มให้ดูสวยงามและไม่ซ้ำใคร
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