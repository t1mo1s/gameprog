import java.awt.*;

class Game_Avatar extends A_GameObject {
  public Game_Avatar(double x, double y)
  { super(x,y,0,200,15,25, new Color(96,96,255));
    this.isMoving = false;
  }
  public void moveLeft(double diffSeconds){
      super.moveLeft(diffSeconds);
      //hier dann noch collision detections berechnen
      //und bestimmen was passiert wenn collision
      //z.B. moveBack();
  }
  public void moveRight(double diffSeconds){
      super.moveRight(diffSeconds);
  }
  public void jump(double diffSeconds){
      super.jump(diffSeconds);
      A_GameObjectList collisions = physicsSystem.getCollisions(this);
        for(int i=0; i<collisions.size(); i++)
        {
            A_GameObject obj = collisions.get(i);
            if(obj.type()==A_Const.TYPE_GROUND)
            {
                this.y = obj.y ;
                this.playerSpeedY = 0;
                isJumping = false;
                isOnGround = true;
                System.out.println("touch with ground " + obj.y);
            }
        }
  }

  public int type() { return A_Const.TYPE_AVATAR; }
}
