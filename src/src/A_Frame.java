interface A_Frame {
    // appear on Screen
    void displayOnScreen();
    void closeWindow();

    // return Subsystems
    A_GraphicSystem getGraphicSystem();

    A_InputSystem getInputSystem();
}
