import java.util.*;

public class MeanStory {

    public static List<Dialogue> getStory() {

        List<Dialogue> story = new ArrayList<>();

        // 0
        story.add(new Dialogue(
                "บรรยาย",
                "วันแรก ๆ ของการเปิดเทอม คุณแวะเข้าห้องสมุดเพื่อหาที่เงียบ ๆ อ่านหนังสือ",
                "res/BGLibrary.png"
        ));

        // 1 - Choice: ช่วย→2, เดินผ่าน→7
        story.add(new Dialogue(
                "บรรยาย",
                "คุณเห็นผู้หญิงคนหนึ่งกำลังเอื้อมหยิบหนังสือไม่ถึง เธอดูตั้งใจมาก",
                "res/unnamed.png",
                new String[]{"เข้าไปช่วยหยิบให้", "ยิ้มให้แล้วเดินผ่าน", "ยืนดูเงียบ ๆ"},
                new int[]{2, 7, 7},
                new int[]{10, 0, 0}
        ));

        // 2
        story.add(new Dialogue(
                "คุณ",
                "\u201cนี่ครับ หนังสือที่คุณต้องการ\u201d",
                "res/BGLibrary|res/givebookNoBG.png"
        ));

        // 3
        story.add(new Dialogue(
                "มีน",
                "\u201cเอ๊ะ\u2026 ขอบคุณมากนะคะ ถ้าไม่ได้คุณช่วยคงต้องปีนชั้นหนังสือแล้วแน่ ๆ เลย\u201d",
                "res/MeanHappy.png"
        ));

        // 4 - Choice: ตอบตกลง→5, เฉย ๆ→9
        story.add(new Dialogue(
                "มีน",
                "\u201cคุณก็มาห้องสมุดบ่อยเหรอคะ ไว้ถ้าเจอกันอีก มานั่งอ่านด้วยกันได้นะ\u201d",
                "res/MeanSmile.png",
                new String[]{"ยิ้มแล้วตอบตกลง", "บอกว่าแค่มาหาหนังสือเฉย ๆ"},
                new int[]{5, 9},
                new int[]{10, 0}
        ));

        // 5
        story.add(new Dialogue(
                "มีน",
                "\u201cดีใจจัง งั้นต่อไปเรามาอ่านด้วยกันบ่อย ๆ นะ\u201d",
                "res/MeanClose.png"
        ));

        // 6 - Happy Ending
        story.add(new Dialogue(
                "SYSTEM",
                "\uD83D\uDC96 HAPPY ENDING \uD83D\uDC96",
                "res/HappyEnding.png"
        ));

        // 7 - Bad Route
        story.add(new Dialogue(
                "บรรยาย",
                "คุณเดินผ่านไป เธอก้มหน้าอ่านหนังสือต่ออย่างเงียบ ๆ",
                "res/LibrarySilent.png"
        ));

        // 8 - Bad Ending
        story.add(new Dialogue(
                "SYSTEM",
                "\uD83D\uDC94 BAD ENDING \uD83D\uDC94",
                "res/BadEnding.png"
        ));

        // 9 - Friend Route
        story.add(new Dialogue(
                "มีน",
                "\u201cอ๋อ เข้าใจแล้วค่ะ ยังไงก็ขอบคุณอีกครั้งนะคะ\u201d",
                "res/MeanFriend.png"
        ));

        // 10 - Friend Ending
        story.add(new Dialogue(
                "SYSTEM",
                "\uD83D\uDE42 FRIEND ENDING \uD83D\uDE42",
                "res/FriendEnding.png"
        ));

        return Collections.unmodifiableList(story);
    }
}