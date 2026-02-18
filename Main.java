import java.awt.Font;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        UIManager.put("OptionPane.messageFont", new Font("Tahoma", Font.PLAIN, 16));
        UIManager.put("Button.font", new Font("Tahoma", Font.PLAIN, 14));
        
        GameLogic logic = new GameLogic();
        GameUI ui = new GameUI(logic);
        ui.show();
    }
}