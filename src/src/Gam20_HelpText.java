
// (c) Thorsten Hasbargen


import java.awt.*;

class Gam20_HelpText extends A_TextObject {
    public Gam20_HelpText(int x, int y) {
        super(x, y, new Color(0, 120, 255, 60));
    }

    public String toString() {
        return "MOVE:Mouse left      SHOOT:Mouse right      " +
                "Grenade:Space bar     END: Escape";
    }

}
