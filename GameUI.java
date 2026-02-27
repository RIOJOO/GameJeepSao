import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.List;
import javax.swing.*;

public class GameUI {
    private JFrame frame;
    private GameLogic logic;
    private CardLayout cardLayout;
    private JPanel mainContainer;

    // ตัวแปรระบบเนื้อเรื่อง
    private List<Dialogue> currentStory;
    private int currentStep = 0;
    private JLabel dialogLabel, speakerLabel, characterSprite, bgLabel;
    private JPanel choicePanel;

    // Status Bar
    private JLabel statusLabel;

    public GameUI(GameLogic logic) {
        System.setProperty("sun.java2d.uiScale", "1.0");
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

    // --- หน้าจอเมนูหลัก ---
    private JPanel createMenuPanel() {
        JPanel mainPanel = new JPanel(null);

        JLabel titleLabel = new JLabel("<html><div style='text-align: center; color: #FF69B4; " +
                "text-shadow: 3px 3px 0px #FFFFFF;'>First Love</div></html>", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 100));
        titleLabel.setBounds(0, 80, 1200, 150);

        int btnX = 490;

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

        ImageIcon bgIcon = new ImageIcon("res/school_bg.jpg");
        JLabel menuBg = new JLabel(new ImageIcon(bgIcon.getImage().getScaledInstance(1200, 800, Image.SCALE_SMOOTH)));
        menuBg.setBounds(0, 0, 1200, 800);

        mainPanel.add(titleLabel);
        mainPanel.add(startBtn);
        mainPanel.add(settingsBtn);
        mainPanel.add(loadBtn);
        mainPanel.add(exitBtn);
        mainPanel.add(menuBg);

        mainPanel.setComponentZOrder(menuBg, mainPanel.getComponentCount() - 1);

        return mainPanel;
    }

    // --- หน้าจอเล่นเกม (Gameplay) ---
    private JPanel createGameplayPanel() {
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.BLACK);

        // 1. Status Bar
        statusLabel = new JLabel("");
        statusLabel.setBounds(20, 15, 1160, 45);
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        statusLabel.setOpaque(true);
        statusLabel.setBackground(new Color(0, 0, 0, 120));
        statusLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        updateStatus();

        // 2. Choice Panel
        choicePanel = new JPanel(new GridLayout(0, 1, 10, 10));
        choicePanel.setBounds(300, 150, 600, 350);
        choicePanel.setOpaque(false);
        choicePanel.setVisible(false);

        // 3. ชื่อผู้พูด
        speakerLabel = new JLabel("");
        speakerLabel.setBounds(50, 560, 200, 40);
        speakerLabel.setOpaque(true);
        speakerLabel.setBackground(new Color(255, 105, 180));
        speakerLabel.setForeground(Color.WHITE);
        speakerLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        speakerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // 4. กล่องคำพูด
        dialogLabel = new JLabel("", SwingConstants.CENTER);
        dialogLabel.setBounds(50, 600, 1100, 130);
        dialogLabel.setOpaque(true);
        dialogLabel.setBackground(new Color(255, 255, 255, 220));
        dialogLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
        dialogLabel.setBorder(BorderFactory.createLineBorder(new Color(255, 105, 180), 3));

        // 5. Sprite & Background
        characterSprite = new JLabel();
        characterSprite.setBounds(0, 0, 1200, 800);
        characterSprite.setHorizontalAlignment(SwingConstants.CENTER);

        bgLabel = new JLabel();
        bgLabel.setBounds(0, 0, 1200, 800);

        // จัดเลเยอร์ (หน้าสุดไปหลังสุด)
        panel.add(statusLabel);
        panel.add(choicePanel);
        panel.add(speakerLabel);
        panel.add(dialogLabel);
        panel.add(characterSprite);
        panel.add(bgLabel);

        panel.setComponentZOrder(statusLabel, 0);
        panel.setComponentZOrder(choicePanel, 1);
        panel.setComponentZOrder(speakerLabel, 2);
        panel.setComponentZOrder(dialogLabel, 3);
        panel.setComponentZOrder(characterSprite, 4);
        panel.setComponentZOrder(bgLabel, 5);

        // คลิกเพื่อไปต่อ
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
            public void componentShown(ComponentEvent e) {
                startNewStory();
                updateStatus();
            }
        });

        return panel;
    }

    // ฟังก์ชันอัปเดตสถานะจาก Logic
    private void updateStatus() {
        if (logic != null && statusLabel != null) {
            statusLabel.setText(logic.getStatusText());
        }
    }

    private void updateImageLayer(JLabel label, String path, int w, int h) {
        try {
            ImageIcon icon = new ImageIcon(path);
            Image img = icon.getImage();
            BufferedImage bimg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = bimg.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.drawImage(img, 0, 0, w, h, null);
            g2.dispose();
            label.setIcon(new ImageIcon(bimg));
        } catch (Exception e) {
            System.err.println("Load Error: " + path);
        }
    }

    private void startNewStory() {
        String selected = logic.getSelectedCharacter();
        currentStep = 0;
        if ("มีน".equals(selected)) {
            currentStory = MeanStory.getStory();
        } else if ("ลิลลี่".equals(selected)) {
            currentStory = LilliStory.getStory();
        } else if ("พลอย".equals(selected)) {
            currentStory = PloyStory.getStory();
        }
        advanceDialogue();
    }

    private void advanceDialogue() {
        if (currentStory == null || currentStory.isEmpty()) {
            cardLayout.show(mainContainer, "MENU");
            return;
        }

        if (currentStep >= currentStory.size()) {
            cardLayout.show(mainContainer, "MENU");
            return;
        }

        Dialogue d = currentStory.get(currentStep);

        updateStatus();

        speakerLabel.setText(d.speaker != null ? d.speaker : "");
        dialogLabel.setText("<html><div style='padding:15px;'>" +
                (d.text != null ? d.text : "") +
                "</div></html>");

        // จัดการรูปภาพ
        if (d.imagePath != null && !d.imagePath.isEmpty()) {
            if (d.imagePath.contains("|")) {
                String[] paths = d.imagePath.split("\\|");
                if (paths.length > 0)
                    updateImageLayer(bgLabel, paths[0], 1200, 800);
                if (paths.length > 1)
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

        // จัดการ Choice
        if (d.choices != null && d.choices.length > 0) {
            if (d.nextSteps == null)
                d.nextSteps = new int[d.choices.length];
            showChoices(d.choices, d.nextSteps, d.affectionGains);
        } else {
            currentStep++;
            choicePanel.setVisible(false);
        }
    }

    private void showChoices(String[] choices, int[] nextSteps, int[] affectionGains) {
        choicePanel.removeAll();
        choicePanel.setVisible(true);

        for (int i = 0; i < choices.length; i++) {
            JButton btn = new JButton(choices[i]);
            styleButton(btn);

            final int target = (nextSteps != null && i < nextSteps.length)
                    ? nextSteps[i] : currentStep + 1;

            final int affGain = (affectionGains != null && i < affectionGains.length)
                    ? affectionGains[i] : 0;

            btn.addActionListener(e -> {
                if (affGain != 0) logic.addAffection(affGain);
                updateStatus();
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
        btn.setFont(new Font("Tahoma", Font.BOLD, 22));
        btn.setBackground(Color.WHITE);
        btn.setForeground(new Color(255, 105, 180));
        btn.setBorder(BorderFactory.createLineBorder(new Color(255, 105, 180), 2));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public void show() { frame.setVisible(true); }
}
