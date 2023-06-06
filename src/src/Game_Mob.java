import java.awt.*;

public class Game_Mob extends A_GameObject {

    public Game_Mob(double x, double y, int width_, int heigth_) {
        super(x, y, 0, 0, width_, heigth_, new Color(164, 109, 12));
    }

    @Override
    int type() {
        return A_Const.TYPE_GROUND;
    }
}
