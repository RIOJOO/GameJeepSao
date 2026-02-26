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

        // 0
        story.add(new Dialogue(
                "บรรยาย",
                "ช่วงบ่ายที่ลานกิจกรรมของมหาลัย แต่ละชมรมกำลังตั้งบูธกันคึกคัก"
        ));

        // 1
        story.add(new Dialogue(
                "บรรยาย",
                "คุณเห็นผู้หญิงคนหนึ่งกำลังยกของพะรุงพะรังคนเดียว ดูวุ่นวายมาก",
                Arrays.asList(
                        new Choice("อาสาช่วยงาน", 2),
                        new Choice("ชมบูธว่าน่ารัก", 4),
                        new Choice("เดินผ่านเฉย ๆ", 7)
                )
        ));

        // 2
        story.add(new Dialogue(
                "คุณ",
                "“เดี๋ยวผมช่วยถือให้นะครับ”"
        ));

        // 3
        story.add(new Dialogue(
                "พลอย",
                "“โห ถ้าไม่ได้ช่วยนะ ของคงหล่นหมดแล้ว ขอบคุณมากจริง ๆ”"
        ));

        // 4
        story.add(new Dialogue(
                "พลอย",
                "“วันนี้ก็แวะมาช่วยอีกเหรอ เริ่มเป็นผู้ช่วยประจำบูธเราแล้วนะเนี่ย”",
                Arrays.asList(
                        new Choice("ช่วยจัดของต่อ", 5),
                        new Choice("ซื้อเครื่องดื่มมาให้", 5)
                )
        ));

        // 5 GOOD ROUTE
        story.add(new Dialogue(
                "พลอย",
                "“ไม่รู้ทำไม พอเห็นหน้าแล้วหายเหนื่อยเลย”\n"
              + "เธอยิ้มแบบเขิน ๆ ก่อนจะยื่นสายคล้องบัตรมาให้"
        ));

        // 6 GOOD END
        story.add(new Dialogue(
                "SYSTEM",
                "💖 GOOD ENDING 💖\n"
              + "หลังจบงาน พลอยพูดเบา ๆ\n"
              + "“เราชอบเธอเหมือนกันนะ”\n"
              + "✨ คบกันแบบสดใส"
        ));

        // 7 BAD ROUTE
        story.add(new Dialogue(
                "บรรยาย",
                "คุณเดินผ่านไป บูธยังคึกคักเหมือนเดิม"
        ));

        // 8 BAD END
        story.add(new Dialogue(
                "SYSTEM",
                "💔 BAD ENDING 💔\n"
              + "ข้าง ๆ พลอยเป็นคนอื่นที่ช่วยแทน\n"
              + "รอยยิ้มนั้นไม่ได้หันมาทางคุณอีกแล้ว"
        ));

        return Collections.unmodifiableList(story);
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

                    if (currentIndex >= story.size()) {
                        break;
                    }
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