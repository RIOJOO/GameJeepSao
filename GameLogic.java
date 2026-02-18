public class GameLogic {
    private String selectedCharacter = "";

    // ฝั่ง UI เรียกใช้เพื่อบันทึกว่ากดเลือกใคร
    public void setSelectedCharacter(String name) {
        this.selectedCharacter = name; // UI ส่งชื่อมาเก็บไว้ที่นี่
    }

    // ฝั่ง UI (Gameplay) เรียกใช้เพื่อดูว่าต้องโหลดเนื้อเรื่องใคร
    public String getSelectedCharacter() {
        return this.selectedCharacter; // ส่งชื่อกลับไปให้ UI แสดงผล
    }

    // เมธอดอื่นๆ ที่เพื่อนอาจจะทำ เช่น คะแนน
    public void addScore(int s) { /* เพื่อนเขียนต่อ */ }
}