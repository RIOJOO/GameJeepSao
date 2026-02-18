import java.util.ArrayList;
import java.util.List;

public class MeanStory {
    public static List<Dialogue> getStory() {
        List<Dialogue> list = new ArrayList<>();
        // ตัวอย่างบทสนทนา (ตรวจสอบชื่อไฟล์รูปภาพใน res ให้ตรงกัน)
        list.add(new Dialogue("มีน", "สวัสดีค่ะ... มาหาหนังสืออ่านเหรอคะ?", "res/Mean.png"));
        list.add(new Dialogue("คุณ", "ใช่ครับ บรรยากาศเงียบดีนะ", null));
        list.add(new Dialogue("มีน", "ดีจังค่ะ ถ้ามีอะไรให้ช่วยบอกได้นะ", "res/Mean.png"));
        return list;
    }
}