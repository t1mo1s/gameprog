import java.awt.*;

class Game_LevelDisplay extends A_TextObject {
    private int number = 1;

    public Game_LevelDisplay(int x, int y) {
        super(x, y, 20, Color.WHITE, Color.BLACK);
    }

    public String toString() {
        return "Level: " + number;
    }

    public void setLVL(int n) {
        number = n;
    }
}
