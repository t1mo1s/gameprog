import java.awt.*;

public class Gam20_Ground extends A_GameObject {


    public Gam20_Ground(double x, double y, int width_, int heigth_) {
        super(x, y, 0, 0, width_, heigth_, new Color(100, 0, 0));
    }

    @Override
    int type() {
        return A_Const.TYPE_GROUND;
    }
}
