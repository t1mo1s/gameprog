final class A_Main {


    public A_Main() {
        A_Frame frame = new B_Frame();
        frame.displayOnScreen();

        A_World world = new Game_World();

        world.setGraphicSystem(frame.getGraphicSystem());
        world.setInputSystem(frame.getInputSystem());

        A_GameObject.setWorld(world);
        A_GameObject.setPhysicsSystem(world.getPhysicsSystem());
        A_TextObject.setWorld(world);
        frame.getGraphicSystem().setWorld(world);

        world.init();
        world.run();
    }

    public static void main(String[] args) {
        new A_Main();
    }
}
