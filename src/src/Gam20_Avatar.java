
// (c) Thorsten Hasbargen


import java.awt.*;

class Gam20_Avatar extends A_GameObject
{


  public Gam20_Avatar(double x, double y) 
  { super(x,y,0,200,15,25, new Color(96,96,255));
    this.isMoving = false;
  }




  public void move(double diffSeconds)
  {
	// move Avatar one step forward
	super.move(diffSeconds);
	
    // calculate all collisions with other Objects 
	A_GameObjectList collisions = world.getPhysicsSystem().getCollisions(this);
	for(int i=0; i<collisions.size(); i++)
	{
	  A_GameObject obj = collisions.get(i);
	  
	  // if Object is a tree, move back one step
	  if(obj.type()==A_Const.TYPE_TREE) 
	  { this.moveBack(); }
	  
	  // pick up Grenades
	  /*
	  Grenade zeug wird nich gebraucht
	  else if(obj.type()==A_Const.TYPE_GRENADE)
	  { ((Gam20_World)world).addGrenade();
	    obj.isLiving=false;
	  }

	   */
	}
  }

  public void moveLeft(double diffSeconds){
      super.moveLeft(diffSeconds);
      //hier dann noch collision detections
  }

  public void moveRight(double diffSeconds){
      super.moveRight(diffSeconds);
  }

  public void jump(double diffSeconds){
      super.jump(diffSeconds);
  }
  
  
  public int type() { return A_Const.TYPE_AVATAR; }
}
