public class C_Building extends C_Item {

    public C_Building(double x, double y, double width, double height) {
        if (x == 0 && y == 0 && width == 0 && height == 0) {
            x = C_Board.getInstance().getWidth() - 1;
            y = C_Board.getRandom(200, 250);
            width = C_Board.getRandom(500, 1000);
            height = 300;
        }
        this.setRect(x, y, width, height);
        if (C_Board.getRandom(0,5) == 4) {
            this.setRotation(C_Board.getRandom(5,10));
        }
    }
}