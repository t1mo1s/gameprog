import java.awt.*;

class Game_Timer extends A_TextObject {
    private long s, ms;

    public Game_Timer(int x, int y) {
        super(x, y, 20, Color.WHITE, Color.BLACK);
    }

    public String toString() {
        return "Time: " + s + ":" + ms;
    }

    public void setTimer(long s, long ms) {
        this.s = s;
        this.ms = ms;
    }
}
