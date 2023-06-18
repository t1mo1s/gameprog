import java.util.HashMap;

final class A_UserInput {
    // everything a user can press on keyboard or mouse
    int mousePressedX, mousePressedY, mouseMovedX, mouseMovedY, mouseButton;
    char keyPressed;

    HashMap<Character, Boolean> keyMap = new HashMap<>();

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
        //open Menu
        keyMap.put('m', false);
        //restart game
        keyMap.put('r', false);
        this.clear();
    }

    void clear() {
        isMouseEvent = false;
        isKeyEvent = false;
    }
}
