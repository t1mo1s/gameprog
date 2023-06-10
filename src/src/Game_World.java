import java.awt.*;

class Game_World extends A_World {


    final double spawnGround = 70;
    private final double ground = A_Const.WORLD_HEIGHT - spawnGround;
    private final int smallPlatform = 75, longPlatform = 125;



    protected void init() {
        // add the Avatar
        avatar = new Game_Avatar(30, ground - 25);
        // set WorldPart position
        worldPartX = 1500;

        gameObjects.add(avatar);
    }

    /**
     * BUILDING METHODS!
     **/

    //From Spawn height adjustable | so you don't have to place it from the top, but rather from the floor itself
    public double setGround(int diff) {
        return ground - diff;
    }

    //PlaceGround takes startX Coord and the desired length, e.g. start = 100, length = 200 => ground with the total length of 300
    private int placeGround(int start, int length) {
        gameObjects.add(new Game_Ground(start, ground, length, 100, new Color(108, 103, 103)));
        return length + start;
    }

    //like PlaceGround but with changeable y coord. WARNING, use "higher" as desired height, not coord.!
    //prob for LVL 3
    private int placeGround(int start, int length, double higher) {
        gameObjects.add(new Game_Ground(start, ground - higher, length, 100, new Color(108, 103, 103)));
        return length + start;
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

    /****MOBS*****/
    private void spawnMobs(int x, int wd) {
        spawnMobs(x, setGround(0), wd);
    }

    private void spawnMobs(int x, double y, int wd) {
        //wd => Walking Distance
        gameObjects.add(new Game_Mob(x, y));
        //TODO: walking animation
    }

    /*** MAP-Build area ***/
    public void map1() {
        int platformSTART = 100, platformEND;

        platformEND = placeGround(platformSTART, 400);
        platformSTART = 175 + placeDoublePlatform(platformEND, 70);

        //TODO: 1 game_mob at x: 1475 walks 100pxl and back!
        spawnMobs(1475, 100);

        platformEND = placeGround(platformSTART, 650);
        platformSTART = 150 + placeQuadPlatform(platformEND, 65);
        //System.out.println(platformSTART);
        placeGround(platformSTART, A_Const.WORLD_WIDTH - 300);
        //TODO:  1 Game_mob at x: 3322, walks 150pxl and back and faster!
        spawnMobs(3322, 150);

        goal();
    }

    public void map2() {
        int platformSTART = 100, platformEND;

        platformEND = placeGround(platformSTART, 300);
        platformSTART = 150 + placeQuadPlatform(platformEND, 70);

        //TODO: 1 Game_mob at x = 1790, walks 100pxl
        spawnMobs(1790, 100);

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
        gameObjects.add(new Game_Platform(platformSTART, setGround(70 + 115), longPlatform * 3));

        //TODO: 1 Game_mob at x = 1790, walks 500pxl
        spawnMobs(platformSTART + 50, setGround(70 + 115), 250);
        spawnMobs(platformSTART + 300, setGround(70 + 115), -250);

        platformSTART += longPlatform * 3;

        platformEND = placeGround(platformSTART += 150, 700);

        spawnMobs(platformSTART + 150, 500);
        //spawnMobs(platformSTART+150, 100);
        platformSTART = 25 + placeQuadPlatform(platformEND, 65);

        placeGround(platformSTART, A_Const.WORLD_WIDTH - 300);

        goal(50);
    }

    /**
     * GOAL METHODS
     **/
    //No entry -> on ground-level
    private void goal() {
        goal(0);
    }

    private void goal(int y) {
        //add Spawn
        gameObjects.add(new Game_Ground(0, setGround(0), 100, 70, new Color(100, 0, 0)));

        placeGround(A_Const.WORLD_WIDTH - 300, A_Const.WORLD_WIDTH, y);
        gameObjects.add(new Game_Goal((int) (spawnGround + y)));
    }

    protected void processUserInput(A_UserInput userInput, double diffSeconds) {
        if (userInput.keyMap.get('a') && avatar.x >= 0) avatar.moveLeft(diffSeconds);

        if (userInput.keyMap.get('d') && avatar.x <= A_Const.WORLD_WIDTH - 30) avatar.moveRight(diffSeconds);

        if (userInput.keyMap.get('a')) {
            avatar.moveLeft(diffSeconds);
            //this.getPhysicsSystem().getCollisions(avatar);
        }
        if (userInput.keyMap.get('d')) {
            avatar.moveRight(diffSeconds);
            /*this.getPhysicsSystem().getCollisions(avatar);

             */
        }

        if ((userInput.keyMap.get(' ') && !avatar.isJumping) || (userInput.keyMap.get('w') && !avatar.isJumping)) {

            avatar.jump(diffSeconds);
        }
    }
}
