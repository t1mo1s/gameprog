import java.awt.*;

class Game_HighScore extends A_TextObject {
    private long s = 0, ms = 0;

    public Game_HighScore(int x, int y) {
        super(x, y, 20, Color.WHITE, Color.BLACK);
    }

    public String toString() {
        return "HighScore: " + s + ":" + ms;
    }

    public void setHS(long s, long ms) {
        this.s = s;
        this.ms = ms;
    }
}
