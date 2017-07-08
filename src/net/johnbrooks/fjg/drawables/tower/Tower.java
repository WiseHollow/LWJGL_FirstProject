package net.johnbrooks.fjg.drawables.tower;

import net.johnbrooks.fjg.Clock;
import net.johnbrooks.fjg.GameInput;
import net.johnbrooks.fjg.audio.AudioManager;
import net.johnbrooks.fjg.audio.Sound;
import net.johnbrooks.fjg.drawables.DisplayManager;
import net.johnbrooks.fjg.drawables.Draw;
import net.johnbrooks.fjg.drawables.GameTexture;
import net.johnbrooks.fjg.drawables.entities.Enemy;
import net.johnbrooks.fjg.drawables.tiles.Tile;
import net.johnbrooks.fjg.level.Level;
import net.johnbrooks.fjg.ui.buttons.Button;
import net.johnbrooks.fjg.ui.buttons.ButtonSell;
import net.johnbrooks.fjg.ui.buttons.ButtonUpgrade;

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

    public boolean upgradeTower()
    {
        if (power < 4 && level.getPlayer().getCoins() >= towerType.getTowerStats().getCost())
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
        float warmUpTime = towerType.getTowerStats().getWarmUp() - (power * 0.2f);
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

        //upgradeButton.update();
        //sellButton.update();
    }

    public void draw()
    {
        if (towerType.getTowerStats().getBaseTexture() != null)
            Draw.drawTexture(towerType.getTowerStats().getBaseTexture(), x, y, WIDTH, HEIGHT);
        if (towerType.getTowerStats().getTopTexture() != null)
            Draw.drawTexture(towerType.getTowerStats().getTopTexture(), x, y, topTextureRotation);

        for (Projectile projectile : projectileList)
            projectile.draw();

        //upgradeButton.draw();
        //sellButton.draw();
    }

    protected float calculateAngleToTarget()
    {
        double tempAngle = Math.atan2(target.getY() - y, target.getX() - x);
        return (float) Math.toDegrees(tempAngle) + 90f;
    }

    protected Enemy calculateEnemyTarget()
    {
        HashMap<Float, Enemy> distanceMap = new HashMap<>();
        float closest = getTotalViewDistance() + 1;

        for (Enemy enemy : enemyList)
        {
            if (!enemy.isActive())
                continue;
            float distance = (float) Math.sqrt(Math.pow(x - enemy.getX(), 2) + Math.pow(y - enemy.getY(), 2));
            if (distance <= getTotalViewDistance())
            {
                distanceMap.put(distance, enemy);
                if (distance < closest)
                    closest = distance;
            }
        }

        if (!distanceMap.isEmpty())
            return distanceMap.get(closest);
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
