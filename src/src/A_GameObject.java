import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

abstract class A_GameObject {
    protected double x, y;
    protected double alfa = 0;

    protected double gravity;

    protected static A_PhysicsSystem physicsSystem;
    protected static A_World         world;

    double playerSpeedX = 0; // Horizontale Geschwindigkeit des Spielers
    double playerSpeedY = 0; // Vertikale Geschwindigkeit des Spielers

    protected double speed = 0;
    protected int height, width;
    protected Color color;
    protected boolean isLiving = false;
    protected boolean isMoving = true;

    // destination the object shall move to,
    // old position etc
    private double destX, destY;
    private boolean hasDestination = false;
    boolean isOnGround = false; // Gibt an, ob der Spieler auf dem Boden ist

    protected boolean isJumping = false;
    private double xOld, yOld;
    //vertivcal Speed
    private double vSpeed = 100;

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    //max jump height kommt später in A_Const
    double maxJumpHeight = y + 150;

    public void setY(double y) {
        this.y = y;
    }

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
        x -=  3*speed * diffSeconds;
    }

    public void moveRight(double diffSeconds) {
        x += 3* speed * diffSeconds;
    }


    public void jump(double diffSeconds) {
        y -= 8d*speed * diffSeconds;
    }

    public void jump2(double diffSeconds, double dir) {
        y += playerSpeedY * diffSeconds;

    }


    // Helper method to calculate the y-coordinate on a Bézier curve for a given parameter value t
    private double calculateBezierPoint(double t, double[] controlPoints) {
        int n = controlPoints.length - 1;
        double yBezier = 0.0;

        for (int i = 0; i <= n; i++) {
            double coefficient = binomialCoefficient(n, i) * Math.pow(1 - t, n - i) * Math.pow(t, i);
            yBezier += coefficient * controlPoints[i];
        }

        return yBezier;
    }

    // Helper method to calculate the binomial coefficient (n choose k)
    private int binomialCoefficient(int n, int k) {
        if (k == 0 || k == n) {
            return 1;
        } else {
            return binomialCoefficient(n - 1, k - 1) + binomialCoefficient(n - 1, k);
        }
    }



    public void fall(double diffSeconds) {
        class FallHelper extends TimerTask {

            @Override
            public void run() {
                if (y < A_Const.WORLD_HEIGHT - 70 - 25 - 1) {
                    y += 2 * vSpeed * diffSeconds;

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

    static void setPhysicsSystem(A_PhysicsSystem ps){physicsSystem=ps;}

}
