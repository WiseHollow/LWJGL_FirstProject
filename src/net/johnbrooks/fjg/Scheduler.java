package net.johnbrooks.fjg;

import java.util.HashMap;

/**
 * Created by ieatl on 7/2/2017.
 */
public class Scheduler
{
    private static Scheduler instance;
    public static Scheduler getInstance()
    {
        if (instance == null)
            instance = new Scheduler();
        return instance;
    }

    private HashMap<Integer, Schedule> scheduleHashMap;
    private int latestId;

    private Scheduler()
    {
        scheduleHashMap = new HashMap<>();
        latestId = 1;
    }

    public void doTaskLater(final Runnable runnable, float delay)
    {
        Schedule schedule = new Schedule(runnable, latestId, delay);
        scheduleHashMap.put(latestId, schedule);
        latestId++;
    }

    public void update()
    {
        if (!scheduleHashMap.isEmpty())
        {
            Schedule[] schedules = scheduleHashMap.values().toArray(new Schedule[scheduleHashMap.values().size()]);
            for (int i = 0; i < schedules.length; i++)
            {
                Schedule schedule = schedules[i];
                schedule.update();
            }
        }
    }

    private class Schedule
    {
        private int id;
        private float delay, time;
        private Runnable runnable;

        public Schedule(final Runnable runnable, int id, float delay)
        {
            this.id = id;
            this.runnable = runnable;
            this.delay = delay;
            this.time = 0f;
        }

        public void run()
        {
            scheduleHashMap.remove(id);
            runnable.run();
        }

        public boolean update()
        {
            time += Clock.delta();
            if (time >= delay)
            {
                run();
                return true;
            }
            return false;
        }
    }
}
