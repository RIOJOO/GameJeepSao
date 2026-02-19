import java.util.ArrayList;
import java.util.List;

public class LilliStory {
    public static List<Dialogue> getStory() {
        List<Dialogue> list = new ArrayList<>();
        list.add(new Dialogue("ลิลลี่", "น้อง... มาหาพี่มีธุระอะไรคะ?", "res/Lilli.png"));
        list.add(new Dialogue("คุณ", "อยากคุยด้วยครับพี่", null));
        list.add(new Dialogue("ลิลลี่", "ได้สิ พี่ว่างพอดี ♥", "res/Lilli.png"));
        return list;
    }
}