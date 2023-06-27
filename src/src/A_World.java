import java.util.ArrayList;

abstract class A_World {
    private A_GraphicSystem graphicSystem;
    private final A_PhysicsSystem physicsSystem;
    private A_InputSystem inputSystem;

    int spawnX = 40;
    private int lvl = 1;

    int maxLvl = 4;

    abstract void map1();

    abstract void map2();

    abstract void map3();

    abstract void map4();

    // top left corner of the displayed pane of the world
    protected double worldPartX = 0;

    // defines maximum frame rate
    private static final int FRAME_MINIMUM_MILLIS = 10;

    // if game is over
    protected boolean gameOver = false, gamePaused = false;

    // all objects in the game, including the Avatar
    A_GameObjectList gameObjects = new A_GameObjectList();
    A_GameObject avatar;
    ArrayList<A_TextObject> textObjects = new ArrayList<>();

    private final Game_Over gameOverText = new Game_Over(450, A_Const.WORLD_HEIGHT / 2 - 90);
    private final Game_GameOver_InfoText gameOverInfoText = new Game_GameOver_InfoText(600, A_Const.WORLD_HEIGHT / 2);
    private final Game_Title gameMenuTitle = new Game_Title(450, A_Const.WORLD_HEIGHT / 2 - 70);
    private final Game_InfoText infoText = new Game_InfoText(10, A_Const.WORLD_HEIGHT / 2);
    private final Game_Sound gameSound = new Game_Sound();
    long seconds, milliseconds, storeSeconds = seconds, storeMilliseconds = milliseconds;

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
        long lastTick = System.currentTimeMillis(), startTime = lastTick;

        while (true) {
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
                gameSound.restart();

                avatar.x = spawnX;
                avatar.y = A_Const.WORLD_HEIGHT - 70;

                gameOver = false;
                removeText(gameOverInfoText);
                removeText(gameOverText);

                gamePaused = false;
                removeText(gameMenuTitle);
                removeText(infoText);

                startTime = System.currentTimeMillis();
            }

            userInput.clear();

            if (avatar.y >= A_Const.WORLD_HEIGHT) gameOver = true;
            if (gameOver) {
                textObjects.add(gameOverText);
                textObjects.add(gameOverInfoText);
                removeText(gameMenuTitle);
                removeText(infoText);
            } else {
                removeText(gameOverInfoText);
                removeText(gameOverText);
            }

            int gameSize = gameObjects.size();

            //get collisions if goal -> load new map
            A_GameObjectList collisions = physicsSystem.getCollisions(avatar);
            for (A_GameObject collision : collisions) {
                if (collision.type() == A_Const.TYPE_GOAL) {
                    loadMap();
                    break;
                }
            }

            //let mobs walk
            for (int i = 0; i < gameSize; i++) {
                A_GameObject obj = gameObjects.get(i);
                if (obj.type() == A_Const.TYPE_MOB) {
                    Game_Mob mob = (Game_Mob) obj;
                    // Game_Mob_Flower flower = (Game_Mob_Flower) obj;
                    mob.move(millisDiff / 1000.0);
                    // flower.move(millisDiff / 1000.0);
                } else if (obj.type() == A_Const.TYPE_FLOWERMOB) {
                    Game_Mob_Flower flower = (Game_Mob_Flower) obj;
                    flower.move(millisDiff / 1000.0);
                }
            }

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
            for (A_TextObject textObject : textObjects) {
                //Update Level Text
                levelTxt(lvl);
                if (!gameOver) {
                    getTimer(startTime);
                }
                if (gameOver) getHighScore();

                graphicSystem.draw(textObject);
            }

            // redraw everything
            graphicSystem.redraw();
        }
    }

    /******************************** END OF GAME LOOP **********************************/

    // adjust the displayed pane of the world according to Avatar and Bounds
    private void adjustWorldPart() {
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

    private void getTimer(long startTime) {
        Game_Timer timer = (Game_Timer) textObjects.get(1);

        long currentTime = System.currentTimeMillis();
        long elapsedTime = (currentTime - startTime);
        // Calculate seconds and milliseconds
        seconds = elapsedTime / 1000;
        milliseconds = elapsedTime % 1000;

        if (!gameOver || !gamePaused) {
            timer.setTimer(seconds, milliseconds);
        } else {
            timer.setTimer(storeSeconds, storeMilliseconds);
        }
    }

    private void getHighScore() {
        Game_HighScore hs = (Game_HighScore) textObjects.get(2);

        if (seconds == storeSeconds) {
            storeMilliseconds = Math.max(storeMilliseconds, milliseconds);
        } else if (seconds > storeSeconds) {
            storeMilliseconds = milliseconds;
        }

        storeSeconds = Math.max(storeSeconds, seconds);
        hs.setHS(storeSeconds, storeMilliseconds);
    }

    private void removeText(A_TextObject txtObj) {
        textObjects.remove(txtObj);
    }

    protected void setGraphicSystem(A_GraphicSystem p) {
        graphicSystem = p;
    }

    protected A_GraphicSystem getGraphicSystem() {
        return graphicSystem;
    }

    protected void setInputSystem(A_InputSystem p) {
        inputSystem = p;
    }

    protected A_PhysicsSystem getPhysicsSystem() {
        return physicsSystem;
    }

    protected void loadMap() {
        switch (lvl) {
            case 1 -> map1();
            case 2 -> map2();
            case 3 -> map3();
            case 4 -> map4();
        }
    }

    protected abstract void init();

    protected abstract void processUserInput(A_UserInput input); //, double diffSec);
}
