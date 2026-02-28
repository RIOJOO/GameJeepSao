<<<<<<< HEAD
import java.util.*;

public class PloyStory {

<<<<<<< HEAD
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
                "ช่วงบ่ายที่ลานกิจกรรมของมหาลัย แต่ละชมรมกำลังตั้งบูธกันคึกคัก\n"
              + "พลอยกำลังยกของพะรุงพะรังคนเดียว"
        ));

        story.add(new Dialogue(
                "SYSTEM",
                "🌸 ใครจะเข้าไปช่วยพลอย?",
                Arrays.asList(
                        new Choice("ภีมอาสาช่วยถือของอย่างสุภาพ", "PHIM", 10),
                        new Choice("เซนเข้าไปแซวแล้วช่วยทันที", "ZEN", 10),
                        new Choice("เรย์เงียบ ๆ แล้วช่วยจัดของให้", "RAY", 10)
                )
        ));

        story.add(new Dialogue(
                "พลอย",
                "“ขอบคุณนะ ถ้าไม่ได้ช่วยคงแย่เลยจริง ๆ”"
        ));

        story.add(new Dialogue(
                "SYSTEM",
                "🌸 พลอยชวนอยู่ช่วยจัดบูธต่อ",
                Arrays.asList(
                        new Choice("ภีมช่วยจัดทุกอย่างจนเรียบร้อย", "PHIM", 15),
                        new Choice("เซนชวนคนมาที่บูธให้", "ZEN", 15),
                        new Choice("เรย์แก้ปัญหาอุปกรณ์ให้เงียบ ๆ", "RAY", 15)
                )
        ));

        story.add(new Dialogue(
                "SYSTEM",
                "🌸 หลังจบงาน พลอยดูเหนื่อยแต่ยิ้ม",
                Arrays.asList(
                        new Choice("ภีมบอกว่า ภูมิใจในตัวเธอ", "PHIM", 20),
                        new Choice("เซนบอกว่า ครั้งหน้าช่วยอีกนะ", "ZEN", 20),
                        new Choice("เรย์พูดเบา ๆ ว่า วันนี้เก่งมาก", "RAY", 20)
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
            System.out.println("💖 พลอยเลือกภีม 💖");
            System.out.println("ภีม: \"เราจะช่วยเธอทุกงานเลยนะ\"");
            System.out.println("พลอยยิ้มอย่างอบอุ่นใต้แสงแดดบ่าย");
        }

        private void zenEnding() {
            System.out.println("🔥 พลอยเลือกเซน 🔥");
            System.out.println("เซน: \"เห็นไหม อยู่กับเราสนุกสุดแล้ว\"");
            System.out.println("เสียงหัวเราะดังขึ้นหน้าบูธ");
        }

        private void rayEnding() {
            System.out.println("🌙 พลอยเลือกเรย์ 🌙");
            System.out.println("เรย์: \"เราไม่พูดเยอะ…แต่เราจะอยู่ข้างเธอ\"");
            System.out.println("พลอยจับมือเขาเบา ๆ อย่างมั่นใจ");
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

public class PloyStory {
    public static List<Dialogue> getStory() {
        List<Dialogue> list = new ArrayList<>();
        list.add(new Dialogue("พลอย", "เฮ้! มาทำงานกลุ่มด้วยกันไหม?", "res/Emean.png"));
        list.add(new Dialogue("คุณ", "ได้เลยครับ", null));
        list.add(new Dialogue("พลอย", "ดีจัง! งั้นเริ่มกันเลยนะ", "res/Emean.png"));
        return list;
    }
>>>>>>> 27dd230c70694e9d37a44a119ae41c0285807474
}