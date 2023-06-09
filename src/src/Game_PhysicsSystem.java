import java.awt.*;

class Game_PhysicsSystem extends A_PhysicsSystem {

  Game_PhysicsSystem(A_World w) {
    super(w);
  }


  //
  // collisions for circle Objects only...
  //
  public A_GameObjectList getCollisions(A_GameObject object) {
    A_GameObjectList result = new A_GameObjectList();

    int len = world.gameObjects.size();
    for (int i = 0; i < len; i++) {
      A_GameObject obj2 = world.gameObjects.get(i);

      // an object doesn't collide with itself
      if (obj2 == object) continue;

      // check if they touch each other
      //implement touch check here
      if (obj2.type() == A_Const.TYPE_GROUND) {
        double touchInterfaceY = obj2.y - 26;
        double touchInterfaceX1 = obj2.x;
        double touchInterfaceX2 = obj2.x + obj2.width;
        double touchInterfaceY2 = obj2.y + obj2.height;
        if (object.y < touchInterfaceY2 && object.y >= touchInterfaceY && object.x >= touchInterfaceX1 && object.x <= touchInterfaceX2) {
          result.add(obj2);
          System.out.println("touch with ground " + obj2.y);
          object.y = touchInterfaceY;
          if (object.isJumping) {
            object.isJumping = false;
          }

        }
      }
    }
    return result;


  }
}