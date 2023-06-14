abstract class A_PhysicsSystem {
    protected A_World world;

    public A_PhysicsSystem(A_World w) {
        world = w;
    }

    protected abstract A_GameObjectList getCollisions(A_GameObject object);
    public abstract void applyGravity();

}
