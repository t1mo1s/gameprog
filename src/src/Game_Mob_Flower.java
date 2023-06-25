import java.awt.*;

public class Game_Mob_Flower extends A_GameObject {

    //TODO: ich glaub um die SpawnZeit länger zu halten sollte man einfach die SpawnDist länger machen,
    // dann "läuft er tiefer was länger dauert.

    /**
     * der Mob is ja 20pxl lang, also sollte er MINDESTENS ein pxl tiefer sein, damit der Player kontakt mit dem boden hat
     * und nicht das der mob mit dem boden Flush is und der player andauernd wegen den MOb stirbt
     */
    double spawnDist = 21;

    double startPosY;
    boolean movingDown = true;

    public Game_Mob_Flower(double x, double y) {
        super(x, y - 20, 0, 40, 15, 20, new Color(241, 6, 6));
        startPosY = y;
    }


    public void move(double diffSeconds) {
        if(movingDown && y < startPosY + height){
            //height = (int) (height - 4 * diffSeconds);
            y += 10 * diffSeconds;
            if(y >= startPosY + height){
                movingDown = false;
            }
        }else if(!movingDown && y > startPosY - height){
            long last = System.currentTimeMillis();
            y -= 20 * diffSeconds;
            if(y <= startPosY - height){
                long current = System.currentTimeMillis();
                while(current - last < 500){
                    current = System.currentTimeMillis();
                }
                movingDown = true;
            }
        }

    }


    @Override
    int type() {
        return A_Const.TYPE_FLOWERMOB;//A_Const.TYPE_MOB;
    }

}
