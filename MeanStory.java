<<<<<<< HEAD
import java.util.*;

public class MeanStory {

<<<<<<< HEAD
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
=======
    public static void main(String[] args) {
        GameEngine engine = new GameEngine(createStory());
        engine.start();
    }

    /* ===========================
       Story
    =========================== */
    private static List<Dialogue> createStory() {

        List<Dialogue> story = new ArrayList<>();

        story.add(new Dialogue(
                "บรรยาย",
                "วันแรกของการเปิดเทอม มีนเดินเข้าห้องสมุดเพื่อหามุมเงียบ ๆ"
        ));

        story.add(new Dialogue(
                "SYSTEM",
                "🌸 ใครจะเข้าไปช่วยมีนหยิบหนังสือ?",
                Arrays.asList(
                        new Choice("ภีมช่วยหยิบให้แบบสุภาพ", "PHIM", 10),
                        new Choice("เซนเดินไปแหย่แล้วหยิบให้", "ZEN", 10),
                        new Choice("เรย์หยิบให้เงียบ ๆ แล้วส่งให้", "RAY", 10)
                )
        ));

        story.add(new Dialogue(
                "มีน",
                "“ขอบคุณนะคะ… ดีจังที่มีคนช่วย”"
        ));

        story.add(new Dialogue(
                "SYSTEM",
                "🌸 มีนชวนไปนั่งอ่านด้วยกัน",
                Arrays.asList(
                        new Choice("ภีมนั่งอ่านข้าง ๆ แบบเงียบ ๆ", "PHIM", 15),
                        new Choice("เซนชวนคุยเรื่องตลกเบา ๆ", "ZEN", 15),
                        new Choice("เรย์แนะนำหนังสือดี ๆ ให้", "RAY", 15)
                )
        ));

        story.add(new Dialogue(
                "SYSTEM",
                "🌸 ก่อนกลับบ้าน มีนยิ้มให้",
                Arrays.asList(
                        new Choice("ภีมบอกว่า ดีใจที่ได้เจอเธอ", "PHIM", 20),
                        new Choice("เซนบอกว่า คราวหน้ามาอีกนะ", "ZEN", 20),
                        new Choice("เรย์พูดเบา ๆ ว่า ระวังตัวนะ", "RAY", 20)
                )
>>>>>>> script
        ));

        return Collections.unmodifiableList(story);
    }
<<<<<<< HEAD
=======

    /* ===========================
       Game Engine
    =========================== */
    static class GameEngine {

        private final List<Dialogue> story;
        private final Scanner scanner = new Scanner(System.in);

        private int lovePhim = 0;
        private int loveZen = 0;
        private int loveRay = 0;

        private int currentIndex = 0;

        public GameEngine(List<Dialogue> story) {
            this.story = story;
        }

        public void start() {

            while (currentIndex < story.size()) {

                Dialogue current = story.get(currentIndex);

                System.out.println("\n----------------------------------");
                System.out.println("[" + current.getSpeaker() + "]");
                System.out.println(current.getText());

                if (current.hasChoices()) {

                    List<Choice> choices = current.getChoices();

                    for (int i = 0; i < choices.size(); i++) {
                        System.out.println((i + 1) + ". " + choices.get(i).getText());
                    }

                    System.out.print("เลือก: ");
                    int input = scanner.nextInt();

                    if (input < 1 || input > choices.size()) {
                        System.out.println("เลือกใหม่อีกครั้งนะคะ");
                        continue;
                    }

                    Choice selected = choices.get(input - 1);
                    addScore(selected.getTarget(), selected.getScore());
                }

                currentIndex++;
            }

            ending();
        }

        private void addScore(String target, int score) {
            switch (target) {
                case "PHIM":
                    lovePhim += score;
                    break;
                case "ZEN":
                    loveZen += score;
                    break;
                case "RAY":
                    loveRay += score;
                    break;
            }
        }

