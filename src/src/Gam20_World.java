// (c) Thorsten Hasbargen


import java.awt.*;
import java.awt.event.KeyEvent;

class Gam20_World extends A_World {

    private Gam20_CounterGrenades counterG;
    private Gam20_Counter counterZ;
    private Gam20_HelpText helpText;
    public int lvl = 1;
    private double ground = A_Const.WORLD_HEIGHT - 70;
    private double playerFeet = ground - 25;

    protected void init() {
        // set WorldPart position
        worldPartX = 1500;
        worldPartY = 1500;
        //add roof
        //gameObjects.add(new Gam20_Ground(0, 0, A_Const.WORLD_WIDTH, 70));
        //add ground
        gameObjects.add(new Gam20_Ground(0, getGround(0), 100, 70, new Color(100, 0, 0)));


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
        avatar = new Gam20_Avatar(30, playerFeet);
        gameObjects.add(avatar);
    }

    //From Spawn height adjustable
    public double getGround(int diff) {
        ground = ground - diff;
        return ground;
    }

    /**
     * Object(posX, posY, width, Height)
     * Position from Right, Pos from TOp, Till Left, Till Bottom *
     * player kann nur 225pxl hoch springen, platform MUSS max 222pxl hoch sein! *
     **/
    private void map1() {
        gameObjects.add(new Gam20_Ground(200, getGround(30), A_Const.WORLD_WIDTH, 100, new Color(108, 103, 103)));


        //Test Platform
        gameObjects.add(new Gam20_Platform(300, getGround(220)));
        // gameObjects.add(new Gam20_Goal(A_Const.WORLD_WIDTH-100, ));
    }

    private void map2() {

    }

    private void map3() {
    }


    protected void processUserInput(A_UserInput userInput, double diffSeconds) {

        if (userInput.keyMap.get('a')) {
            avatar.moveLeft(diffSeconds);
        }
        if (userInput.keyMap.get('d')) {
            avatar.moveRight(diffSeconds);
        }
        if ((userInput.keyMap.get(' ') && !avatar.isJumping) || (userInput.keyMap.get('w') != null && userInput.keyMap.get('w') && !avatar.isJumping)) {
            avatar.isJumping = true;
            avatar.jump(diffSeconds);
        }
    }


}
