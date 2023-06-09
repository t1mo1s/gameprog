import java.awt.*;
import java.awt.image.ImageObserver;

interface A_GraphicSystem {
    // prepare to draw a new Screen
    void clear();

    // draw ONE GameObject on the Screen
    void draw(A_GameObject rect);

    // draw ONE TextObject on the Screen
    void draw(A_TextObject obj);

    void drawImage(Image img, int x, int y, int width, int height, ImageObserver observer);

    // display the completed Screen
    void redraw();

    // set world
    void setWorld(A_World world);
}
