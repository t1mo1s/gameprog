import java.awt.*;

public class Gam20_Spike extends A_GameObject {

    public Gam20_Spike(double x, double y, int width_, int heigth_) {
        super(x, y, 0, 0, width_, heigth_, new Color(82, 82, 82));
    }
    @Override
    int type() {
        return A_Const.TYPE_GROUND;
    }
}
