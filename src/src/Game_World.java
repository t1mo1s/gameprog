import java.awt.*;

class Game_World extends A_World {
    final double spawnGround = 70;
    private final double ground = A_Const.WORLD_HEIGHT - spawnGround;
    private final int smallPlatform = 75, longPlatform = 125;


    //TODO:: ADD LVL TEXT
    // ADD FIRST TIME HELPING TEXT (Controls)
    // Game Over Screen

    protected void init() {
        //add BackgroundIMG

        // add the Avatar
        avatar = new Game_Avatar(spawnX, ground - 25);
        // set WorldPart position (top left corner of part which is shown)
        worldPartX = 0;
        gameObjects.add(avatar);
        loadMap();

        createText();

        //if (gameStart) createTextAtStart();
    }

    private void createText() {

        Game_LevelDisplay levelDisplay = new Game_LevelDisplay(20, 30);
        textObjects.add(levelDisplay);
        Game_Timer timerText = new Game_Timer(200, 30);
        textObjects.add(timerText);

        Game_HighScore hs = new Game_HighScore(1200, 30);
        textObjects.add(hs);
    }

    /**
     * BUILDING METHODS!
     **/
    //From Spawn height adjustable | so you don't have to place it from the top, but rather from the floor itself
    public double setGround(double diff) {
        return ground - diff;
    }

    //PlaceGround takes startX Coord and the desired length, e.g. start = 100, length = 200 => ground with the total length of 300
    private int placeGround(int start, int length) {
        gameObjects.add(new Game_Ground(start, ground, length, 100));
        return length + start;
    }

    //like PlaceGround but with changeable y coord. WARNING, use "higher" as desired height, not coord.!
    //prob for LVL 3
    private int placeGround(int start, int length, double higher) {
        gameObjects.add(new Game_Ground(start, ground - higher, length, 100));
        return length + start;
    }

    private int placeTubes(int s, double h) {
        s = drawPipe(s, h) - 30;
        s = drawPipe(s, h + 10) + 30;
        s = drawPipe(s, h + 20);
        s = drawPipe(s, h);

        return s;
    }

    private int drawPipe(int s, double h) {
        h = setGround(h);
        int w = 50;

        Color green = new Color(82, 203, 50);
        Color seperatorLine = new Color(34, 86, 20);


        //The Head
        gameObjects.add(new Game_Platform(s - 10, h, w, 20, green));
        //The Base
        gameObjects.add(new Game_Ground(s, h, w - 20, 100, green));
        //The Line
        gameObjects.add(new Game_Ground(s - 8, h + 18, w - 4, 2, seperatorLine));

        return s + w + 150;
    }

    private int placeDoublePlatform(int x, int y) {
        gameObjects.add(new Game_Platform(x += 150, setGround(y), smallPlatform));
        gameObjects.add(new Game_Platform(x += smallPlatform + 100, setGround(y + 50), smallPlatform));
        return x + smallPlatform;
    }

    private int placeQuadPlatform(int x, int y) {
        x = placeDoublePlatform(x, y);
        gameObjects.add(new Game_Platform(x += 150, setGround(y + 75), longPlatform));
        gameObjects.add(new Game_Platform(x += longPlatform + 150, setGround(y + 115), smallPlatform));

        return x + longPlatform;
    }

    private int placeTrippleBackPlatform(int x, int y) {
        gameObjects.add(new Game_Platform(x, setGround(y), longPlatform));
        gameObjects.add(new Game_Platform(x - 225, setGround(y += 120), smallPlatform + 7));
        gameObjects.add(new Game_Platform(x += 25, setGround(y += 60), longPlatform * 2));

        return x + longPlatform * 2 + 125;
    }


    /****MOBS*****/
    private void spawnMobs(int x, int wd) {
        spawnMobs(x, setGround(0), wd);
    }

    private void spawnMobs(int x, double y, int wd) {
        //wd => Walking Distance
        gameObjects.add(new Game_Mob(x, y, wd));
    }

