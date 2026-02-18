import java.awt.*;
import java.util.List;
import javax.swing.*;

public class GameUI {
    private JFrame frame;
    private GameLogic logic;
    private CardLayout cardLayout;
    private JPanel mainContainer;

    // ตัวแปรสำหรับระบบเนื้อเรื่อง
    private List<Dialogue> currentStory;
    private int currentStep = 0;
    private JLabel dialogLabel, speakerLabel, characterSprite;

    public GameUI(GameLogic logic) {
        this.logic = logic;
        initWindow();
    }

    private void initWindow() {
        frame = new JFrame("FirstLove - เกมจีบสาว");
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);

        // เพิ่มทุกหน้าจอลงใน CardLayout
        mainContainer.add(createMenuPanel(), "MENU");
        mainContainer.add(new CharacterSelect(cardLayout, mainContainer, logic), "CHAR_SELECT");
        mainContainer.add(createGameplayPanel(), "GAMEPLAY");

        frame.add(mainContainer);
    }

    // --- หน้าจอเมนูหลัก (คืนชีพปุ่มทั้งหมด 4 ปุ่ม) ---
    private JPanel createMenuPanel() {
        JPanel mainPanel = new JPanel(null); // ใช้ null layout เพื่อความแม่นยำ

        // 1. ชื่อเกม (Title)
        JLabel titleLabel = new JLabel("<html><div style='text-align: center; color: #FF69B4; " +
                "text-shadow: 3px 3px 0px #FFFFFF;'>First Love</div></html>", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 100));
        titleLabel.setBounds(0, 80, 1200, 150);

        // 2. ปุ่มกดทั้งหมด (คืนค่าเดิมของคุณ)
        int btnX = 490; // กึ่งกลางจอพอดี
        
        JButton startBtn = new JButton("START GAME");
        styleButton(startBtn);
        startBtn.setBounds(btnX, 300, 220, 60);
        startBtn.addActionListener(e -> cardLayout.show(mainContainer, "CHAR_SELECT"));

        JButton settingsBtn = new JButton("SETTINGS");
        styleButton(settingsBtn);
        settingsBtn.setBounds(btnX, 380, 220, 60);

        JButton loadBtn = new JButton("LOAD GAME");
        styleButton(loadBtn);
        loadBtn.setBounds(btnX, 460, 220, 60);

        JButton exitBtn = new JButton("EXIT");
        styleButton(exitBtn);
        exitBtn.setBounds(btnX, 540, 220, 60);
        exitBtn.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(frame, "ออกจากเกม?", "Exit", 0) == 0) System.exit(0);
        });

        // 3. Background
        ImageIcon bgIcon = new ImageIcon("res/school_bg.jpg");
        JLabel bgLabel = new JLabel(new ImageIcon(bgIcon.getImage().getScaledInstance(1200, 800, Image.SCALE_SMOOTH)));
        bgLabel.setBounds(0, 0, 1200, 800);

        // ใส่ตามลำดับ Layer (ปุ่มต้องอยู่ก่อน Background)
        mainPanel.add(titleLabel);
        mainPanel.add(startBtn);
        mainPanel.add(settingsBtn);
        mainPanel.add(loadBtn);
        mainPanel.add(exitBtn);
        mainPanel.add(bgLabel);

        return mainPanel;
    }

    // --- หน้าจอ Gameplay (แก้ไขปัญหาหน้าจอดำ) ---
    private JPanel createGameplayPanel() {
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.BLACK);
        
        characterSprite = new JLabel();
        characterSprite.setBounds(300, 50, 600, 550);
        characterSprite.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(characterSprite);

        dialogLabel = new JLabel("", SwingConstants.CENTER);
        dialogLabel.setBounds(50, 600, 1100, 130);
        dialogLabel.setOpaque(true);
        dialogLabel.setBackground(new Color(255, 255, 255, 220));
        dialogLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
        dialogLabel.setBorder(BorderFactory.createLineBorder(new Color(255, 105, 180), 3));
        panel.add(dialogLabel);

        speakerLabel = new JLabel("");
        speakerLabel.setBounds(50, 560, 200, 40);
        speakerLabel.setOpaque(true);
        speakerLabel.setBackground(new Color(255, 105, 180));
        speakerLabel.setForeground(Color.WHITE);
        speakerLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        speakerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(speakerLabel);

        // คลิกเพื่ออ่านต่อ
        java.awt.event.MouseAdapter clickHandler = new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) { advanceDialogue(); }
        };
        panel.addMouseListener(clickHandler);
        dialogLabel.addMouseListener(clickHandler);

        panel.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentShown(java.awt.event.ComponentEvent e) { startNewStory(); }
        });

        return panel;
    }

    private void startNewStory() {
        String selected = logic.getSelectedCharacter();
        currentStep = 0;
        if ("มีน".equals(selected)) currentStory = MeanStory.getStory();
        else if ("พลอย".equals(selected)) currentStory = PloyStory.getStory();
        else if ("พี่ลิลลี่".equals(selected)) currentStory = LilliStory.getStory();
        
        advanceDialogue(); // โหลดประโยคแรกทันทีที่หน้าจอโชว์
    }

    private void advanceDialogue() {
        if (currentStory != null && currentStep < currentStory.size()) {
            Dialogue d = currentStory.get(currentStep);
            speakerLabel.setText(d.speaker);
            dialogLabel.setText("<html><div style='padding:10px;'>" + d.text + "</div></html>");
            
            if (d.imagePath != null) {
                ImageIcon icon = new ImageIcon(d.imagePath);
                Image img = icon.getImage().getScaledInstance(600, 550, Image.SCALE_SMOOTH);
                characterSprite.setIcon(new ImageIcon(img));
            } else {
                characterSprite.setIcon(null);
            }
            currentStep++;
            
            // บังคับวาดหน้าจอใหม่
            mainContainer.repaint();
            mainContainer.revalidate();
        } else {
            cardLayout.show(mainContainer, "MENU");
        }
    }

    private void styleButton(JButton btn) {
        btn.setFont(new Font("Tahoma", Font.BOLD, 18));
        btn.setBackground(Color.WHITE);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createLineBorder(new Color(255, 105, 180), 2));
    }

    public void show() { frame.setVisible(true); }
}