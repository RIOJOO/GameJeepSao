import java.util.*;

public class PloyStory {

    public static List<Dialogue> getStory() {

        List<Dialogue> story = new ArrayList<>();

        // 0
        story.add(new Dialogue(
                "บรรยาย",
                "ช่วงบ่ายที่ลานกิจกรรมของมหาลัย แต่ละชมรมกำลังตั้งบูธกันคึกคัก",
                "res/Campus.png"
        ));

        // 1 - Choice
        story.add(new Dialogue(
                "บรรยาย",
                "คุณเห็นผู้หญิงคนหนึ่งกำลังยกของพะรุงพะรังคนเดียว ดูวุ่นวายมาก",
                "res/Campus.png",
                new String[]{"อาสาช่วยงาน", "ชมบูธว่าน่ารัก", "เดินผ่านเฉย ๆ"},
                new int[]{2, 4, 7},
                new int[]{15, 5, 0}
        ));

        // 2
        story.add(new Dialogue(
                "คุณ",
                "\u201cเดี๋ยวผมช่วยถือให้นะครับ\u201d",
                "res/Campus.png"
        ));

        // 3
        story.add(new Dialogue(
                "พลอย",
                "\u201cโห ถ้าไม่ได้ช่วยนะ ของคงหล่นหมดแล้ว ขอบคุณมากจริง ๆ\u201d",
                "res/PloyHappy.png"
        ));

        // 4 - Choice
        story.add(new Dialogue(
                "พลอย",
                "\u201cวันนี้ก็แวะมาช่วยอีกเหรอ เริ่มเป็นผู้ช่วยประจำบูธเราแล้วนะเนี่ย\u201d",
                "res/PloySmile.png",
                new String[]{"ช่วยจัดของต่อ", "ซื้อเครื่องดื่มมาให้"},
                new int[]{5, 5},
                new int[]{10, 10}
        ));

        // 5 - Good Route
        story.add(new Dialogue(
                "พลอย",
                "\u201cไม่รู้ทำไม พอเห็นหน้าแล้วหายเหนื่อยเลย\u201d\n"
              + "เธอยิ้มแบบเขิน ๆ ก่อนจะยื่นสายคล้องบัตรมาให้",
                "res/PloyClose.png"
        ));

        // 6 - Good Ending
        story.add(new Dialogue(
                "SYSTEM",
                "\uD83D\uDC96 GOOD ENDING \uD83D\uDC96\n"
              + "หลังจบงาน พลอยพูดเบา ๆ\n"
              + "\u201cเราชอบเธอเหมือนกันนะ\u201d\n"
              + "\u2728 คบกันแบบสดใส",
                "res/GoodEnding.png"
        ));

        // 7 - Bad Route
        story.add(new Dialogue(
                "บรรยาย",
                "คุณเดินผ่านไป บูธยังคึกคักเหมือนเดิม",
                "res/Campus.png"
        ));

        // 8 - Bad Ending
        story.add(new Dialogue(
                "SYSTEM",
                "\uD83D\uDC94 BAD ENDING \uD83D\uDC94\n"
              + "ข้าง ๆ พลอยเป็นคนอื่นที่ช่วยแทน\n"
              + "รอยยิ้มนั้นไม่ได้หันมาทางคุณอีกแล้ว",
                "res/BadEnding.png"
        ));

        return Collections.unmodifiableList(story);
    }
}