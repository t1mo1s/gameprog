import java.awt.*;

class Game_Over extends A_TextObject {

    public Game_Over(int x, int y) {
        super(x - 150, y, 150, Color.RED, Color.BLACK);
    }

    public String toString() {
        return "Game Over";
    }

}
