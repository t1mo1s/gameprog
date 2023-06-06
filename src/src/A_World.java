import java.util.ArrayList;

abstract class A_World {
    private A_GraphicSystem graphicSystem;
    private A_PhysicsSystem physicsSystem;
    private A_InputSystem inputSystem;
    private A_UserInput userInput;
    //private Game_World  gameWorld;

    // top left corner of the displayed pane of the world
    double worldPartX = 0;


    // defines maximum frame rate
    private static final int FRAME_MINIMUM_MILLIS = 10;

    // if game is over
    boolean gameOver = false;


    // all objects in the game, including the Avatar
    A_GameObjectList gameObjects = new A_GameObjectList();
    A_GameObject avatar;
    ArrayList<A_TextObject> textObjects = new ArrayList<A_TextObject>();


    A_World() {
        physicsSystem = new Game_PhysicsSystem(this);
    }


    //
    // the main GAME LOOP
    //
    final void run() {

        long lastTick = System.currentTimeMillis();

        while (true) {

            // calculate elapsed time
            long currentTick = System.currentTimeMillis();
            long millisDiff = currentTick - lastTick;

            // don�t run faster then MINIMUM_DIFF_SECONDS per frame
            //
            if (millisDiff < FRAME_MINIMUM_MILLIS) {
                try {
                    Thread.sleep(FRAME_MINIMUM_MILLIS - millisDiff);
                } catch (Exception ex) {
                }
                currentTick = System.currentTimeMillis();
                millisDiff = currentTick - lastTick;
            }

            lastTick = currentTick;

            // process User Input
            //TODO: REMOVE FROM END GAME!!! //
            userInput = inputSystem.getUserInput();
            processUserInput(userInput, millisDiff / 1000.0);
            if (userInput.keyMap.get('p'))
                System.out.println("PlayPOS   X: " + (int) avatar.x + " | Y: " + (int) avatar.y);
            userInput.clear();

            //Load Maps and CheckGoals
            if (avatar.x >= A_Const.WORLD_WIDTH - 200) {

                try {
                    Thread.sleep(1000);

                    avatar.x = 30;
                    avatar.y = A_Const.WORLD_HEIGHT - (70 + 25);

                  //  gameWorld.lvl++;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }


            /*
            //Changes Level manually
            if (userInput.keyMap.get('n') && gameWorld.lvl > 1) {
                gameWorld.lvl--;
                System.out.println(gameWorld.lvl);
            }
            if (userInput.keyMap.get('m') && gameWorld.lvl < 3) {
                gameWorld.lvl++;
                System.out.println(gameWorld.lvl);
            }*/

            /*****************************************************/

            // no actions if game is over
            if (gameOver) {
                continue;
            }

            int gameSize = gameObjects.size();

            // adjust displayed pane of the world
            this.adjustWorldPart();

            // draw all Objects
            graphicSystem.clear();
            for (int i = 0; i < gameSize; i++) {
                graphicSystem.draw(gameObjects.get(i));
            }


            // draw all TextObjects
            for (int i = 0; i < textObjects.size(); i++) {
                graphicSystem.draw(textObjects.get(i));
            }

            // redraw everything
            graphicSystem.redraw();
        }
    }


    // adjust the displayed pane of the world according to Avatar and Bounds
    //
    private final void adjustWorldPart() {
        final int RIGHT_END = A_Const.WORLD_WIDTH - A_Const.WORLDPART_WIDTH;

        // if avatar is too much right in display ...
        if (avatar.x > worldPartX + A_Const.WORLDPART_WIDTH - A_Const.SCROLL_BOUNDS) {
            // ... adjust display
            worldPartX = avatar.x + A_Const.SCROLL_BOUNDS - A_Const.WORLDPART_WIDTH;
            if (worldPartX >= RIGHT_END) {
                worldPartX = RIGHT_END;
            }
        }

        // same left

        else if (avatar.x < worldPartX + A_Const.SCROLL_BOUNDS) {
            worldPartX = avatar.x - A_Const.SCROLL_BOUNDS;
            if (worldPartX <= 0) {
                worldPartX = 0;
            }
        }


    }


    protected void setGraphicSystem(A_GraphicSystem p) {
        graphicSystem = p;
    }

    protected void setInputSystem(A_InputSystem p) {
        inputSystem = p;
    }

    protected A_PhysicsSystem getPhysicsSystem() {
        return physicsSystem;
    }


    protected abstract void init();

    protected abstract void processUserInput(A_UserInput input, double diffSec);
}
