import java.awt.*;

public class Game_Platform extends A_GameObject {

    public Game_Platform(double x, double y, int width) {
        super(x, y, 1, 0, width, 35, new Color(52, 19, 21));
    }

    @Override
    int type() {
        return A_Const.TYPE_GROUND;
    }
}
