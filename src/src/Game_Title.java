import java.awt.*;

class Game_Title extends A_TextObject {

    public Game_Title(int x, int y) {
        super(x - 100, y - 50, 100, new Color(128, 130, 154), new Color(30, 31, 34));
    }

    public String toString() {
        return "Daring-Dash";
    }
}