        private void ending() {

            System.out.println("\n========== ตอนจบ ==========");

            if (lovePhim > loveZen && lovePhim > loveRay) {
                phimEnding();
            } else if (loveZen > lovePhim && loveZen > loveRay) {
                zenEnding();
            } else {
                rayEnding();
            }

            System.out.println("\nจบเกม 💛");
        }

        private void phimEnding() {
            System.out.println("💖 มีนเลือกภีม 💖");
            System.out.println("ภีม: \"เราจะอยู่ข้างเธอแบบนี้ทุกวันเลยนะ\"");
            System.out.println("มีนยิ้มอบอุ่นในมุมห้องสมุด");
        }

        private void zenEnding() {
            System.out.println("🔥 มีนเลือกเซน 🔥");
            System.out.println("เซน: \"เห็นไหม อยู่กับเราสนุกสุดแล้ว\"");
            System.out.println("เสียงหัวเราะเบา ๆ ดังในห้องสมุด");
        }

        private void rayEnding() {
            System.out.println("🌙 มีนเลือกเรย์ 🌙");
            System.out.println("เรย์: \"เราอาจไม่พูดมาก…แต่เราจริงใจ\"");
            System.out.println("มีนพยักหน้าช้า ๆ อย่างมั่นใจ");
        }
    }

    /* ===========================
       Dialogue
    =========================== */
    static class Dialogue {

        private final String speaker;
        private final String text;
        private final List<Choice> choices;

        public Dialogue(String speaker, String text) {
            this(speaker, text, new ArrayList<>());
        }

        public Dialogue(String speaker, String text, List<Choice> choices) {
            this.speaker = speaker;
            this.text = text;
            this.choices = choices != null
                    ? Collections.unmodifiableList(choices)
                    : new ArrayList<>();
        }

        public String getSpeaker() { return speaker; }
        public String getText() { return text; }
        public List<Choice> getChoices() { return choices; }

        public boolean hasChoices() {
            return !choices.isEmpty();
        }
    }

    /* ===========================
       Choice
    =========================== */
    static class Choice {

        private final String text;
        private final String target;
        private final int score;

        public Choice(String text, String target, int score) {
            this.text = text;
            this.target = target;
            this.score = score;
        }

