// (c) Thorsten Hasbargen


import javax.swing.*;
import java.io.Serial;

class B_Frame extends JFrame implements A_Frame {
    // ...ok...
    @Serial
    private static final long serialVersionUID = 2L;

    private B_Panel panel = null;

    public B_Frame() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //this.setSize(A_Const.WORLD_PART_WIDTH+2,A_Const.WORLD_PART_HEIGHT+2);
        this.setSize(A_Const.WORLD_WIDTH, A_Const.WORLD_HEIGHT);

        this.setAlwaysOnTop(true);
        this.setUndecorated(true);

        this.setResizable(true);

        panel = new B_Panel();

        // needed for Keyboard input !!!
        panel.setFocusable(true);
        panel.requestFocusInWindow();

        this.setContentPane(panel);
    }

    public void displayOnScreen() {
        validate();
      /*
      pack();

       */
        //sets the screen into the middle
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
