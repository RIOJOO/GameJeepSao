import java.util.*;

public class LilliStory {

<<<<<<< HEAD
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
                "ช่วงเย็นหลังเลิกเรียน แถวตึกศิลปะเงียบกว่าปกติ\n"
              + "ลิลลี่กำลังนั่งวาดรูปอยู่ใต้ต้นไม้"
        ));

        story.add(new Dialogue(
                "SYSTEM",
                "🌸 ฉาก 1: ใครจะเข้าไปคุยกับลิลลี่",
                Arrays.asList(
                        new Choice("ภีมเข้าไปชมรูปอย่างสุภาพ", "PHIM", 10),
                        new Choice("เซนเดินเข้าไปแหย่เล่น", "ZEN", 10),
                        new Choice("เรย์ยืนดูเงียบ ๆ แล้วค่อยพูด", "RAY", 10)
                )
        ));

        story.add(new Dialogue(
                "SYSTEM",
                "🌸 ฉาก 2: ลิลลี่ดูเหนื่อย ๆ",
                Arrays.asList(
                        new Choice("ภีมยื่นน้ำให้", "PHIM", 15),
                        new Choice("เซนชวนไปกินของหวาน", "ZEN", 15),
                        new Choice("เรย์ช่วยเก็บของให้", "RAY", 15)
                )
        ));

        story.add(new Dialogue(
                "SYSTEM",
                "🌸 ฉาก 3: พระอาทิตย์กำลังตก",
                Arrays.asList(
                        new Choice("ภีมชวนดูวิวเงียบ ๆ", "PHIM", 20),
                        new Choice("เซนขอถ่ายรูปคู่", "ZEN", 20),
                        new Choice("เรย์พูดความในใจเบา ๆ", "RAY", 20)
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
                        System.out.println("เลือกใหม่อีกครั้งนะ");
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

            System.out.println("\nจบเกม 💜");
        }

        private void phimEnding() {
            System.out.println("💖 ลิลลี่เลือกภีม 💖");
            System.out.println("ภีม: \"เราจะดูแลเธอแบบนี้ทุกวันเลย\"");
            System.out.println("ลิลลี่ยิ้มอย่างอบอุ่นใต้แสงเย็น");
        }

        private void zenEnding() {
            System.out.println("🔥 ลิลลี่เลือกเซน 🔥");
            System.out.println("เซน: \"เห็นไหม อยู่กับเราสนุกสุดแล้ว\"");
            System.out.println("เสียงหัวเราะดังขึ้นใต้ต้นไม้ต้นเดิม");
        }

        private void rayEnding() {
            System.out.println("🌙 ลิลลี่เลือกเรย์ 🌙");
            System.out.println("เรย์: \"เราไม่เก่งเรื่องพูด…แต่เราจริงใจ\"");
            System.out.println("ลิลลี่จับมือเขาเบา ๆ อย่างมั่นคง");
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
}