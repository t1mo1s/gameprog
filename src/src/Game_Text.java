import java.awt.*;
import javax.swing.*;

public class Game_Text extends JPanel {
    /**GEHT NICHT!! EINFACH INGORIEREN*/

    private int x;
    private int y;
    private String text;
    private Color textColor;
    private Color strokeColor;
    private int fontSize;

    public Game_Text(int x, int y, String text, int fontSize, Color textColor, Color strokeColor) {
        this.x = x;
        this.y = y;
        this.text = text;
        this.textColor = textColor;
        this.strokeColor = strokeColor;
        this.fontSize = fontSize;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        Font font = new Font("Arial", Font.BOLD, fontSize);
        g2d.setFont(font);

        // Set the stroke for the outline
        Stroke outlineStroke = new BasicStroke(2f);
        g2d.setStroke(outlineStroke);

        // Draw the outline
        g2d.setColor(strokeColor);
        g2d.drawString(text, x, y);

        // Set the color for the text
        g2d.setColor(textColor);
        g2d.drawString(text, x, y);
    }
}