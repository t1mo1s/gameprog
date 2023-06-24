import java.awt.*;

public class Game_Ground extends A_GameObject {
    public Game_Ground(double x, double y, int width, int height) {
        super(x, y, 0, 0, width, height, new Color(108, 103, 103));
    }

    public Game_Ground(double x, double y, int width, int height, Color color) {
        super(x, y, 0, 0, width, height, color);
    }

    @Override
    int type() {
        return A_Const.TYPE_GROUND;
    }
}