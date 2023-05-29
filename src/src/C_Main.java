import javax.swing.*;

public class C_Main extends JFrame {

    private static int width = 1000;
    private static int height = 400;
    private static C_Main instance;
    private C_Board activeBoard;

    public C_Main() {
        instance = this;

        activeBoard = new C_Board();
        add(activeBoard);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(C_Main.width, C_Main.height);
        setLocationRelativeTo(null);

        setTitle("JumpNRun - GameProg INF");

        setResizable(false);
        setVisible(true);
    }

    public static C_Main getInstance() {
        return instance;
    }

    public int getWidth() {
        return C_Main.width;
    }

    public int getHeight() {
        return C_Main.height;
    }

    public static void main(String[] args) {
        new C_Main();
    }
}