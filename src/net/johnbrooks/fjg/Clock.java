package net.johnbrooks.fjg;

import org.lwjgl.Sys;

/**
 * Created by ieatl on 6/29/2017.
 */
public class Clock
{
    private static boolean paused;
    private static int fps;
    private static long lastFrame, totalTime;
    public static float delta = 0, multiplier = 1, lastFPSCalculation = 1;

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

    private static void calculateFPS()
    {
        if (lastFPSCalculation >= 1)
        {
            fps = (int) (1 / Clock.delta);
            lastFPSCalculation = 0;
        }
        else
            lastFPSCalculation+=delta();
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
        calculateFPS();
    }

    public static void changeMultiplier(float change)
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

    public static int getFps()
    {
        return fps;
    }
}