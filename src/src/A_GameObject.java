
// (c) Thorsten Hasbargen


import java.awt.*;

abstract class A_GameObject 
{
  // yes, public  :(
  //
  protected double  x,y;
  protected double  alfa  = 0;
  protected double  speed = 0;
  //protected int     radius = 7;
    protected int height,width;
  protected Color   color;
  protected boolean isJumping = false;
    protected boolean isFalling = false;
  
  // if the object is existing, moving etc
  protected boolean isLiving = true;
  protected boolean isMoving = true;

  // destination the object shall move to,
  // old position etc
  private double  destX, destY;
  private boolean hasDestination = false;

  private double  xOld,  yOld;
  
      
  // GameObjects sometimes call physics methods
  protected static A_World         world;
  
  
  // construct GameObject
  public A_GameObject(double x_, double y_, 
		              double a_, double s_, 
		              int width_,int heigth_, Color color_)
  { 
	x=x_;    y=y_; 
    xOld=x;  yOld=y;
    alfa=a_; speed=s_;
    width = width_;
    height = heigth_;
    color = color_;
  }
  
  
  // move one step to direction <alfa>
  public void move(double diffSeconds)
  {  
    if(!isMoving) return;	  
	  
    // move if object has a destination
      /* brauchen des mit destination nich da der avatar mit der tastatur bewegt werden soll
	if(hasDestination)
	{
	  // stop if destination is reached	
	  double diffX = Math.abs(x-destX);
	  double diffY = Math.abs(y-destY);
	  if(diffX<3 && diffY<3)
	  { isMoving = false;
          if (isJumping==true){
              setDestination(x,y+60);
              isJumping=false;
          }
	    return;
	  }
	}

       */
    
    // remember old position
	xOld=x; yOld=y; 
	  
	// move one step
    x += Math.cos(alfa)*speed*diffSeconds;
    y += Math.sin(alfa)*speed*diffSeconds;   	  
  }

  public void moveLeft(double diffSeconds){
    x -= 2 *speed*diffSeconds;
  }

  public void moveRight(double diffSeconds){
    x += 2*speed*diffSeconds;
  }

  public void jump(double diffSeconds){
    y -= 4*speed*diffSeconds;
  }
  
  
  // test and reflect on Window Borders
  /*protected void reflectOnBorders()
  {
	double rad = radius;
	double PI  = Math.PI;
	
    if(x<rad && (alfa>PI/2 && alfa<PI*3/2))
	{ alfa = Math.PI-alfa;
	}
    if(y<rad && alfa>PI)
	{ alfa = PI*2-alfa; 
	}
    if(x>A_Const.WORLD_WIDTH-rad)
	{ alfa = Math.PI-alfa;
	}
    if(y>A_Const.WORLD_HEIGHT-rad)
	{ alfa = PI*2-alfa;
    }

	
	if(alfa<0)    alfa += PI*2;
	if(alfa>PI*2) alfa -= PI*2;	
  }

   */
  
  
  // set a point in the world as destination
  public final void setDestination(double dx, double dy)
  {
    isMoving       = true;
    hasDestination = true;
    destX          = dx;
    destY          = dy;
    
    alfa = Math.atan2(dy-y, dx-x);
  }  
  
  
  // set the LOCATION of an object as destination
  public void setDestination(A_GameObject obj)
  { setDestination(obj.x, obj.y);	  
  }
  
  
  // move back to the position BEFORE the move Method was called
  protected void moveBack() { x=xOld; y=yOld; }
    public void jump(int height){
        y -= height;
    }
  
  
  abstract int type();
  static void setWorld(A_World w) {world=w;}
  
}
