package net.johnbrooks.fjg;

import org.lwjgl.input.Mouse;

/**
 * Created by ieatl on 7/3/2017.
 */
public class GameInput
{
    private static GameInput instance;
    public static GameInput getInstance()
    {
        if (instance == null)
            instance = new GameInput();
        return instance;
    }

    private boolean[] mouseDown;

    private GameInput()
    {
        mouseDown = new boolean[] { false, false };
    }

    public boolean isButtonDown(int mouseButton)
    {
        return mouseDown[mouseButton];
    }
    public void update()
    {
        while (Mouse.next())
        {
            if (Mouse.getEventButtonState())
            {
                if (Mouse.getEventButton() == 0)
                {
                    // Left pressed
                    mouseDown[0] = true;
                }
                else if (Mouse.getEventButton() == 1)
                {
                    // Right pressed
                    mouseDown[0] = true;
                }
            }
            else
            {
                if (Mouse.getEventButton() == 0)
                {
                    // Left released
                    mouseDown[0] = false;
                }
                else if (Mouse.getEventButton() == 1)
                {
                    // Right released
                    mouseDown[1] = false;
                }
            }
        }
    }
}
