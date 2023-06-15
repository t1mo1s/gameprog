import java.util.ArrayList;

abstract class A_World {
    private A_GraphicSystem graphicSystem;
    private final A_PhysicsSystem physicsSystem;
    private A_InputSystem inputSystem;
    private int lvl = 1;

    abstract void map1();

    abstract void map2();

    abstract void map3();


    // top left corner of the displayed pane of the world
    protected double worldPartX = 0;

    // defines maximum frame rate
    private static final int FRAME_MINIMUM_MILLIS = 10;

    // if game is over
    protected boolean gameOver = false;
    protected boolean gamePaused = false;
    protected boolean gameStart = false;


    // all objects in the game, including the Avatar
    A_GameObjectList gameObjects = new A_GameObjectList();
    A_GameObject avatar;
    ArrayList<A_TextObject> textObjects = new ArrayList<>();

    private final Game_Over gameOverText = new Game_Over(450, A_Const.WORLD_HEIGHT / 2 - 90);
    private final Game_GameOver_InfoText gameOverInfoText = new Game_GameOver_InfoText(600, A_Const.WORLD_HEIGHT / 2);
    private final Game_Title gameMenuTitle = new Game_Title(450, A_Const.WORLD_HEIGHT / 2 - 70);
    private final Game_InfoText infoText = new Game_InfoText(10, A_Const.WORLD_HEIGHT / 2);

    A_World() {
        physicsSystem = new Game_PhysicsSystem(this);
    }


    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    // the main GAME LOOP
    final void run() {
        long lastTick = System.currentTimeMillis();


        while (true) {
            // Check LVLs
            switch (lvl) {
                case 1 -> {
                    map1();
                }
                case 2 -> {
                    map2();
                }
                case 3 -> {
                    map3();
                }
                default -> {
                    lvl = 1;
                    map1();
                }
            }


            // calculate elapsed time
            long currentTick = System.currentTimeMillis();
            long millisDiff = currentTick - lastTick;

            if (millisDiff < FRAME_MINIMUM_MILLIS) {
                try {
                    Thread.sleep(FRAME_MINIMUM_MILLIS - millisDiff);
                } catch (Exception e) {
                    e.getStackTrace();
                }
                currentTick = System.currentTimeMillis();
                millisDiff = currentTick - lastTick;
            }

            lastTick = currentTick;


            this.getPhysicsSystem().applyGravity();

            // process User Input
            A_UserInput userInput = inputSystem.getUserInput();
            processUserInput(userInput); //, millisDiff / 1000.0);

            //TODO: Remove Player Pos Button!


            if (userInput.keyMap.get('m') && !gamePaused) {
                textObjects.add(gameMenuTitle);
                textObjects.add(infoText);
                gamePaused = true;
            } else if (userInput.keyMap.get('m') && gamePaused) {
                removeText(gameMenuTitle);
                removeText(infoText);
                gamePaused = false;
            }

            if (userInput.keyMap.get('r')) {
                avatar.x = 40;
                avatar.y = A_Const.WORLD_HEIGHT - 70;
            }

            userInput.clear();

            // no actions if game is over
           /* if (gameOver) {
                continue;
            }*/


            if (avatar.y >= A_Const.WORLD_HEIGHT) {
                textObjects.add(gameOverText);
                textObjects.add(gameOverInfoText);
                gameOver = true;
            } else {
                gameOver = false;
                removeText(gameOverInfoText);
                removeText(gameOverText);
            }

            //this.getPhysicsSystem().getCollisions(avatar);

            int gameSize = gameObjects.size();


            int num = 0;
            while (num < gameSize) {
                if (!gameObjects.get(num).isLiving) {
                    gameObjects.remove(num);
                    gameSize--;
                } else {
                    num++;
                }
            }


            // adjust displayed pane of the world
            this.adjustWorldPart();

            // draw all Objects
            graphicSystem.clear();
            for (int i = 0; i < gameSize; i++) {
                graphicSystem.draw(gameObjects.get(i));
            }

            // draw all TextObjects
            for (int i = 0; i < textObjects.size(); i++) {
                //Update Level Text
                levelTxt(lvl);

                //getTimer(millisDiff);
                graphicSystem.draw(textObjects.get(i));
            }

            // redraw everything
            graphicSystem.redraw();


            //END OF THE WHILE(!GAMEOVER) LOOP
        }

    }

    /******************************** END OF GAME LOOP **********************************/

    // adjust the displayed pane of the world according to Avatar and Bounds
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

    private void levelTxt(int lvl) {
        Game_LevelDisplay levelDisplay = (Game_LevelDisplay) textObjects.get(0);
        levelDisplay.setLVL(lvl);
    }

/* DOESN'T WORK AS INTENDED xD
    private void getTimer(long elapsedTime) {
        // Calculate seconds and milliseconds
        long seconds = elapsedTime / 1000;
        long milliseconds = elapsedTime % 1000;
        Game_Timer timer = (Game_Timer) textObjects.get(1);
        timer.setTimer(seconds, milliseconds);
    }
 */

    private void removeText(A_TextObject txtObj) {
        textObjects.remove(txtObj);
    }

    protected void setGraphicSystem(A_GraphicSystem p) {
        graphicSystem = p;
    }

    protected A_InputSystem getInputSystem() {
        return inputSystem;
    }

    protected void setInputSystem(A_InputSystem p) {
        inputSystem = p;
    }

    protected A_PhysicsSystem getPhysicsSystem() {
        return physicsSystem;
    }

    protected abstract void init();

    protected abstract void processUserInput(A_UserInput input); //, double diffSec);
}
