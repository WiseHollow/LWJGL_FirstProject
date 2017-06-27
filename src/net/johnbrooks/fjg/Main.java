package net.johnbrooks.fjg;

import org.lwjgl.opengl.Display;

/**
 * Created by ieatl on 6/26/2017.
 */
public class Main
{
    public static void main(String[] args)
    {
        DisplayManager.create();

        while (!Display.isCloseRequested())
        {
            //update and draw

            DisplayManager.draw();
            DisplayManager.update();
        }

        DisplayManager.close();
    }
}
