public class GameLogic {
    private int affectionScore = 0; // คะแนนความรัก

    public void addScore(int points) {
        this.affectionScore += points;
        System.out.println("คะแนนปัจจุบัน: " + affectionScore);
    }

    public int getScore() {
        return affectionScore;
    }
}