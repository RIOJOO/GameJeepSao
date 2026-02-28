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

    // HUD labels
    private JLabel moneyLabel, affectionLabel, energyLabel;
    private JPanel hudLeft, hudRight;
    private boolean hudVisible = true;

    public GameUI(GameLogic logic) {
        System.setProperty("sun.java2d.uiScale", "1.0");
        this.logic = logic;
        initWindow();
    }

    public void initWindow() {
        frame = new JFrame("FirstLove - เกมจีบสาว");
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);

        mainContainer.add(createMenuPanel(),   "MENU");
        mainContainer.add(new CharacterSelect(cardLayout, mainContainer, logic), "CHAR_SELECT");
        mainContainer.add(createGameplayPanel(), "GAMEPLAY");
        mainContainer.add(new WorkGame_ui(cardLayout, mainContainer, logic), "WORK");
        mainContainer.add(new Shop_ui(cardLayout, mainContainer, logic),     "SHOP");

        frame.add(mainContainer);
    }

    // หน้าเมนูหลัก
    public JPanel createMenuPanel() {
        JPanel mainPanel = new JPanel(null);

        JLabel titleLabel = new JLabel(
            "<html><div style='text-align: center; color: #FF69B4; text-shadow: 3px 3px 0px #FFFFFF;'>First Love</div></html>",
            SwingConstants.CENTER);
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
            if (JOptionPane.showConfirmDialog(frame, "ออกจากเกม?", "Exit", JOptionPane.YES_NO_OPTION) == 0)
                System.exit(0);
        });

        JLabel menuBg = new JLabel();
        menuBg.setBounds(0, 0, 1200, 800);
        updateImageLayer(menuBg, "res/school_bg.jpg", 1200, 800);

        mainPanel.add(titleLabel);
        mainPanel.add(startBtn);
        mainPanel.add(settingsBtn);
        mainPanel.add(loadBtn);
        mainPanel.add(exitBtn);
        mainPanel.add(menuBg);
        mainPanel.setComponentZOrder(menuBg, mainPanel.getComponentCount() - 1);
        return mainPanel;
    }

    // หน้าเล่นเกม
    public JPanel createGameplayPanel() {
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.BLACK);

        // ── HUD กรอบซ้าย: เงิน | ความชอบ | พลังงาน ──
        hudLeft = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 7));
        hudLeft.setOpaque(true);
        hudLeft.setBackground(new Color(15, 15, 15, 200));
        hudLeft.setBorder(BorderFactory.createLineBorder(new Color(255, 105, 180), 2, true));
        hudLeft.setBounds(8, 8, 840, 46);

        moneyLabel     = makeHudLabel("💰 500 บาท",         new Color(255, 230, 80));
        affectionLabel = makeHudLabel("💝 ความชอบ 0/100",   new Color(255, 160, 210));
        energyLabel    = makeHudLabel("⚡ 100/100",          new Color(100, 220, 255));

        JSeparator sep1 = new JSeparator(JSeparator.VERTICAL);
        sep1.setPreferredSize(new Dimension(2, 26));
        sep1.setForeground(new Color(255, 105, 180, 100));
        JSeparator sep2 = new JSeparator(JSeparator.VERTICAL);
        sep2.setPreferredSize(new Dimension(2, 26));
        sep2.setForeground(new Color(255, 105, 180, 100));

        hudLeft.add(moneyLabel);
        hudLeft.add(sep1);
        hudLeft.add(affectionLabel);
        hudLeft.add(sep2);
        hudLeft.add(energyLabel);

        // ── HUD กรอบขวา: ปุ่ม Job | Shop ──
        hudRight = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 7));
        hudRight.setOpaque(true);
        hudRight.setBackground(new Color(15, 15, 15, 200));
        hudRight.setBorder(BorderFactory.createLineBorder(new Color(255, 105, 180), 2, true));
        hudRight.setBounds(856, 8, 336, 46);

        JButton jobBtn  = makeHudButton("💼 งาน",    new Color(100, 220, 120));
        JButton shopBtn = makeHudButton("🛍 ร้านค้า", new Color(255, 105, 180));
        jobBtn.addActionListener(e  -> cardLayout.show(mainContainer, "WORK"));
        shopBtn.addActionListener(e -> cardLayout.show(mainContainer, "SHOP"));
        hudRight.add(jobBtn);
        hudRight.add(shopBtn);

        // ── Choice Panel ──
        choicePanel = new JPanel(new GridLayout(0, 1, 15, 15));
        choicePanel.setBounds(300, 150, 600, 350);
        choicePanel.setOpaque(false);
        choicePanel.setVisible(false);

        // ── ชื่อผู้พูด ──
        speakerLabel = new JLabel("");
        speakerLabel.setBounds(50, 560, 200, 40);
        speakerLabel.setOpaque(true);
        speakerLabel.setBackground(new Color(255, 105, 180));
        speakerLabel.setForeground(Color.WHITE);
        speakerLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        speakerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // ── กล่องคำพูด ──
        dialogLabel = new JLabel("", SwingConstants.CENTER);
        dialogLabel.setBounds(50, 600, 1100, 130);
        dialogLabel.setOpaque(true);
        dialogLabel.setBackground(new Color(255, 255, 255, 180));
        dialogLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
        dialogLabel.setBorder(BorderFactory.createLineBorder(new Color(255, 105, 180), 3));

        // ── Sprite + BG ──
        characterSprite = new JLabel();
        characterSprite.setBounds(0, 0, 1200, 800);
        characterSprite.setHorizontalAlignment(SwingConstants.CENTER);

        bgLabel = new JLabel();
        bgLabel.setBounds(0, 0, 1200, 800);

        // ── ปุ่ม toggle HUD (มุมบนขวาสุด) ──
        JButton toggleBtn = new JButton("📊");
        toggleBtn.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        toggleBtn.setBackground(new Color(30, 30, 30));
        toggleBtn.setForeground(new Color(255, 105, 180));
        toggleBtn.setBorder(BorderFactory.createLineBorder(new Color(255, 105, 180), 2, true));
        toggleBtn.setFocusPainted(false);
        toggleBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        toggleBtn.setToolTipText("แสดง/ซ่อน HUD");
        toggleBtn.setBounds(1162, 8, 30, 46);
        toggleBtn.addActionListener(e -> {
            hudVisible = !hudVisible;
            hudLeft.setVisible(hudVisible);
            hudRight.setVisible(hudVisible);
        });

        // ── เพิ่มและจัดเลเยอร์ ──
        panel.add(toggleBtn);
        panel.add(hudLeft);
        panel.add(hudRight);
        panel.add(choicePanel);
        panel.add(speakerLabel);
        panel.add(dialogLabel);
        panel.add(characterSprite);
        panel.add(bgLabel);

        panel.setComponentZOrder(toggleBtn,       0);
        panel.setComponentZOrder(hudLeft,         1);
        panel.setComponentZOrder(hudRight,        2);
        panel.setComponentZOrder(choicePanel,     3);
        panel.setComponentZOrder(speakerLabel,    4);
        panel.setComponentZOrder(dialogLabel,     5);
        panel.setComponentZOrder(characterSprite, 6);
        panel.setComponentZOrder(bgLabel,         7);

        // คลิกเพื่อไปต่อ
        MouseAdapter clickHandler = new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                if (!choicePanel.isVisible()) advanceDialogue();
            }
        };
        panel.addMouseListener(clickHandler);
        dialogLabel.addMouseListener(clickHandler);

        panel.addComponentListener(new ComponentAdapter() {
            @Override public void componentShown(ComponentEvent e) {
                startNewStory();
                updateStatus();
            }
        });

        return panel;
    }

    // อัปเดต HUD
    public void updateStatus() {
        if (logic == null) return;
        if (moneyLabel     != null) moneyLabel.setText("💰 " + logic.getMoney() + " บาท");
        if (affectionLabel != null) affectionLabel.setText("💝 ความชอบ " + logic.getCurrentAffection() + "/100");
        if (energyLabel    != null) energyLabel.setText("⚡ " + logic.getEnergy() + "/" + logic.getMaxEnergy());
    }

    private JLabel makeHudLabel(String text, Color color) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Tahoma", Font.BOLD, 15));
        lbl.setForeground(color);
        return lbl;
    }

    private JButton makeHudButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Tahoma", Font.BOLD, 15));
        btn.setBackground(new Color(30, 30, 30));
        btn.setForeground(color);
        btn.setBorder(BorderFactory.createLineBorder(color, 2, true));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(148, 30));
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setBackground(new Color(50, 50, 50)); }
            public void mouseExited(MouseEvent e)  { btn.setBackground(new Color(30, 30, 30)); }
        });
        return btn;
    }

    public void updateImageLayer(JLabel label, String path, int w, int h) {
        try {
            ImageIcon icon = new ImageIcon(path);
            Image img = icon.getImage();
            BufferedImage bimg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = bimg.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.setRenderingHint(RenderingHints.KEY_RENDERING,     RenderingHints.VALUE_RENDER_QUALITY);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,  RenderingHints.VALUE_ANTIALIAS_ON);
            g2.drawImage(img, 0, 0, w, h, null);
            g2.dispose();
            label.setIcon(new ImageIcon(bimg));
        } catch (Exception e) {
            System.err.println("Load Error: " + path);
        }
    }

    public void startNewStory() {
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

    public void advanceDialogue() {
        if (currentStory != null && currentStep < currentStory.size()) {
            Dialogue d = currentStory.get(currentStep);
            updateStatus();

            speakerLabel.setText(d.speaker);
            dialogLabel.setText("<html><div style='padding:15px;'>" + d.text + "</div></html>");

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

            if (d.choices != null && d.choices.length > 0) {
                showChoices(d.choices, d.nextSteps, d.affectionGains);
            } else {
                currentStep++;
                choicePanel.setVisible(false);
            }
        } else {
            cardLayout.show(mainContainer, "MENU");
        }
    }

    public void showChoices(String[] choices, int[] nextSteps, int[] affectionGains) {
        choicePanel.removeAll();
        choicePanel.setVisible(true);
        for (int i = 0; i < choices.length; i++) {
            JButton btn = new JButton(choices[i]);
            styleButton(btn);
            final int target = (i < nextSteps.length) ? nextSteps[i] : currentStep + 1;
            final int gain   = (affectionGains != null && i < affectionGains.length) ? affectionGains[i] : 0;

            btn.addActionListener(e -> {
                if (gain != 0) logic.addAffection(gain);
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

    public void styleButton(JButton btn) {
        btn.setFont(new Font("Tahoma", Font.BOLD, 22));
        btn.setBackground(Color.WHITE);
        btn.setForeground(new Color(255, 105, 180));
        btn.setBorder(BorderFactory.createLineBorder(new Color(255, 105, 180), 2));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public void show() { frame.setVisible(true); }
}