
// (c) Thorsten Hasbargen


import java.util.ArrayList;

class Gam20_World extends A_World
{
  private double timePassed = 0;
  private double timeSinceLastShot = 0;
  
  // for grenades
  private int grenades = 5;
  private Gam20_CounterGrenades counterG;
  private Gam20_Counter         counterZ;
  private Gam20_HelpText        helpText;
  private double spawnGrenade = 0;
  
  private double lifeHelpText = 10.0;
	
  protected void init()
  {
        // add the Avatar
        //A_Const.WORLD_Height-70-25 um avatar auf boden zu setzen => -70 wegen ground height und -25 wegen avatar height
	    avatar = new Gam20_Avatar(30,A_Const.WORLD_HEIGHT-70-25);
        gameObjects.add(avatar);
	
	    // set WorldPart position
	    worldPartX = 1500;
	    worldPartY = 1500;
	
      //add ground
      gameObjects.add(new Gam20_Ground(0,A_Const.WORLD_HEIGHT-70-1,A_Const.WORLD_WIDTH, 70));
      //add roof
      gameObjects.add(new Gam20_Ground(0,0,A_Const.WORLD_WIDTH,70));
      //




    // add one zombie



  }
	
  protected void processUserInput(A_UserInput userInput, double diffSeconds) {
      // distinguish if Avatar shall move or shoots

      //n-key rollover => geht aber noch nich so wie gewünscht
      ArrayList<Character> inputs = new ArrayList<>();
      if(userInput.isKeyEvent){
          inputs.add(userInput.keyPressed);
      }

      if(!inputs.isEmpty()){
          for(int i = 0; i < inputs.size(); i++){
              if(inputs.get(i) == 'a'){
                  avatar.moveLeft(diffSeconds);
              }else if(inputs.get(i) == 'd'){
                  avatar.moveRight(diffSeconds);
              }else if(inputs.get(i) == ' ' && !avatar.isJumping){
                  avatar.isJumping = true;
                  avatar.jump(diffSeconds);
              }
          }
      }
      //
      //handle key events
      /*
      if(userInput.isKeyEvent){
          if(userInput.keyPressed == 'a'){
              avatar.moveLeft(diffSeconds);
          }else if(userInput.keyPressed == 'd'){
              avatar.moveRight(diffSeconds);
          }else if(userInput.keyPressed == ' ' && !avatar.isJumping){
              avatar.isJumping = true;
              avatar.jump(diffSeconds);
              //avatar.fall(diffSeconds);
          }
      }

       */

      //
      // Mouse still pressed?
      //
      /*
	if(userInput.isMousePressed && button==3)
	{
	  // only 1 shot every ... seconds:
      timeSinceLastShot += diffSeconds;
      if(timeSinceLastShot > 0.2)
      {
    	timeSinceLastShot = 0;
    	
        Gam20_Shot shot = new Gam20_Shot(
          avatar.x,avatar.y,userInput.mouseMovedX+worldPartX,userInput.mouseMovedY+worldPartY);		
        this.gameObjects.add(shot);    	  
      }
	}

	//
	// Keyboard events
	//
	if(userInput.isKeyEvent)
	{
	  if(userInput.keyPressed==' ')
	  { throwGrenade(userInput.mouseMovedX+worldPartX,userInput.mouseMovedY+worldPartY);
	  }
	  else if(userInput.keyPressed==(char)27)
	  { System.exit(0);
	  }
	}
  }

       */
  
   
  /*private void throwGrenade(double x, double y)
  {
	if(grenades<=0) return;  
	  
	// throw grenade
    for(int i=0; i<2000; i++)
    {
      double alfa = Math.random()*Math.PI*2;
      double speed = 50+Math.random()*200;
      double time  = 0.2+Math.random()*0.4;
      Gam20_Shot shot = new Gam20_Shot(x,y,alfa,speed,time);
      this.gameObjects.add(shot);
    }	  
    
    // inform counter
    grenades--;
    counterG.setNumber(grenades);
  }

   */

  
  /*
  protected void createNewObjects(double diffSeconds)
  {
    createZombie(diffSeconds);
    createGrenade(diffSeconds);
    
    // delete HelpText after ... seconds
    if(helpText!=null)
    {
      lifeHelpText -= diffSeconds;
      if(lifeHelpText < 0)
      {
        textObjects.remove(helpText);
        helpText = null;
      }
    }
  }

   */
  
  /*
  private void createGrenade(double diffSeconds)
  {
    final double INTERVAL = A_Const.SPAWN_GRENADE;
		  
	spawnGrenade += diffSeconds;
	if(spawnGrenade>INTERVAL)
	{
	  spawnGrenade -= INTERVAL;
	      
	  // create new Grenade
	  double x = worldPartX+Math.random()*A_Const.WORLDPART_WIDTH;
	  double y = worldPartY+Math.random()*A_Const.WORLDPART_HEIGHT;
	      
	  // if too close to Avatar, cancel
	  double dx = x-avatar.x;
	  double dy = y-avatar.y;
	  if(dx*dx+dy*dy < 200*200) 
	  { spawnGrenade = INTERVAL;
	    return;
	  }
	       
	  
	  // if collisions occur, cancel
	  Gam20_Grenade grenade = new Gam20_Grenade(x,y);
	  A_GameObjectList list = getPhysicsSystem().getCollisions(grenade);
	  if(list.size()!=0)
	  { spawnGrenade = INTERVAL;
	    return;
	  }	  
	  
	  // else add zombie to world
	  this.gameObjects.add(grenade);
      counterG.setNumber(grenades);      
    }
	  
  }

   */
  
  
  /*
  private void createZombie(double diffSeconds)
  {
    final double INTERVAL = A_Const.SPAWN_INTERVAL;
		  
	timePassed += diffSeconds;
	if(timePassed>INTERVAL)
	{
	  timePassed -= INTERVAL;
	      
	  // create new Zombie; preference to current screen
	  double x,y;
	  if(Math.random() < 0.7)
	  { x = Math.random()*A_Const.WORLD_WIDTH;
	    y = Math.random()*A_Const.WORLD_HEIGHT;
	  }
	  else
	  { x = worldPartX+Math.random()*A_Const.WORLDPART_WIDTH;
	    y = worldPartY+Math.random()*A_Const.WORLDPART_HEIGHT;
	  }
	  
	      
	  // if too close to Avatar, cancel
	  double dx = x-avatar.x;
	  double dy = y-avatar.y;
	  if(dx*dx+dy*dy < 400*400) 
	  { timePassed = INTERVAL;
	    return;
	  }
	      
	  // if collisions occur, cancel
	  Gam20_ZombieAI zombie = new Gam20_ZombieAI(x,y);
	  A_GameObjectList list = getPhysicsSystem().getCollisions(zombie);
	  if(list.size()!=0)
	  { timePassed = INTERVAL;
	    return;
	  }
	      
	  // else add zombie to world
	  this.gameObjects.add(zombie);
	  zombie.setDestination(avatar);
	  Gam20_Counter counter = (Gam20_Counter)textObjects.get(0);
      counter.increment();      
    }
	  
  }
 
  
  public void addGrenade()
  {
    if(grenades<999) { grenades++; }
    counterG.setNumber(grenades);
  }

   */
  }
}
