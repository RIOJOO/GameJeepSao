import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;

public class GameUI {
    private JFrame frame;
    private GameLogic logic;
    private CardLayout cardLayout;
    private JPanel mainContainer;

    private List<Dialogue> currentStory;
    private int currentStep = 0;
    private JLabel dialogLabel, speakerLabel, characterSprite, bgLabel;
    private JPanel choicePanel;

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

        mainContainer.add(createMenuPanel(), "MENU");
        mainContainer.add(new CharacterSelect(cardLayout, mainContainer, logic), "CHAR_SELECT");
        mainContainer.add(createGameplayPanel(), "GAMEPLAY");

        frame.add(mainContainer);
    }

    // --- แก้ไข: หน้าจอเมนูหลัก (จัด Layer ใหม่) ---
    private JPanel createMenuPanel() {
        JPanel mainPanel = new JPanel(null);

        // Title
        JLabel titleLabel = new JLabel("<html><div style='text-align: center; color: #FF69B4; " +
                "text-shadow: 3px 3px 0px #FFFFFF;'>First Love</div></html>", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 100));
        titleLabel.setBounds(0, 80, 1200, 150);

        int btnX = 490;
        // ปุ่ม START
        JButton startBtn = new JButton("START GAME");
        styleButton(startBtn);
        startBtn.setBounds(btnX, 300, 220, 60);
        startBtn.addActionListener(e -> cardLayout.show(mainContainer, "CHAR_SELECT"));

        // ปุ่ม SETTINGS
        JButton settingsBtn = new JButton("SETTINGS");
        styleButton(settingsBtn);
        settingsBtn.setBounds(btnX, 380, 220, 60);

        // ปุ่ม LOAD
        JButton loadBtn = new JButton("LOAD GAME");
        styleButton(loadBtn);
        loadBtn.setBounds(btnX, 460, 220, 60);

        // ปุ่ม EXIT
        JButton exitBtn = new JButton("EXIT");
        styleButton(exitBtn);
        exitBtn.setBounds(btnX, 540, 220, 60);
        exitBtn.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(frame, "ออกจากเกม?", "Exit", 0) == 0) System.exit(0);
        });

        // พื้นหลัง (ต้องโหลดรูปให้สำเร็จก่อน add)
        ImageIcon bgIcon = new ImageIcon("res/school_bg.jpg");
        JLabel menuBg = new JLabel(new ImageIcon(bgIcon.getImage().getScaledInstance(1200, 800, Image.SCALE_SMOOTH)));
        menuBg.setBounds(0, 0, 1200, 800);

        // ลำดับการ Add: สำคัญมาก!
        mainPanel.add(titleLabel);
        mainPanel.add(startBtn);
        mainPanel.add(settingsBtn);
        mainPanel.add(loadBtn);
        mainPanel.add(exitBtn);
        mainPanel.add(menuBg); // Add พื้นหลังเป็นลำดับสุดท้าย

        // บังคับให้พื้นหลังไปอยู่เลเยอร์ล่างสุด (Deepest Layer)
        mainPanel.setComponentZOrder(menuBg, mainPanel.getComponentCount() - 1);

        return mainPanel;
    }

    private JPanel createGameplayPanel() {
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.BLACK);

        choicePanel = new JPanel(new GridLayout(0, 1, 10, 10));
        choicePanel.setBounds(400, 200, 400, 200);
        choicePanel.setOpaque(false);
        choicePanel.setVisible(false);
        panel.add(choicePanel);

        speakerLabel = new JLabel("");
        speakerLabel.setBounds(50, 560, 200, 40);
        speakerLabel.setOpaque(true);
        speakerLabel.setBackground(new Color(255, 105, 180));
        speakerLabel.setForeground(Color.WHITE);
        speakerLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        speakerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(speakerLabel);

        dialogLabel = new JLabel("", SwingConstants.CENTER);
        dialogLabel.setBounds(50, 600, 1100, 130);
        dialogLabel.setOpaque(true);
        dialogLabel.setBackground(new Color(255, 255, 255, 220));
        dialogLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
        dialogLabel.setBorder(BorderFactory.createLineBorder(new Color(255, 105, 180), 3));
        panel.add(dialogLabel);

        characterSprite = new JLabel();
        characterSprite.setBounds(0, 0, 1200, 800); 
        characterSprite.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(characterSprite);

        bgLabel = new JLabel();
        bgLabel.setBounds(0, 0, 1200, 800);
        panel.add(bgLabel);

        // ปรับ Z-Order ใน Gameplay Panel
        panel.setComponentZOrder(choicePanel, 0); // ตัวเลือกอยู่หน้าสุด
        panel.setComponentZOrder(speakerLabel, 1);
        panel.setComponentZOrder(dialogLabel, 2);
        panel.setComponentZOrder(characterSprite, 3);
        panel.setComponentZOrder(bgLabel, 4); // พื้นหลังอยู่หลังสุด

        MouseAdapter clickHandler = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!choicePanel.isVisible()) advanceDialogue();
            }
        };
        panel.addMouseListener(clickHandler);
        dialogLabel.addMouseListener(clickHandler);

        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) { startNewStory(); }
        });

        return panel;
    }

    private void startNewStory() {
        String selected = logic.getSelectedCharacter();
        currentStep = 0;
        if ("มีน".equals(selected)) currentStory = MeanStory.getStory();
        advanceDialogue();
    }

    private void advanceDialogue() {
        if (currentStory != null && currentStep < currentStory.size()) {
            Dialogue d = currentStory.get(currentStep);
            speakerLabel.setText(d.speaker);
            dialogLabel.setText("<html><div style='padding:10px;'>" + d.text + "</div></html>");

            // การจัดการรูปภาพแบบ Layer
            if (d.imagePath != null && !d.imagePath.isEmpty()) {
                if (d.imagePath.contains("|")) {
                    String[] paths = d.imagePath.split("\\|");
                    updateImageLayer(bgLabel, paths[0], 1200, 800);
                    updateImageLayer(characterSprite, paths[1], 1200, 800);
                } else {
                    if ("บรรยาย".equals(d.speaker)) {
                        updateImageLayer(bgLabel, d.imagePath, 1200, 800);
                        characterSprite.setIcon(null);
                    } else {
                        updateImageLayer(characterSprite, d.imagePath, 1200, 800);
                    }
                }
            }

            // เช็ก Choice
            if (d.choices != null && d.choices.length > 0) {
                showChoices(d.choices, d.nextSteps);
            } else {
                currentStep++;
                choicePanel.setVisible(false);
            }
            mainContainer.repaint();
            mainContainer.revalidate();
        } else {
            cardLayout.show(mainContainer, "MENU");
        }
    }

    private void updateImageLayer(JLabel label, String path, int w, int h) {
        try {
            ImageIcon icon = new ImageIcon(path);
            Image img = icon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            System.err.println("ไม่สามารถโหลดรูปได้: " + path);
        }
    }

    private void showChoices(String[] choices, int[] nextSteps) {
        choicePanel.removeAll();
        choicePanel.setVisible(true);
        
        for (int i = 0; i < choices.length; i++) {
            JButton btn = new JButton(choices[i]);
            styleButton(btn);
            
            final int target = (i < nextSteps.length) ? nextSteps[i] : currentStep + 1;

            btn.addActionListener(e -> {
                currentStep = target;
                choicePanel.setVisible(false);
                advanceDialogue();
            });
            choicePanel.add(btn);
        }
        choicePanel.revalidate();
        choicePanel.repaint();
    }

    private void styleButton(JButton btn) {
        btn.setFont(new Font("Tahoma", Font.BOLD, 18));
        btn.setBackground(Color.WHITE);
        btn.setBorder(BorderFactory.createLineBorder(new Color(255, 105, 180), 2));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public void show() { frame.setVisible(true); }
}