        public String getText() { return text; }
        public String getTarget() { return target; }
        public int getScore() { return score; }
    }
>>>>>>> script
=======
import java.util.ArrayList;
import java.util.List;

public class MeanStory {
    public static List<Dialogue> getStory() {
        List<Dialogue> list = new ArrayList<>();

        // Index 0
        list.add(new Dialogue("บรรยาย", "วันแรก ๆ ของการเปิดเทอม เราแวะเข้าห้องสมุดเพื่อหาที่เงียบ ๆ", "res/BGLibrary.jpg")); 
        // Index 1
        list.add(new Dialogue("บรรยาย", "ในห้องสมุดเงียบๆ มีผู้หญิงคนหนึ่งกำลังเอื้อมหยิบหนังสือไม่ถึง", "res/unnamed.jpg")); 
        // Index 2
        list.add(new Dialogue("บรรยาย", "เธอดูตั้งใจมาก จนเผลอยิ้มออกมา", "res/unnamed.jpg"));
        
        // Index 3: จุดตัวเลือกแรก (เพิ่มคะแนนความชอบ)
        list.add(new Dialogue("คุณ", "“เข้าไปช่วยดีไหมนะ...”", "res/unnamed.jpg",
            new String[]{"เข้าไปช่วยหยิบให้", "ยิ้มให้แล้วเดินผ่าน", "ยืนดูห่างๆ"},
            new int[]{4, 17, 17},           // เลือกข้อแรกไป Index 4, ข้ออื่นไป Bad End (17)
            new int[]{20, -10, 0}           // << เพิ่ม: ข้อแรก +20, ข้อสอง -10, ข้อสาม 0
        ));

        // Index 4 (รูทช่วยหยิบหนังสือ)
        list.add(new Dialogue("คุณ", "“ นี่ครับหนังสือที่คุณต้องการ ”", "res/BGLibrary.jpg|res/Givebook.png"));
        // Index 5
        list.add(new Dialogue("มีน", "“ขอบคุณนะคะ”", "res/BGLibrary.jpg|res/Mean2.png"));
        // Index 6
        list.add(new Dialogue("บรรยาย", " นั่นคือจุดเริ่มต้นของความสัมพันธ์เรา ", "res/BGLibrary.jpg"));
        // Index 7
        list.add(new Dialogue("มีน", "“วันนี้มาอ่านหนังสืออีกแล้วหรอ ดีเลย...จะได้ไม่เหงา”", "res/BGLibrary.jpg|res/Mean2.png")); 
        // Index 8
        list.add(new Dialogue("คุณ", "“ เจอกันอีกแล้วนะ ”", "res/BGLibrary.jpg|res/Givebook.png"));
        
        // Index 9:
        list.add(new Dialogue("มีน", "“ช่วงนี้เรามานั่งอ่านหนังสือด้วยกันบ่อยเนอะ”", "res/BGLibrary.jpg|res/Mean2.png"));
        
        // Index 10: คำถามสารภาพรัก (เพิ่มคะแนนความชอบ)
        list.add(new Dialogue("คุณ", "“ เราก็รู้สึกเหมือนกันนะ ”", "res/BGLibrary.jpg|res/Givebook.png",
            new String[]{
                "งั้นต่อไป...ขออยู่ข้างๆแบบนี้ทุกวันได้ไหม",
                "เราก็ดีใจนะ ที่มีเธอเป็นเพื่อนอ่านหนังสือ",
                "ขอโทษนะ หลังจากนี้เราอาจไม่ได้มาบ่อยๆแล้ว"
            },
            new int[]{11, 14, 17},          // ไป Good End (11), Friend End (14), Bad End (17)
            new int[]{50, 10, -30}          // << เพิ่ม: ให้คะแนนตามระดับความสัมพันธ์
        ));

        // --- Good End (เริ่มที่ Index 11) ---
        list.add(new Dialogue("มีน", "“เล่มนี้สนุกมาก แต่อยากอ่านไปพร้อมกันมากกว่า”", "res/BGLibrary.jpg|res/Mean2.png")); 
        list.add(new Dialogue("บรรยาย", "มีนยื่นหนังสือที่มีลายมือเล็กๆ เขียนคั่นไว้ แล้วเธอก็นั่งข้างเราเหมือนทุกวัน", "res/BGLibrary.jpg|res/Mean2.png")); 
        list.add(new Dialogue("SYSTEM", "--- HAPPY ENDING ---", null));

        // --- Friend End (เริ่มที่ Index 14) ---
        list.add(new Dialogue("มีน", "“ขอบคุณนะที่ชอบมาอ่านด้วยกัน อยู่ด้วยแล้วสบายใจดี”", "res/BGLibrary.jpg|res/Mean2.png")); 
        list.add(new Dialogue("บรรยาย", "ความสัมพันธ์ของเรา กลายเป็นมุมสงบ ๆ ในห้องสมุดเสมอ", "res/BGLibrary.jpg")); 
        list.add(new Dialogue("SYSTEM", "--- FRIEND ENDING ---", null));

        // --- Bad End (เริ่มที่ Index 17) ---
        list.add(new Dialogue("บรรยาย", "เธอก้มหน้าอ่านหนังสือเงียบ ๆ เหมือนกำลังรอใครบางคนที่ไม่ได้กลับมาอีก", "res/empty_chair.jpg")); 
        list.add(new Dialogue("บรรยาย", "มุมประจำยังเหมือนเดิม แต่ที่นั่งข้างมีนว่างเปล่าเสมอ...", "res/empty_chair.jpg")); 
        list.add(new Dialogue("SYSTEM", "--- BAD ENDING ---", null));

        return list;
    }
>>>>>>> 27dd230c70694e9d37a44a119ae41c0285807474
}