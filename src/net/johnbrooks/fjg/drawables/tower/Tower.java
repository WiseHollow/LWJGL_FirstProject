package net.johnbrooks.fjg.drawables.tower;

import net.johnbrooks.fjg.Clock;
import net.johnbrooks.fjg.audio.AudioManager;
import net.johnbrooks.fjg.audio.Sound;
import net.johnbrooks.fjg.drawables.Draw;
import net.johnbrooks.fjg.drawables.entities.Enemy;
import net.johnbrooks.fjg.drawables.tiles.Tile;
import net.johnbrooks.fjg.level.Level;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ieatl on 6/30/2017.
 */
public class Tower
{
    private static final short WIDTH = 64, HEIGHT = 64;

    protected Level level;
    protected Tile tile;
    protected List<Enemy> enemyList;
    protected TowerType towerType;

    protected float timeSinceLastShot, topTextureRotation;
    protected int x, y, power;
    protected Enemy target;
    protected List<Projectile> projectileList;
    protected boolean toRemove;

    protected TargetStyle targetStyle;

    public Tower(TowerType towerType, Level level, Tile tile, List<Enemy> enemyList)
    {
        this.level = level;
        this.tile = tile;
        this.enemyList = enemyList;
        this.towerType = towerType;

        this.x = tile.getX();
        this.y = tile.getY();

        this.topTextureRotation = 0;
        this.timeSinceLastShot = towerType.getTowerStats().getWarmUp();
        this.target = null;
        this.projectileList = new ArrayList<>();
        this.power = 1;

        this.targetStyle = TargetStyle.FURTHEST_TRAVELLED;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getSlotX() { return tile.getXSlot(); }
    public int getSlotY() { return tile.getYSlot(); }
    public Tile getTile() { return tile; }
    public int getCost() { return towerType.getTowerStats().getCost(); }
    public int getPower() { return power; }
    public boolean isToRemove() { return toRemove; }
    public TowerType getTowerType() { return towerType; }

    public List<Enemy> getEnemyList() { return enemyList; }

    public int getTotalViewDistance()
    {
        return towerType.getTowerStats().getViewDistance() + ((power - 1) * 10);
    }

    public int getTotalDamage()
    {
        return towerType.getProjectileStats().getDamage() * power;
    }

    public int getTotalSellPrice()
    {
        return (int) (towerType.getTowerStats().getCost() * power * 0.5f);
    }

    public void setTile(Tile tile)
    {
        this.x = tile.getX();
        this.y = tile.getY();
        this.tile = tile;
    }

    public boolean canUpgrade()
    {
        return power < 4;
    }

    public boolean upgradeTower()
    {
        if (canUpgrade() && level.getPlayer().getCoins() >= towerType.getTowerStats().getCost())
        {
            level.getPlayer().modifyCoins(-towerType.getTowerStats().getCost());
            AudioManager.getInstance().play(Sound.COIN_REWARD);
            power++;
            return true;
        }

        return false;
    }

    public void sellTower()
    {
        level.getPlayer().modifyCoins(getTotalSellPrice());
        toRemove = true;
        AudioManager.getInstance().play(Sound.COIN_REWARD);
    }

    public void update()
    {
        target = calculateEnemyTarget();
        timeSinceLastShot += Clock.delta();
        float warmUpTime = towerType.getTowerStats().getWarmUp() - (power * 0.15f);
        if (warmUpTime < 0.1f)
            warmUpTime = 0.1f;
        if (timeSinceLastShot > warmUpTime && !Clock.isPaused())
            shoot();

        for (int i = 0; i < projectileList.size(); i++)
        {
            Projectile projectile = projectileList.get(i);
            if (!projectile.isAlive())
            {
                projectileList.remove(projectile);
                i--;
            }
            else
            {
                projectile.update();
            }
        }

        if (towerType.getTowerStats().getTopTexture() != null)
        {
            if (target == null)
                topTextureRotation += Clock.delta() * 30;
            else
                topTextureRotation = calculateAngleToTarget();
        }
    }

    public void draw()
    {
        if (towerType.getTowerStats().getBaseTexture() != null)
            Draw.drawTexture(towerType.getTowerStats().getBaseTexture(), x, y, WIDTH, HEIGHT);
        if (towerType.getTowerStats().getTopTexture() != null)
            Draw.drawTexture(towerType.getTowerStats().getTopTexture(), x, y, topTextureRotation);

        for (Projectile projectile : projectileList)
            projectile.draw();
    }

    protected float calculateAngleToTarget()
    {
        double tempAngle = Math.atan2(target.getY() - y, target.getX() - x);
        return (float) Math.toDegrees(tempAngle) + 90f;
    }

    protected Enemy calculateEnemyTarget()
    {
        HashMap<Float, Enemy> targetMap = new HashMap<>();
        float closest = Float.MAX_VALUE;
        if (targetStyle == TargetStyle.FURTHEST_TRAVELLED)
            closest = 0f;

        for (Enemy enemy : enemyList)
        {
            if (!enemy.isActive())
                continue;
            float distance = (float) Math.sqrt(Math.pow(x - enemy.getX(), 2) + Math.pow(y - enemy.getY(), 2));
            if (distance <= getTotalViewDistance())
            {
                if (targetStyle == TargetStyle.CLOSEST)
                    targetMap.put(distance, enemy);
                else if (targetStyle == TargetStyle.FURTHEST_TRAVELLED || targetStyle == TargetStyle.LEAST_TRAVELLED)
                    targetMap.put(enemy.getTravelled(), enemy);

                if (targetStyle == TargetStyle.CLOSEST && distance < closest)
                    closest = distance;
                else if (targetStyle == TargetStyle.FURTHEST_TRAVELLED && distance > closest)
                    closest = enemy.getTravelled();
                else if (targetStyle == TargetStyle.LEAST_TRAVELLED && distance < closest)
                    closest = enemy.getTravelled();
            }
        }

        if (!targetMap.isEmpty())
            return targetMap.get(closest);
        else
            return null;
    }

    protected void shoot()
    {
        if (target != null)
        {
            AudioManager.getInstance().play(Sound.CANNON_FIRE);
            timeSinceLastShot = 0;
            projectileList.add(new Projectile(this, towerType.getProjectileStats().getProjectileTexture(), tile, towerType.getProjectileStats().getSpeed(), getTotalDamage(), target));
        }
    }
}
