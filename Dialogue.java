                                    public class Dialogue {
    public String speaker;
    public String text;
    public String imagePath;
    // เพิ่มตัวแปรสำหรับระบบ Choice
    public String[] choices;
    public int[] nextSteps;

    // แบบที่ 1: สำหรับบทสนทนาปกติ (เหมือนที่คุณมีอยู่เดิม)
    public Dialogue(String speaker, String text, String imagePath) {
        this.speaker = speaker;
        this.text = text;
        this.imagePath = imagePath;
        this.choices = null; 
        this.nextSteps = null;
    }

    // แบบที่ 2: สำหรับบทสนทนาที่ต้องการให้มีตัวเลือกตอบ
    public Dialogue(String speaker, String text, String imagePath, String[] choices, int[] nextSteps) {
        this.speaker = speaker;
        this.text = text;
        this.imagePath = imagePath;
        this.choices = choices;
        this.nextSteps = nextSteps;
    }
}
