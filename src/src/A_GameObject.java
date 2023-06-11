import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

abstract class A_GameObject {

    protected double x, y;
    protected double alfa = 0;
    protected static A_PhysicsSystem physicsSystem;
    protected double speed = 0;
    protected int height, width;
    protected Color color;
    protected boolean isLiving = true;
    protected boolean isMoving = true;
    public boolean isOnGround = false;
    protected boolean isJumping = false;
    double playerSpeedX = 0; // Horizontale Geschwindigkeit des Spielers
    double playerSpeedY = 0; // Vertikale Geschwindigkeit des Spielers
    protected static A_World world;

    // construct GameObject
    public A_GameObject(double x_, double y_, double a_, double s_, int width_, int heigth_, Color color_) {
        x = x_;
        y = y_;

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
        y -= 6 * speed * diffSeconds;
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

    abstract int type();

    static void setWorld(A_World w) {
        world = w;
    }

    static void setPhysicsSystem(A_PhysicsSystem ps){
        physicsSystem = ps;
    }
}
