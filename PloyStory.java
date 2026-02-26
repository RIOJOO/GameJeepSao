import java.util.*;

public class PloyStory {

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
        ));

        return Collections.unmodifiableList(story);
    }

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
}