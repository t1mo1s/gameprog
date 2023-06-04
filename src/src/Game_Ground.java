import java.awt.*;

public class Game_Ground extends A_GameObject{


    public Game_Ground(double x, double y, int width_, int height_, Color color) {
        super(x, y, 0, 0, width_, height_, color);
    }



    @Override
    int type() {
        return A_Const.TYPE_GROUND;
    }
}
