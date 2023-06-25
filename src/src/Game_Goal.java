import java.awt.*;

public class Game_Goal extends A_GameObject {

    public Game_Goal(int diff) {
        super(A_Const.WORLD_WIDTH - 200, A_Const.WORLD_HEIGHT - diff, 0, 0, 200, 10, new Color(108, 185, 90), new Color(34, 86, 20));
    }

    @Override
    int type() {
        return A_Const.TYPE_GOAL;
    }
}
