import java.awt.*;

class Game_Counter extends A_TextObject
{
  private int number = 1;
	
  public Game_Counter(int x, int y)
  { super(x,y, new Color(255,255,0,210));
  }
  
  public String toString()
  { String display = "Zombies: ";
    display += number;
    return display;
  }
  
  public void increment(){ number++; }
}
