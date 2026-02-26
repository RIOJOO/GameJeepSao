import java.util.*;

public class LilliStory {

    public static List<Dialogue> getStory() {

        List<Dialogue> story = new ArrayList<>();

        // 0
        story.add(new Dialogue(
                "บรรยาย",
                "ช่วงเย็นหลังเลิกเรียน แถวตึกศิลปะเงียบกว่าปกติ\n"
              + "มีผู้หญิงคนหนึ่งนั่งวาดรูปอยู่ใต้ต้นไม้ แสงเย็นสะท้อนโทนสีอบอุ่นในสมุดของเธอ",
                "res/ArtBuilding.png"
        ));

        // 1
        story.add(new Dialogue(
                "คุณ",
                "\u201cชอบวาดรูปสไตล์นี้เหรอ เราว่าสวยนะ\u201d",
                "res/ArtBuilding.png"
        ));

        // 2
        story.add(new Dialogue(
                "ลิลลี่",
                "\u201cขะ\u2026ขอบคุณนะ ไม่ค่อยมีคนมาดูรูปเราหรอก\u201d",
                "res/LilliShy.png"
        ));

        // 3 - Choice: ฉาก 1
        story.add(new Dialogue(
                "SYSTEM",
                "\uD83C\uDF38 ฉาก 1: เห็นวาดรูป",
                "res/ArtBuilding.png",
                new String[]{"ชมว่าวาดสวย", "ขอชมใกล้ ๆ", "มองไกล ๆ"},
                new int[]{4, 4, 5},
                new int[]{15, 10, 0}
        ));

        // 4
        story.add(new Dialogue(
                "ลิลลี่",
                "\u201cเราชอบมาวาดตรงนี้ตอนเย็น แสงมันสวยดี\u2026แล้วก็สงบ\u201d",
                "res/LilliSmile.png"
        ));

        // 5 - Choice: ฉาก 2
        story.add(new Dialogue(
                "SYSTEM",
                "\uD83C\uDF38 ฉาก 2: มุมศิลปะ",
                "res/ArtBuilding.png",
                new String[]{"ชวนคุยเรื่องศิลปะ", "ลองวาดด้วยกัน", "ดูเฉย ๆ"},
                new int[]{6, 6, 7},
                new int[]{10, 15, 0}
        ));

        // 6
        story.add(new Dialogue(
                "ลิลลี่",
                "\u201cช่วงนี้วาดรูปได้นานขึ้นนะ เพราะมีคนนั่งเป็นเพื่อน\u201d",
                "res/LilliHappy.png"
        ));

        // 7 - Choice: ฉาก 3
        story.add(new Dialogue(
                "SYSTEM",
                "\uD83C\uDF38 ฉาก 3: นั่งดูพระอาทิตย์ตก",
                "res/Sunset.png",
                new String[]{"ชวนดูวิว", "ถ่ายรูปให้", "ขอตัวกลับ"},
                new int[]{8, 8, 9},
                new int[]{15, 10, 0}
        ));

        // 8 - Good ending path
        story.add(new Dialogue(
                "ลิลลี่",
                "\u201cรู้ไหม ทำไมเราชอบวาดเธอ\u201d\n\u201cเพราะเวลาเธอยิ้ม มันดูอบอุ่น\u201d",
                "res/LilliClose.png"
        ));

        // 9 - Ending branch (score-based shown in GameUI or here as placeholder)
        story.add(new Dialogue(
                "SYSTEM",
                "\u2728 ขึ้นอยู่กับคะแนนความชอบ...",
                "res/Sunset.png"
        ));

        return Collections.unmodifiableList(story);
    }
}