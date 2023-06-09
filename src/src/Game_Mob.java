import java.awt.*;

public class Game_Mob extends A_GameObject {

    public Game_Mob(double x, double y) {
        super(x, y - 20, 0, 0, 15, 20, new Color(164, 109, 12));
    }

    @Override
    int type() {
        return A_Const.TYPE_GROUND;
    }
}
