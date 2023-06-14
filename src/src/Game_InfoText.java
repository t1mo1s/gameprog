import java.awt.*;

class Game_InfoText extends A_TextObject {


    public Game_InfoText(int x, int y) {
        super(x, y, 30, new Color(62, 123, 229), new Color(29, 47, 108));
    }

    public String toString() {
        return "A/D for Movement | W/Space to Jump | R to Restart | M to Open/Close the Menu ";
    }

}
