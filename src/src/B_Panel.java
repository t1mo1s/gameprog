import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.io.Serial;


class B_Panel extends JPanel implements A_GraphicSystem {
    // constants
    @Serial
    private static final long serialVersionUID = 1L;

    // InputSystem is an external instance
    private final B_InputSystem inputSystem = new B_InputSystem();
    private A_World world = null;
    private BufferedImage imageBuffer;
    private Graphics graphics;

    public B_Panel() {

        this.setSize(A_Const.WORLDPART_WIDTH, A_Const.WORLD_HEIGHT);

        // GraphicsSystem variables
        GraphicsConfiguration graphicsConf = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        imageBuffer = graphicsConf.createCompatibleImage(this.getWidth(), this.getHeight());

        graphics = imageBuffer.getGraphics();

        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/fonts/DePixelKlein.ttf"));
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(customFont);

            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/fonts/Retro Gaming.ttf"));
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(customFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        // initialize Listeners
        this.addMouseListener(inputSystem);
        this.addMouseMotionListener(inputSystem);
        this.addKeyListener(inputSystem);
    }

    public void clear() {
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillRect(0, 0, A_Const.WORLDPART_WIDTH, A_Const.WORLD_HEIGHT);
    }

    public final void draw(A_GameObject rect) {
        //set relative to worldPart
        int x = (int) (rect.x - world.worldPartX);
        int y = (int) rect.y;

        drawObjBorder(x, y, rect.width, rect.height);

        graphics.setColor(rect.color);
        graphics.fillRect(x, y, rect.width, rect.height);
        graphics.drawRect(x, y, rect.width, rect.height);
    }

    private void drawObjBorder(int x, int y, int width, int height) {
        graphics.setColor(Color.BLACK);

        int border = 2;
        graphics.drawRect(x - border / 2, y - border / 2, width + border, height + border);
    }

    public void draw(A_TextObject text) {
        Font font = new Font("Retro Gaming", Font.PLAIN, text.fontSize);

        graphics.setFont(font);
        //strokeSettings
        drawStroke(text.strokeColor, text.toString(), text.x, text.y, text.fontSize);

        //Text
        graphics.setColor(text.textColor);
        graphics.drawString(text.toString(), text.x, text.y);
    }

    private void drawStroke(Color strokeColor, String txt, int x, int y, int fontsize) {
        graphics.setColor(strokeColor);
        int pos;
        if (fontsize >= 100) {
            pos = 8;
        } else {
            pos = fontsize / 10;
        }

        int[] positions = {-pos, 0, pos};

        for (int offsetX : positions) {
            for (int offsetY : positions) {
                if (offsetX != 0 || offsetY != 0) {
                    graphics.drawString(txt, x + offsetX, y + offsetY);
                }
            }
        }
    }

    public void drawImage(Image img, int x, int y, int width, int height, ImageObserver observer) {
        graphics.drawImage(img, x, y, width, height, observer);

    }

    public void redraw() {
        this.getGraphics().drawImage(imageBuffer, 0, 0, this);
    }

    public final A_InputSystem getInputSystem() {
        return inputSystem;
    }

    public final void setWorld(A_World world_) {
        this.world = world_;
    }
}