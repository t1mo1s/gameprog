import javax.swing.*;
import java.io.Serial;

class B_Frame extends JFrame implements A_Frame {
    // ...ok...
    @Serial
    private static final long serialVersionUID = 2L;

    private final B_Panel panel = new B_Panel();

    public B_Frame() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(A_Const.WORLDPART_WIDTH, A_Const.WORLD_HEIGHT);

        this.setTitle("DaringDash");

        ImageIcon icon = new ImageIcon("src/assets/img/LOGO.png");
        this.setIconImage(icon.getImage());

        this.setResizable(false);


        // needed for Keyboard input !!!
        panel.setFocusable(true);
        panel.requestFocusInWindow();

        this.setContentPane(panel);
    }

    public void displayOnScreen() {
        validate();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public A_GraphicSystem getGraphicSystem() {
        return panel;
    }

    public A_InputSystem getInputSystem() {
        return panel.getInputSystem();
    }

}
