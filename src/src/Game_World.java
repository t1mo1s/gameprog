import java.awt.*;

class Game_World extends A_World {
    int platformSTART = 100, platformEND = platformSTART;
    public int lvl = 1;
    private double groundlevel = 70;
    private double ground = A_Const.WORLD_HEIGHT - groundlevel;
    private double playerFeet = ground - 25;    //25 -> Player-height

    protected void init() {
        // set WorldPart position
        worldPartX = 1500;
        //add roof
        //gameObjects.add(new Gam20_Ground(0, 0, A_Const.WORLD_WIDTH, 70));
        //add ground
        gameObjects.add(new Game_Ground(0, setGround(0), 100, 70, new Color(100, 0, 0)));

        if (lvl == 1) {
            map1();
        }
        if (lvl == 2) {
            map2();
        }
        if (lvl == 3) {
            map3();
        }

        // add the Avatar
        avatar = new Game_Avatar(30, playerFeet);
        gameObjects.add(avatar);
    }

    //From Spawn height adjustable | so you don't have to place it from the top, but rather from the floor itself
    public double setGround(int diff) {
        return ground - diff;
    }

    /**
     * Object(posX, posY, width, Height)
     * Position from Right, Pos from Top, Till Left, Till Bottom *
     **/
    private void placeGround(int start, int end) {
        gameObjects.add(new Game_Ground(start, ground, end, 100, new Color(108, 103, 103)));
    }

    //prob for LVL 3
    private void placeGround(int start, int end, double higher) {
        gameObjects.add(new Game_Ground(start, ground + higher, end, 100, new Color(108, 103, 103)));
    }

    private void placeDoublePlatform(int x, int y) {
        gameObjects.add(new Game_Platform(x += 150, setGround(y)));
        gameObjects.add(new Game_Platform(x += 350, setGround(y + 100)));
    }

    private void placeTriplePlatform(int x, int y) {
        placeDoublePlatform(x, y);
        gameObjects.add(new Game_Platform(x += 700, setGround(y + 170)));

    }

    private void map1() {
        platformEND = platformSTART + 400;
        int smallJump = 800, bigJump = smallJump + 400;
        int holes = 0;

        placeGround(platformSTART, holes = platformEND);
        placeDoublePlatform(platformEND, 80);

        System.out.println("Start: " + platformSTART + " END: " + holes);
        placeGround(platformSTART = holes + smallJump, holes = platformEND + 250);
        placeTriplePlatform(platformEND = platformSTART + holes, 75);

        System.out.println("Start: " + platformSTART + " END: " + platformEND);


        placeGround(platformSTART = A_Const.WORLD_WIDTH - 750, A_Const.WORLD_WIDTH - 300);

        System.out.println("Start: " + platformSTART + " END: " + platformEND);
        goal();
    }

    private void map2() {
        //TODO: Kommt noch
        goal();
    }

    private void map3() {
        //TODO: Kommt noch
        goal(5);
    }

    //No entry -> on ground-level
    private void goal() {
        goal(0);
    }

    private void goal(int y) {
        placeGround(platformSTART = A_Const.WORLD_WIDTH - 300, A_Const.WORLD_WIDTH);
        gameObjects.add(new Game_Goal((int) (groundlevel + y)));
    }

    protected void processUserInput(A_UserInput userInput, double diffSeconds) {

        if (userInput.keyMap.get('a')) {
            avatar.moveLeft(diffSeconds);
            this.getPhysicsSystem().getCollisions(avatar);
        }
        if (userInput.keyMap.get('d')) {
            avatar.moveRight(diffSeconds);
            this.getPhysicsSystem().getCollisions(avatar);
        }
        if (userInput.keyMap.get(' ')) {
            /*avatar.isJumping = true;
            avatar.jump(diffSeconds);
            this.getPhysicsSystem().getCollisions(avatar);
             */
            if (!isJumping && isOnGround) {
                playerSpeedY = -12; // Springen
                isJumping = true;
                isOnGround = false;
            }

        }
    }


}
