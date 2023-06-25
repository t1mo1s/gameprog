import java.awt.*;

public class Game_Ground extends A_GameObject {
    public Game_Ground(double x, double y, int width, int height) {
        super(x, y, 0, 0, width, height, new Color(108, 103, 103), Color.BLACK);
    }

    public Game_Ground(double x, double y, int width, int height, Color color) {
        super(x, y, 0, 0, width, height, color, Color.BLACK);
    }
    public Game_Ground(double x, double y, int width, int height, Color color, Color borderColor) {
        super(x, y, 0, 0, width, height, color, borderColor);
    }

    @Override
    int type() {
        return A_Const.TYPE_GROUND;
    }
}