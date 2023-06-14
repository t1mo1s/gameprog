import java.awt.*;


abstract class A_TextObject {
    protected static A_World world;

    // yes, public :(
    protected int x, y, fontSize;
    protected Color textColor, strokeColor;

    //
    public A_TextObject(int x, int y, int fontSize, Color textColor, Color strokeColor) {
        this.x = x;
        this.y = y;
        this.fontSize = fontSize;
        this.textColor = textColor;
        this.strokeColor = strokeColor;
    }

    public abstract String toString();

    protected static void setWorld(A_World w) {
        world = w;
    }
}
