package net.johnbrooks.fjg.achievements;

import net.johnbrooks.fjg.Player;
import net.johnbrooks.fjg.Scheduler;
import net.johnbrooks.fjg.audio.AudioManager;
import net.johnbrooks.fjg.audio.Sound;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ieatl on 7/7/2017.
 */
public class AchievementManager
{
    private Player player;
    private List<Achievement> achievementList;

    public AchievementManager(Player player)
    {
        this.player = player;
        this.achievementList = new ArrayList<>();
    }

    public Player getPlayer()
    {
        return player;
    }

    /**
     * Giving the player an achievement will only return 'true', if the player didn't already have this achievement.
     * If the player already has this achievement, it will not be added.
     * @param achievement
     * @return
     */
    public boolean giveAchievement(Achievement achievement)
    {
        if (!hasAchievement(achievement))
        {
            Scheduler.getInstance().doTaskLater(() ->
            {
                AudioManager.getInstance().play(Sound.COIN_REWARD);
                player.modifyCoins(achievement.getReward());
            }, 0.01f);
            System.out.println("You were given the achievement of '" + achievement.getTitle() + "'.");
            return achievementList.add(achievement);
        }
        return false;
    }

    public boolean hasAchievement(Achievement achievement)
    {
        return achievementList.contains(achievement);
    }
}
