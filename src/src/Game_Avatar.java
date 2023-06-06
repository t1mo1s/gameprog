import java.awt.*;

class Game_Avatar extends A_GameObject {

    Game_PhysicsSystem physicsSystem;

  public Game_Avatar(double x, double y)
  { super(x,y,0,200,15,25, new Color(96,96,255));
    this.isMoving = false;
  }
  public void moveLeft(double diffSeconds){
      super.moveLeft(diffSeconds);
      checkCollision();
      //hier dann noch collision detections berechnen
      //und bestimmen was passiert wenn collision
      //z.B. moveBack(); de

  }
  public void moveRight(double diffSeconds){
      super.moveRight(diffSeconds);
      checkCollision();

  }
  public void jump(double diffSeconds){
      super.jump(diffSeconds);
      checkCollision();
  }

  public void currentCoordinate(){
      System.out.println("X: " + super.x + " Y: " + super.y);
  }

  public int type() { return A_Const.TYPE_AVATAR; }

    public void checkCollision() {
        // Double Platform
        int doublePlatformX1 = 650;
        int doublePlatformY1 = 380 - 100;
        int doublePlatformWidth1 = 100;
        int doublePlatformHeight1 = 70;

        int doublePlatformX2 = 1000;
        int doublePlatformY2 = 70 + 100;
        int doublePlatformWidth2 = 100;
        int doublePlatformHeight2 = 70;

        // Triple Platform
        int triplePlatformX1 = doublePlatformX2;
        int triplePlatformY1 = doublePlatformY2;
        int triplePlatformWidth1 = doublePlatformWidth2;
        int triplePlatformHeight1 = doublePlatformHeight2;

        int triplePlatformX2 = 1700;
        int triplePlatformY2 = 70 + 170;
        int triplePlatformWidth2 = 100;
        int triplePlatformHeight2 = 70;

        int triplePlatformX3 = 2400;
        int triplePlatformY3 = 70 + 170;
        int triplePlatformWidth3 = 100;
        int triplePlatformHeight3 = 70;

        // Check collision with Double Platform
        if (super.x + super.width >= doublePlatformX1 && super.x <= doublePlatformX1 + doublePlatformWidth1 &&
                super.y + height >= doublePlatformY1 && super.y <= doublePlatformY1 + doublePlatformHeight1) {
            System.out.println("Kollision1");
            //passiert wenn kollision gibt
        }

        if (super.x + width >= doublePlatformX2 && super.x <= doublePlatformX2 + doublePlatformWidth2 &&
                super.y + height >= doublePlatformY2 && super.y <= doublePlatformY2 + doublePlatformHeight2) {
            System.out.println("Kollision2");
        }

        // Check collision with Triple Platform
        if (super.x + width >= triplePlatformX1 && super.x <= triplePlatformX1 + triplePlatformWidth1 &&
                super.y + height >= triplePlatformY1 && super.y <= triplePlatformY1 + triplePlatformHeight1) {
            System.out.println("Kollision3");
        }

        if (super.x + width >= triplePlatformX2 && super.x <= triplePlatformX2 + triplePlatformWidth2 &&
                super.y + height >= triplePlatformY2 && super.y <= triplePlatformY2 + triplePlatformHeight2) {
            System.out.println("Kollision4");
        }

        if (super.x + width >= triplePlatformX3 && super.x <= triplePlatformX3 + triplePlatformWidth3 &&
                super.y + height >= triplePlatformY3 && super.y <= triplePlatformY3 + triplePlatformHeight3) {
            System.out.println("Kollision5");
        }
    }
}


