import java.awt.*;

class Game_World extends A_World {

    final double spawnGround = 70;
    private double ground = A_Const.WORLD_HEIGHT - spawnGround;
    private final int smallPlatform = 75, longPlatform = 125;


    protected void init() {
        // add the Avatar
        avatar = new Game_Avatar(30, ground - 25);
        // set WorldPart position
        worldPartX = 1500;
        //add ground
        gameObjects.add(new Game_Ground(0, setGround(0), 100, 70, new Color(100, 0, 0)));
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
     * MAP-Build area *
     **/
    //im falle von mehr maps, A_World muss man die Lvls anpassen!
    public void map1() {
        int platformSTART = 100, platformEND;

        platformEND = placeGround(platformSTART, 400);
        platformSTART = 175 + placeDoublePlatform(platformEND, 70);

        //TODO: 1 game_mob at x: 1475 walks 50pxl and back! (optional)


        //start == 1000;
        platformEND = placeGround(platformSTART, 650);
        platformSTART = 150 + placeQuadPlatform(platformEND, 65);
        placeGround(platformSTART, A_Const.WORLD_WIDTH - 300);

        //TODO:  1 Game_mob at x: 3322, walks 100pxl and back and faster!(optional)

        goal();
    }


    public void map2() {
        int platformSTART = 100, platformEND;

        platformEND = placeGround(platformSTART, 100);
        platformEND = placeGround(platformEND, 200, 100);

        platformSTART = 175 + placeDoublePlatform(platformEND, 70);

        //TODO: 1 game_mob at x: 1475 walks 50pxl and back! (optional)


        //start == 1000;
        platformEND = placeGround(platformSTART, 650);
        platformSTART = 150 + placeQuadPlatform(platformEND, 65);
        placeGround(platformSTART, A_Const.WORLD_WIDTH - 300);

        goal(10);
    }

    public void map3() {
        //TODO: Kommt noch
        goal(5);
    }

    /**
     * GOAL METHODS
     **/
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
