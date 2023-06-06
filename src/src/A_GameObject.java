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
    private double maxJumpHeight = y + 150;


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
        // Define the control points of the Bézier curve
        double[] controlPoints = {y, maxJumpHeight, maxJumpHeight, y};

        class JumpHelper extends TimerTask {
            private double t = 0.0;

            @Override
            public void run() {
                if (t <= 1.0) {
                    // Calculate the y-coordinate using the Bézier curve formula
                    double yBezier = calculateBezierPoint(t, controlPoints);

                    // Update the y-coordinate
                    y = yBezier;

                    t += 0.01; // Increase the parameter value for each iteration
                } else {
                    cancel();
                    fall(diffSeconds);
                }
            }
        }

        Timer timer = new Timer();
        TimerTask jumpsTask = new JumpHelper();
        timer.schedule(jumpsTask, 0, 5);
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

}
