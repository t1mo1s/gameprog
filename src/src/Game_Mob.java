import java.awt.*;

public class Game_Mob extends A_GameObject {

    double walkingDist;
    double startPosX;
    boolean movingLeft = true;

    public Game_Mob(double x, double y, double wd) {
        super(x, y - 20, 0, 40, 15, 20, new Color(164, 109, 12));
        walkingDist = wd;
        startPosX = x;
    }

    public void move(double diffSeconds){
        if(movingLeft && x > startPosX - walkingDist){
            super.moveLeft(diffSeconds);

        }else if(movingLeft && x <= startPosX - walkingDist){
           movingLeft = false;
        }

        if(!movingLeft && x < startPosX + walkingDist){
            super.moveRight(diffSeconds);
        }else if(!movingLeft && x >= startPosX + walkingDist){
            movingLeft = true;
        }

    }

    @Override
    int type() {
        return A_Const.TYPE_MOB;
    }
}
