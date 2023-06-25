import java.awt.*;

class Game_Avatar extends A_GameObject {
    public Game_Avatar(double x, double y) {
        super(x, y, 0, 200, 15, 25, new Color(96, 96, 255), Color.BLACK);
        this.isMoving = false;
    }

    public void moveLeft(double diffSeconds) {
        super.moveLeft(diffSeconds);
    }

    public void moveRight(double diffSeconds) {
        super.moveRight(diffSeconds);
    }

    public void jump(double diffSeconds) {
        super.jump(diffSeconds);
    }

    public int type() {
        return A_Const.TYPE_AVATAR;
    }
}