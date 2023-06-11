class Game_PhysicsSystem extends A_PhysicsSystem {

  Game_PhysicsSystem(A_World w) {
    super(w);
  }

  public A_GameObjectList getCollisions(A_GameObject object) {
    A_GameObjectList result = new A_GameObjectList();

    int len = world.gameObjects.size();
    for (int i = 0; i < len; i++) {
      A_GameObject obj2 = world.gameObjects.get(i);

      // an object doesn't collide with itself
      if (obj2 == object) continue;

      // check if they touch each other
      //implement touch check here
      //decide what happens if avatar is specific object

      //calc edges of ground
      double x1 =obj2.x;
      double x2 =obj2.x + obj2.width;;
      double y1 =obj2.y;
      double y2 =obj2.y+obj2.height;

      //calc edges of avatar
      double avX1 = object.x ;
      double avX2 = object.x + object.width;
      double avY1 = object.y;
      double avY2 = object.y + object.height;
      boolean collisionOccurred = false;

      //check if avatar is in ground
      if ((avY2 >= y1 && avY2 <= y2) && (avX1 < x2 && avX2 > x1)) {
        //avatar check the bottom edge of the ground
        object.y = y1 - object.height;
        object.playerSpeedY = 0;
        object.isJumping = false;
        object.isOnGround = true;
        collisionOccurred = true;
      } else if ((avY1 <= y2 && avY1 >= y1) && (avX1 < x2 && avX2 > x1)) {
        // avatar check the top edge of the ground
        object.y = y2;
        object.playerSpeedY = 0;
        collisionOccurred = true;
      } else if ((avX2 > x1 && avX2 <= x1 + 5) && (avY1 < y2 && avY2 > y1)) {
        // avatar check the left edge of the ground
        object.x = x1 - object.width;
        object.playerSpeedX = 0;
        collisionOccurred = true;
      } else if ((avX1 < x2 && avX1 >= x2 - 5) && (avY1 < y2 && avY2 > y1)) {
        // avatar check the right edge of the ground
        object.x = x2;
        object.playerSpeedX = 0;
        collisionOccurred = true;
      }

      if (collisionOccurred) {
        result.add(obj2);
      }
    }
    return result;
  }
}