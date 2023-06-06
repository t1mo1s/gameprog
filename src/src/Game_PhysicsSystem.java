class Game_PhysicsSystem extends A_PhysicsSystem
{
	
  Game_PhysicsSystem(A_World w)
  { super(w);
  }

  //
  // collisions for circle Objects only...
  //
  public A_GameObjectList getCollisions(A_GameObject object)
  {
    A_GameObjectList result = new A_GameObjectList();
    
    int len = world.gameObjects.size();
    for(int i=0; i<len; i++)
    {
      A_GameObject obj2 = world.gameObjects.get(i);
      
      // an object doesn't collide with itself
      if(obj2==object) continue;
      
      // check if they touch each other
      double dist = object.width+obj2.width;
      double dx   = object.x-obj2.x;
      double dy   = object.y-obj2.y;
      
      if(dx*dx+dy*dy < dist*dist) {
        result.add(obj2);
      }
    }
    
    return result;
  }

  @Override
  public void checkCollision()  {
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
    if (object.x + object.width >= doublePlatformX1 && object.x <= doublePlatformX1 + doublePlatformWidth1 &&
            object.x + object.height >= doublePlatformY1 && object.y <= doublePlatformY1 + doublePlatformHeight1) {
      System.out.println("Kollision1");
    }

    if (object.x + object.width >= doublePlatformX2 && object.x <= doublePlatformX2 + doublePlatformWidth2 &&
            object.x + object.height >= doublePlatformY2 && object.x <= doublePlatformY2 + doublePlatformHeight2) {
      System.out.println("Kollision2");
    }

    // Check collision with Triple Platform
    if (object.x + object.width >= triplePlatformX1 && object.x <= triplePlatformX1 + triplePlatformWidth1 &&
            object.y + object.height >= triplePlatformY1 && object.y <= triplePlatformY1 + triplePlatformHeight1) {
      System.out.println("Kollision3");
    }

    if (object.x + object.width >= triplePlatformX2 && object.x <= triplePlatformX2 + triplePlatformWidth2 &&
            object.y + object.height >= triplePlatformY2 && object.y <= triplePlatformY2 + triplePlatformHeight2) {
      System.out.println("Kollision4");
    }

    if (object.x + object.width >= triplePlatformX3 && object.x <= triplePlatformX3 + triplePlatformWidth3 &&
            object.y + object.height >= triplePlatformY3 && object.y <= triplePlatformY3 + triplePlatformHeight3) {
      System.out.println("Kollision5");
    }
  }
}