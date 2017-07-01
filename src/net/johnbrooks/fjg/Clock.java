package net.johnbrooks.fjg;

import org.lwjgl.Sys;

/**
 * Created by ieatl on 6/29/2017.
 */
public class Clock
{
    private static boolean paused;
    private static long lastFrame, totalTime;
    public static float delta = 0, multiplier = 1;

    public static boolean isPaused() { return paused; }

    public static long getTime()
    {
        return Sys.getTime() * 1000 / Sys.getTimerResolution();
    }

    public static float getDelta()
    {
        long currentTime = getTime();
        int delta = (int) (currentTime - lastFrame);
        lastFrame = getTime();

        // Limit the delta returned
        float d = delta * 0.001f;
        if (d > 0.05f)
            d = 0.05f;
        return d;
    }

    public static float delta()
    {
        if (paused)
            return 0;
        else
            return delta * multiplier;
    }

    public static float totalTime()
    {
        return totalTime;
    }

    public static float getMultiplier()
    {
        return multiplier;
    }

    public static void update()
    {
        delta = getDelta();
        totalTime += delta;
    }

    public static void changeMultiplier(int change)
    {
        if (multiplier + change < -1 || multiplier + change > 7)
        {

        }
        else
        {
            multiplier += change;
        }
    }

    public static void pause()
    {
        paused = !paused;
    }
}