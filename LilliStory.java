import java.util.*;

public class LilliStory {

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
              + "มีผู้หญิงคนหนึ่งนั่งวาดรูปอยู่ใต้ต้นไม้ แสงเย็นสะท้อนโทนสีอบอุ่นในสมุดของเธอ"
        ));

        story.add(new Dialogue(
                "คุณ",
                "“ชอบวาดรูปสไตล์นี้เหรอ เราว่าสวยนะ”"
        ));

        story.add(new Dialogue(
                "ลิลลี่",
                "“ขะ…ขอบคุณนะ ไม่ค่อยมีคนมาดูรูปเราหรอก”"
        ));

        story.add(new Dialogue(
                "SYSTEM",
                "🌸 ฉาก 1: เห็นวาดรูป",
                Arrays.asList(
                        new Choice("ชมว่าวาดสวย", 10),
                        new Choice("ขอชมใกล้ ๆ", 10),
                        new Choice("มองไกล ๆ", 5)
                )
        ));

        story.add(new Dialogue(
                "ลิลลี่",
                "“เราชอบมาวาดตรงนี้ตอนเย็น แสงมันสวยดี…แล้วก็สงบ”"
        ));

        story.add(new Dialogue(
                "SYSTEM",
                "🌸 ฉาก 2: มุมศิลปะ",
                Arrays.asList(
                        new Choice("ชวนคุยเรื่องศิลปะ", 10),
                        new Choice("ลองวาดด้วยกัน", 15),
                        new Choice("ดูเฉย ๆ", 5)
                )
        ));

        story.add(new Dialogue(
                "ลิลลี่",
                "“ช่วงนี้วาดรูปได้นานขึ้นนะ เพราะมีคนนั่งเป็นเพื่อน”"
        ));

        story.add(new Dialogue(
                "SYSTEM",
                "🌸 ฉาก 3: นั่งดูพระอาทิตย์ตก",
                Arrays.asList(
                        new Choice("ชวนดูวิว", 15),
                        new Choice("ถ่ายรูปให้", 10),
                        new Choice("ขอตัวกลับ", 0)
                )
        ));

        return Collections.unmodifiableList(story);
    }

    /* ===========================
       Game Engine (มีระบบคะแนน)
    =========================== */
    static class GameEngine {

        private final List<Dialogue> story;
        private final Scanner scanner = new Scanner(System.in);
        private int affection = 0;
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

                    affection += choices.get(input - 1).getScore();
                }

                currentIndex++;
            }

            ending();
        }

        private void ending() {

            System.out.println("\n========== ตอนจบ ==========");

            if (affection >= 35) {
                goodEnding();
            } else if (affection >= 20) {
                friendEnding();
            } else {
                badEnding();
            }

            System.out.println("\nจบเกม 💜");
        }

        private void goodEnding() {
            System.out.println("💖 GOOD ENDING 💖");
            System.out.println("ลิลลี่ยื่นสมุดวาดรูปให้คุณดู");
            System.out.println("ในภาพต้นไม้ตอนเย็น มีคนนั่งอยู่สองคนเคียงกัน");
            System.out.println("ลิลลี่:");
            System.out.println("“รู้ไหม ทำไมเราชอบวาดเธอ”");
            System.out.println("“เพราะเวลาเธอยิ้ม มันดูอบอุ่น”");
            System.out.println("“เธอไม่เคยกดดันเราเลย”");
            System.out.println("“เราชอบเธอนะ”");
            System.out.println("✨ คบกันแบบละมุน");
        }

        private void friendEnding() {
            System.out.println("🙂 FRIEND ENDING 🙂");
            System.out.println("ลิลลี่เปิดสมุดให้ดู เป็นภาพวิวตอนเย็น");
            System.out.println("“ดีใจนะที่มานั่งเป็นเพื่อนบ่อย ๆ”");
            System.out.println("“ไว้มาเป็นแบบให้เราวาดอีก”");
            System.out.println("✨ เพื่อนสายอาร์ต");
        }

        private void badEnding() {
            System.out.println("💔 BAD ENDING 💔");
            System.out.println("ใต้ต้นไม้ต้นเดิม ลิลลี่ยังนั่งวาดรูปเงียบ ๆ");
            System.out.println("ข้าง ๆ เธอว่างเปล่า เหมือนที่นั่งของคุณ");
            System.out.println("“เรายังอยากโฟกัสเรื่องของตัวเองก่อน”");
            System.out.println("✨ จบแบบนิ่ง ๆ");
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
        private final int score;

        public Choice(String text, int score) {
            this.text = text;
            this.score = score;
        }

        public String getText() { return text; }
        public int getScore() { return score; }
    }
}