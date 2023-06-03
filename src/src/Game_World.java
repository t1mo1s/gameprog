class Game_World extends A_World
{
  private double timePassed = 0;
  private double timeSinceLastShot = 0;
  
  // for grenades
  private int grenades = 5;
  private Game_CounterGrenades counterG;
  private Game_Counter counterZ;
  private Game_HelpText helpText;
  private double spawnGrenade = 0;
  
  private double lifeHelpText = 10.0;
	
  protected void init()
  {
        // add the Avatar
        //A_Const.WORLD_Height-70-25 um avatarauf boden zu setzen => -70 wegen ground height und -25 wegen avatar height
	    avatar = new Game_Avatar(30,A_Const.WORLD_HEIGHT-70-25);
        gameObjects.add(avatar);


	    // set WorldPart position
	    worldPartX = 0;

	
      //add ground
      gameObjects.add(new Game_Ground(0,A_Const.WORLD_HEIGHT-70,A_Const.WORLD_WIDTH, 70));
      //add roof
      gameObjects.add(new Game_Ground(0,0,A_Const.WORLD_WIDTH,70));

      //for testing
      gameObjects.add(new Game_Ground(2000, 100,50,30));
      gameObjects.add(new Game_Ground(1200, 200,77,40));
      gameObjects.add(new Game_Ground(2500,170, 100,60));
      gameObjects.add(new Game_Ground(3400, 200, 66,90));
  }
	
  protected void processUserInput(A_UserInput userInput, double diffSeconds) {
      // distinguish if Avatar shall move or shoots
      int button = userInput.mouseButton;

      if(userInput.keyMap.get('a') == true){
          if(avatar.x >= 0){
              avatar.moveLeft(diffSeconds);
          }

      }
      if(userInput.keyMap.get('d') == true){
          avatar.moveRight(diffSeconds);
      }
      if(userInput.keyMap.get(' ')==true && !avatar.isJumping){
          avatar.isJumping = true;
          avatar.jump(diffSeconds);
      }
  }
}
