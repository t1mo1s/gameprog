import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

abstract class A_GameObject {
    protected double x, y;
    protected double alfa = 0;
    protected double speed = 0;
    protected int height, width;
    protected Color color;
    protected boolean isLiving = true;
    protected boolean isMoving = true;

    // destination the object shall move to,
    // old position etc
    private double destX, destY;
    private boolean hasDestination = false;

    protected boolean isJumping = false;
    private double xOld, yOld;
    //vertivcal Speed
    private double vSpeed = 100;

    //max jump height kommt später in A_Const
    private double maxJumpHeight = y + 250;


    // GameObjects sometimes call physics methods
    protected static A_World world;

    // construct GameObject
    public A_GameObject(double x_, double y_, double a_, double s_, int width_, int heigth_, Color color_) {
        x = x_;
        y = y_;
        xOld = x;
        yOld = y;
        alfa = a_;
        speed = s_;
        width = width_;
        height = heigth_;
        color = color_;
    }

    public void moveLeft(double diffSeconds) {
        x -= 2 * speed * diffSeconds;
    }

    public void moveRight(double diffSeconds) {
        x += 2 * speed * diffSeconds;
    }

    public void jump(double diffSeconds) {

        class JumpHelper extends TimerTask {

            @Override
            public void run() {
                if (y >= maxJumpHeight) {
                    y -= 4 * vSpeed * diffSeconds;
                } else {
                    cancel();
                    fall(diffSeconds);

                }

            }
        }

        Timer timer = new Timer();
        TimerTask jumpsTask = new JumpHelper();
        timer.schedule(jumpsTask, 0, 25);

    }


    public void fall(double diffSeconds) {
        class FallHelper extends TimerTask {

            @Override
            public void run() {
                if (y <= A_Const.WORLD_HEIGHT - 70 - 25 - 1) {
                    y += 4 * vSpeed * diffSeconds;
                } else {
                    isJumping = false;
                    cancel();
                }
            }
        }

        Timer timer = new Timer();
        TimerTask fallTask = new FallHelper();
        timer.schedule(fallTask, 0, 20);

    }

    abstract int type();

    static void setWorld(A_World w) {
        world = w;
    }

}
