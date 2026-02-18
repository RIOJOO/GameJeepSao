import java.awt.*;
import javax.swing.*;

public class CharacterSelect extends JPanel {
    private CardLayout cardLayout;
    private JPanel mainContainer;
    private GameLogic logic;

    public CharacterSelect(CardLayout cardLayout, JPanel mainContainer, GameLogic logic) {
        this.cardLayout = cardLayout;
        this.mainContainer = mainContainer;
        this.logic = logic;

        setLayout(new BorderLayout());
        setBackground(new Color(255, 240, 245)); // พื้นหลังโทนชมพูอ่อน

        // --- 1. ส่วนหัวข้อ (North) ---
        JLabel title = new JLabel("เลือกผู้หญิงที่อยากทำความรู้จัก", SwingConstants.CENTER);
        title.setFont(new Font("Tahoma", Font.BOLD, 36));
        title.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 0));
        add(title, BorderLayout.NORTH);

        // --- 2. ส่วนแสดงตัวละคร (Center) ---
        // ใช้ GridLayout 1 แถว 3 คอลัมน์ ระยะห่างระหว่างกัน 50px
        JPanel charContainer = new JPanel(new GridLayout(1, 3, 50, 0)); 
        charContainer.setOpaque(false);
        // เพิ่ม Border รอบข้าง (ซ้าย-ขวา 100px) เพื่อให้ตัวละครไม่ออกไปชิดขอบจอเกินไป
        charContainer.setBorder(BorderFactory.createEmptyBorder(10, 80, 40, 80));

        // สร้างการ์ดตัวละคร 3 คน
        charContainer.add(createCharCard("มีน", "res/Mean.png"));
        charContainer.add(createCharCard("พลอย", "res/Emean.png"));
        charContainer.add(createCharCard("พี่ลิลลี่", "res/lilly_thumb.png"));

        add(charContainer, BorderLayout.CENTER);
    }

    private JPanel createCharCard(String name, String imgPath) {
        System.out.println("Current Directory: " + System.getProperty("user.dir"));
    JPanel card = new JPanel(new BorderLayout(0, 15));
    card.setOpaque(false);

    // --- ส่วนรูปภาพ ---
    ImageIcon icon = new ImageIcon(imgPath);
    // ขนาด 220x380 เป็นค่าที่เหมาะสมสำหรับหน้าจอ 1200x800 ของคุณ
    Image scaled = icon.getImage().getScaledInstance(220, 380, Image.SCALE_SMOOTH);
    JLabel imgLabel = new JLabel(new ImageIcon(scaled));
    imgLabel.setHorizontalAlignment(JLabel.CENTER);
    // เพิ่มคำสั่งนี้เพื่อให้รูปยืนชิดขอบล่าง ป้องกันหัวขาด
    imgLabel.setVerticalAlignment(JLabel.BOTTOM); 
    
    // --- ส่วนปุ่มกด ---
    JPanel btnWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
    btnWrapper.setOpaque(false);
    
    JButton selectBtn = new JButton("เลือก " + name);
    styleButton(selectBtn);
    btnWrapper.add(selectBtn);
    
    selectBtn.addActionListener(e -> {
        JOptionPane.showMessageDialog(this, "คุณเลือก " + name);
        // อย่าลืมสร้างเมธอดใน GameLogic เพื่อเก็บค่าตัวละครที่ผู้เล่นเลือก
        // logic.setSelectedCharacter(name); 
        cardLayout.show(mainContainer, "GAMEPLAY"); 
    });

    card.add(imgLabel, BorderLayout.CENTER);
    card.add(btnWrapper, BorderLayout.SOUTH);
    return card;
}

    private void styleButton(JButton btn) {
        btn.setPreferredSize(new Dimension(200, 50));
        btn.setFont(new Font("Tahoma", Font.BOLD, 18));
        btn.setBackground(Color.WHITE);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createLineBorder(new Color(255, 105, 180), 2));
    }
}