
// (c) Thorsten Hasbargen


import java.util.HashMap;

final class A_UserInput
{
  // everything a user can press on keyboard or mouse	
  int mousePressedX, mousePressedY, 
      mouseMovedX,   mouseMovedY, mouseButton;
  
  char keyPressed;

  HashMap<Character,Boolean> keyMap = new HashMap<Character,Boolean>();
  
  // if Mouse was clicked, Key was pressed or Mouse is still hold down
  boolean isMouseEvent, isKeyEvent, isMousePressed; 
  
  // ... is returned as a data set
  A_UserInput()
  {
    keyMap.put('a', false);
    keyMap.put('d', false);
    keyMap.put('w', false);this.clear();
  }
  
  final void clear()
  { isMouseEvent=false; isKeyEvent=false;
  }
}
