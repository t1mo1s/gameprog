public interface A_GraphicSystem {
    void clear();

    void draw(A_GameObject dot);

    void draw(A_TextObject obj);

    void redraw();

    void setWorld(A_World world);
}
