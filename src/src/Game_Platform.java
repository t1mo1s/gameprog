import java.awt.*;

public class Game_Platform extends A_GameObject {

    public Game_Platform(double x, double y, int width) {
        super(x, y, 1, 0, width, 25, new Color(53, 153, 192));
    }

    public Game_Platform(double x, double y, int width, int height, Color color) {
        super(x, y, 0, 0, width, height, color);
    }

    @Override
    int type() {
        return A_Const.TYPE_GROUND;
    }
}
