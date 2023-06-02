import java.awt.*;

public class Gam20_Platform extends A_GameObject {


    public Gam20_Platform(double x, double y, int width_, int heigth_) {
        super(x, y, 0, 0, width_, heigth_, new Color(56, 20, 20));
    }

    @Override
    int type() {
        return A_Const.TYPE_GROUND;
    }
}
