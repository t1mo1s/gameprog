import java.awt.*;

class Game_World extends A_World {
    public int lvl = 1;
    final double spawnGround = 70;
    private final int smallPlatform = 75, longPlatform = 125;
    private double ground = A_Const.WORLD_HEIGHT - spawnGround;
    private double playerFeet = ground - 25;    //25 -> Player-height


    public Game_World(A_World w) {
        A_World world = w;
    }

    protected void init() {
        // add the Avatar
        avatar = new Game_Avatar(30, playerFeet);
        // set WorldPart position
        worldPartX = 1500;
        //add ground
        gameObjects.add(new Game_Ground(0, setGround(0), 100, 70, new Color(100, 0, 0)));


        //Check LVLs

        if (lvl == 1) map1();
        else if (lvl == 2) map2();
        else map3();


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
        gameObjects.add(new Game_Ground(start, ground + higher, length, 100, new Color(108, 103, 103)));
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

    /**
     * MAP_DESIGNS
     **/
    void map1() {
        int platformSTART = 100, platformEND;

        platformEND = placeGround(platformSTART, 400);
        System.out.println("ground 1: " + platformSTART + " | " + platformEND);
        platformSTART = 175 + placeDoublePlatform(platformEND, 70);

        //TODO: 2 spikes at x: 1475!

        //start == 1000;
        platformEND = placeGround(platformSTART, 650);
        System.out.println(platformSTART + " | " + platformEND);

        platformSTART = 150 + placeQuadPlatform(platformEND, 65);

        System.out.println("QUAD: " + platformEND + " -> " + platformSTART);
        placeGround(platformSTART, A_Const.WORLD_WIDTH - 300);

        //TODO:  4 Spikes at x: 3322!
        goal();

    }


    void map2() {
        //TODO: Kommt noch
        goal();
    }

    void map3() {
        //TODO: Kommt noch
        goal(5);
    }

    /**
     * GOAL METHODS
     **/
    public int getLvl() {
        return lvl;
    }

    public void setLvl(int i) {
        if (i > 3) this.lvl = 1;
        else this.lvl = i;
    }

    //No entry -> on ground-level
    private void goal() {
        goal(0);
    }

    private void goal(int y) {
        placeGround(A_Const.WORLD_WIDTH - 300, A_Const.WORLD_WIDTH);
        gameObjects.add(new Game_Goal((int) (spawnGround + y)));
    }


    protected void processUserInput(A_UserInput userInput, double diffSeconds) {
        if (userInput.keyMap.get('a') && avatar.x >= 0) avatar.moveLeft(diffSeconds);

        if (userInput.keyMap.get('d') && avatar.x <= A_Const.WORLD_WIDTH - 30) avatar.moveRight(diffSeconds);

        if ((userInput.keyMap.get(' ') && !avatar.isJumping) || (userInput.keyMap.get('w') && !avatar.isJumping)) {
            avatar.isJumping = true;
            avatar.jump(diffSeconds);
        }


    }
}