    /*** MAP-Build area ***/
    public void map1() {

        int platformSTART = 100;
        int platformEND;

        platformEND = placeGround(platformSTART, 400);
        platformSTART = 175 + placeDoublePlatform(platformEND, 70);

        spawnMobs(1475, 100);


        platformEND = placeGround(platformSTART, 650);
        platformSTART = 150 + placeQuadPlatform(platformEND, 65);
        //System.out.println(platformSTART);
        placeGround(platformSTART, A_Const.WORLD_WIDTH - 300);

        spawnMobs(3322, 150);
        goal();
    }

    public void map2() {
        int platformSTART = 100, platformEND;

        platformEND = placeGround(platformSTART, 300);
        platformSTART = 150 + placeQuadPlatform(platformEND, 70);

        spawnMobs(1790, 200);

        platformEND = placeGround(platformSTART, 700);
        platformSTART = 25 + placeQuadPlatform(platformEND, 65);

        platformSTART = 150 + placeDoublePlatform(platformSTART, 100);
        placeGround(platformSTART, A_Const.WORLD_WIDTH - 300);

        goal();
    }

    public void map3() {
        int platformSTART = 100, platformEND;

        platformEND = placeGround(platformSTART, 100);
        platformSTART = 150 + placeQuadPlatform(platformEND, 70);
        //the fifth and longest platform of te jump
        gameObjects.add(new Game_Platform(platformSTART, setGround(70 + 115), longPlatform * 3));
        //spawns mobs on the longer platform
        spawnMobs(platformSTART + 150, setGround(70 + 115), 150);
        //takes longer platform in account
        platformSTART += longPlatform * 3;

        platformEND = placeGround(platformSTART += 150, 700, -20);

        spawnMobs(platformSTART + 150, setGround(-20), 150);
        spawnMobs(platformEND - 150, setGround(-20), 100);
        //spawnMobs(platformSTART+150, 100);
        platformSTART = 25 + placeQuadPlatform(platformEND, 65);

        placeGround(platformSTART, A_Const.WORLD_WIDTH - 400);

        goal(50);
    }

    public void map4() {
        spawnX = 1800;

        int platformSTART = 100, platformEND;

        platformEND = placeGround(platformSTART, 600);
        platformEND = placeDoublePlatform(platformSTART = platformEND, 50);

        platformSTART = 150 + placeTrippleBackPlatform(platformEND + 100, 175);
        platformEND = 100 + placeGround(platformSTART, 350);

        gameObjects.add(new Game_FlowerMob(platformEND + 207, setGround(60)));
        platformSTART = 175 + placeTubes(platformEND + 45, 30);


        placeGround(platformSTART, A_Const.WORLD_WIDTH - 400);

        goal(25);
    }

    /**
     * GOAL METHODS
     **/
    //No entry -> on ground-leve
    private void goal() {
        //add Spawn
        goal(0);
    }

    private void goal(int y) {
        //add Spawn
        gameObjects.add(new Game_Ground(0, setGround(0), 100, 70, new Color(83, 67, 175)));
        //draw & Create Goal
        placeGround(A_Const.WORLD_WIDTH - 300, A_Const.WORLD_WIDTH, y);
        gameObjects.add(new Game_Goal((int) (spawnGround + y)));
    }

    protected void processUserInput(A_UserInput userInput) {//, double diffSeconds) {
        double diffSeconds = 0.015;

        if (!gamePaused && !gameOver) {
            if (avatar.y < A_Const.WORLD_HEIGHT + 50) {
                if (userInput.keyMap.get('a') && avatar.x >= 0) {
                    avatar.moveLeft(diffSeconds);
                }
                if (userInput.keyMap.get('d') && avatar.x <= A_Const.WORLD_WIDTH) {
                    avatar.moveRight(diffSeconds);
                }
                if (userInput.keyMap.get(' ') || userInput.keyMap.get('w')) {
                    avatar.jump(diffSeconds);
                }
            }
        }
    }
}