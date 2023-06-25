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
        super(x, y - 20, 0, 40, 15, 20, new Color(241, 6, 6), Color.black);
    }


    public void move(double diffSeconds) {
        if (movingDown && y > startPosY - spawnDist) {
            super.moveLeft(diffSeconds);

        } else if (movingDown && y <= startPosY - spawnDist) {
            movingDown = false;
        }

        if (!movingDown && y < startPosY + spawnDist) {
            super.moveRight(diffSeconds);
        } else if (!movingDown && y >= startPosY + spawnDist) {
            movingDown = true;
        }
    }


    @Override
    int type() {
        return 1;//A_Const.TYPE_MOB;
    }

}
