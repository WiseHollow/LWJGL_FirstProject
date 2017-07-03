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

    private boolean[] isMouseDown;

    private GameInput()
    {
        isMouseDown = new boolean[] { false, false };
    }

    public void update()
    {
        while (Mouse.next())
        {
            if (Mouse.getEventButtonState())
            {
                if (Mouse.getEventButton() == 0)
                {
                    System.out.println("Left button pressed");
                    isMouseDown[0] = true;
                }
                else if (Mouse.getEventButton() == 1)
                {
                    System.out.println("Right button pressed");
                    isMouseDown[0] = false;
                }
            }
            else
            {
                if (Mouse.getEventButton() == 0)
                {
                    System.out.println("Left button released");
                    isMouseDown[1] = true;
                }
                else if (Mouse.getEventButton() == 1)
                {
                    System.out.println("Right button released");
                    isMouseDown[1] = false;
                }
            }
        }
    }
}
