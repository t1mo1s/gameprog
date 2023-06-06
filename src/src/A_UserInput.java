import java.awt.event.KeyEvent;
import java.util.HashMap;

final class A_UserInput {
    // everything a user can press on keyboard or mouse
    int mousePressedX, mousePressedY, mouseMovedX, mouseMovedY, mouseButton;
    char keyPressed;

    HashMap<Character, Boolean> keyMap = new HashMap<Character, Boolean>();

    // if Mouse was clicked, Key was pressed or Mouse is still hold down
    boolean isMouseEvent, isKeyEvent, isMousePressed;

    // ... is returned as a data set
    A_UserInput() {
        //Movement keys
        keyMap.put('a', false);
        keyMap.put('d', false);
        keyMap.put(' ', false);
        keyMap.put('w', false);

        //only for position testing//
        keyMap.put('p', false);

        //Change level manually, since goal hit box does not exist as of now
        keyMap.put('n', false);
        keyMap.put('m', false);

        this.clear();
    }

    final void clear() {
        isMouseEvent = false;
        isKeyEvent = false;
    }
}
