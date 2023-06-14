import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.awt.SystemColor.text;

class B_Panel extends JPanel implements A_GraphicSystem {
    // constants
    private static final long serialVersionUID = 1L;

    // InputSystem is an external instance
    private B_InputSystem inputSystem = new B_InputSystem();
    private A_World world = null;


    // GraphicsSystem variables
    //
    private GraphicsConfiguration graphicsConf = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
    private BufferedImage imageBuffer;
    private Graphics graphics;

    public B_Panel() {

        //this.setSize(A_Const.WORLDPART_WIDTH,A_Const.WORLDPART_HEIGHT);
        this.setSize(A_Const.WORLDPART_WIDTH, A_Const.WORLD_HEIGHT);

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
        //graphics.fillRect(40, 100,A_Const.WORLDPART_WIDTH,A_Const.WORLDPART_HEIGHT);
        graphics.fillRect(0, 0, A_Const.WORLDPART_WIDTH, A_Const.WORLD_HEIGHT);
    }


    public final void draw(A_GameObject rect) {
    /*
	int x = (int)(dot.x-dot.radius-world.worldPartX);
	int y = (int)(dot.y-dot.radius-world.worldPartY);
	int d = (int)(dot.radius*2);
	
	graphics.setColor(dot.color);
	graphics.fillOval(x, y, d, d);
	graphics.setColor(Color.DARK_GRAY);
	graphics.drawOval(x,y,d,d);

     */
        int x = (int) (rect.x - world.worldPartX);
        int y = (int) rect.y;
        graphics.setColor(rect.color);
        graphics.fillRect(x, y, rect.width, rect.height);
        graphics.drawRect(x, y, rect.width, rect.height);
    }

    public void draw(A_TextObject text) {
        Font font = new Font("Retro Gaming", Font.PLAIN, text.fontSize);

        graphics.setFont(font);
        //strokeSettings
        drawStroke(text.strokeColor, text.toString(), text.x, text.y, text.fontSize);

        //Text
        graphics.setColor(text.textColor);
        graphics.drawString(text.toString(), (int) text.x, (int) text.y);
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

    //can be deleted
    public final void draw(A_TextObject text, int fontSize, String fontName, Color textColor, Color strokeColor) {

        fontName = "Arial";

        Graphics2D g2d = (Graphics2D) graphics;

        g2d.setFont(new Font(fontName, Font.PLAIN, fontSize));

        // Set the stroke for the outline
        Stroke outlineStroke = new BasicStroke(2f);
        g2d.setStroke(outlineStroke);

        // Draw the outline
        g2d.setColor(strokeColor);
        g2d.drawString(text.toString(), (int) text.x + 1, (int) text.y + 1);

        // Set the color for the text
        g2d.setColor(textColor);
        g2d.drawString(text.toString(), (int) text.x + 1, (int) text.y + 1);
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

