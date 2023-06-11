import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

abstract class A_GameObject {
    protected double x, y;
    protected double alfa = 0;
    protected static A_PhysicsSystem physicsSystem;
    protected static A_World world;

    double playerSpeedX = 0; // Horizontale Geschwindigkeit des Spielers
    double playerSpeedY = 0; // Vertikale Geschwindigkeit des Spielers

    protected double speed = 0;
    protected int height, width;
    protected Color color;
    protected boolean isLiving = false;
    protected boolean isMoving = true;
    boolean isOnGround = false; // Gibt an, ob der Spieler auf dem Boden ist
    protected boolean isJumping = false; // Gibt an, ob der Spieler gerade springt

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
        y -= 5 * speed * 0.015;//diffSeconds;
    }

    abstract int type();

    static void setWorld(A_World w) {
        world = w;
    }

    static void setPhysicsSystem(A_PhysicsSystem ps) {
        physicsSystem = ps;
    }

}
