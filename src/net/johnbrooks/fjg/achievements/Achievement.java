package net.johnbrooks.fjg.achievements;

/**
 * Created by ieatl on 7/7/2017.
 */
public enum Achievement
{
    DOUBLE_DAMAGE("Double Damage", "Deal X2 Damage to an enemy.", 20), BLASTER("Blaster", "Build a Tracking Tower.", 30), TOWER_O_PLENTY("Tower o' Plenty", "Build at least one of every tower.", 40);

    String title;
    String desc;
    int reward;

    Achievement(String title, String desc, int reward)
    {
        this.title = title;
        this.desc = desc;
        this.reward = reward;
    }

    public String getTitle() { return title; }
    public String getDesc() { return desc; }
    public int getReward() { return reward; }
}
