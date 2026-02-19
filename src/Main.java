public class Main {
    public static void main(String[] args) {
        // สร้าง Object ของ Logic ก่อน
        GameLogic myLogic = new GameLogic();
        
        // ส่ง Logic เข้าไปใน UI เพื่อให้เชื่อมถึงกัน
        GameUI myUI = new GameUI(myLogic);
        
        // สั่งให้ UI แสดงผล
        myUI.show();
    }
}