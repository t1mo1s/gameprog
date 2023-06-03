import java.awt.*;

public class Gam20_Platform extends A_GameObject {

    public Gam20_Platform(double x, double y ) {
        super(x, y, 0, 0, 75, 35, new Color(52, 19, 21));
    }

    @Override
    int type() {
        return A_Const.TYPE_GROUND;
    }
}
