import java.awt.*;

public class Gam20_Goal extends A_GameObject {


    public Gam20_Goal(double x, double y, int width_, int height_) {
        super(x, y, 0, 0, width_, height_, new Color(108, 185, 90));
    }

    @Override
    int type() {
        return A_Const.TYPE_GROUND;
    }
}
