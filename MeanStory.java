import java.util.*;

public class MeanStory {

    public static void main(String[] args) {
        GameEngine engine = new GameEngine(createStory());
        engine.start();
    }

    /* ===========================
       Story (ทำให้ private)
    =========================== */
    private static List<Dialogue> createStory() {

        List<Dialogue> story = new ArrayList<>();

        story.add(new Dialogue(
                "บรรยาย",
                "วันแรก ๆ ของการเปิดเทอม คุณแวะเข้าห้องสมุดเพื่อหาที่เงียบ ๆ อ่านหนังสือ"
        ));

        story.add(new Dialogue(
                "บรรยาย",
                "คุณเห็นผู้หญิงคนหนึ่งกำลังเอื้อมหยิบหนังสือไม่ถึง เธอดูตั้งใจมาก",
                Arrays.asList(
                        new Choice("เข้าไปช่วยหยิบให้", 2),
                        new Choice("ยิ้มให้แล้วเดินผ่าน", 7),
                        new Choice("ยืนดูเงียบ ๆ", 7)
                )
        ));

        story.add(new Dialogue("คุณ", "“นี่ครับ หนังสือที่คุณต้องการ”"));
        story.add(new Dialogue("มีน",
                "“เอ๊ะ… ขอบคุณมากนะคะ ถ้าไม่ได้คุณช่วยคงต้องปีนชั้นหนังสือแล้วแน่ ๆ เลย”"));

        story.add(new Dialogue(
                "มีน",
                "“คุณก็มาห้องสมุดบ่อยเหรอคะ ไว้ถ้าเจอกันอีก มานั่งอ่านด้วยกันได้นะ”",
                Arrays.asList(
                        new Choice("ยิ้มแล้วตอบตกลง", 5),
                        new Choice("บอกว่าแค่มาหาหนังสือเฉย ๆ", 9)
                )
        ));

        story.add(new Dialogue("มีน",
                "“ดีใจจัง งั้นต่อไปเรามาอ่านด้วยกันบ่อย ๆ นะ”\n"
                        + "เธอยิ้มบาง ๆ ก่อนจะขยับเก้าอี้ให้อยู่ใกล้คุณขึ้นนิดหนึ่ง"));

        story.add(new Dialogue("SYSTEM",
                "💖 HAPPY ENDING 💖\n"
                        + "จากวันนั้น มุมเล็ก ๆ ในห้องสมุดก็กลายเป็นที่ประจำของคุณสองคน"));

        story.add(new Dialogue("บรรยาย",
                "คุณเดินผ่านไป เธอก้มหน้าอ่านหนังสือต่ออย่างเงียบ ๆ"));

        story.add(new Dialogue("SYSTEM",
                "💔 BAD ENDING 💔\n"
                        + "บางความสัมพันธ์ อาจเริ่มต้นได้จากความกล้าเพียงก้าวเดียว…"));

        story.add(new Dialogue("มีน",
                "“อ๋อ เข้าใจแล้วค่ะ ยังไงก็ขอบคุณอีกครั้งนะคะ”\n"
                        + "เธอยิ้มให้แบบเป็นมิตร ก่อนจะหันกลับไปอ่านหนังสือต่อ"));

        story.add(new Dialogue("SYSTEM",
                "🙂 FRIEND ENDING 🙂\n"
                        + "คุณสองคนกลายเป็นเพื่อนร่วมมุมอ่านหนังสือ ที่ทักกันทุกครั้งเวลาเจอ"));

        return Collections.unmodifiableList(story); //  ป้องกันแก้ไขภายนอก
    }

    /* ===========================
       Game Engine
    =========================== */
    static class GameEngine {

        private final List<Dialogue> story;
        private int currentIndex = 0;
        private final Scanner scanner = new Scanner(System.in);

        public GameEngine(List<Dialogue> story) {
            this.story = story;
        }

        public void start() {

            while (true) {

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

                    currentIndex = choices.get(input - 1).getNextIndex();

                } else {
                    currentIndex++;
                    if (currentIndex >= story.size()) break;
                }
            }

            System.out.println("\nจบเกม 💛");
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
        private final int nextIndex;

        public Choice(String text, int nextIndex) {
            this.text = text;
            this.nextIndex = nextIndex;
        }

        public String getText() { return text; }
        public int getNextIndex() { return nextIndex; }
    }
}