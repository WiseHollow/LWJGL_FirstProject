package net.johnbrooks.fjg.ui.buttons;

import net.johnbrooks.fjg.drawables.Draw;
import net.johnbrooks.fjg.drawables.GameTexture;
import net.johnbrooks.fjg.drawables.tower.Tower;
import net.johnbrooks.fjg.level.Level;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

/**
 * Created by ieatl on 7/7/2017.
 */
public class ButtonUpgrade extends Button
{
    private Level level;

    public ButtonUpgrade(int x, int y, Texture texture, Level level)
    {
        super(x, y, texture);
        this.level = level;
    }

    @Override
    public void update()
    {
        super.update();
    }

    @Override
    public void draw()
    {
        if (level.getPlayer().getSelectedTower() != null && level.getPlayer().getCoins() >= level.getPlayer().getSelectedTower().getTowerType().getTowerStats().getCost() && level.getPlayer().getSelectedTower().canUpgrade())
        {
            for (Texture texture : textures)
            {
                if (!isHovered())
                    Draw.drawTexture(texture, x, y, width, height);
                else
                    Draw.drawTexture(texture, x, y, (int) (width * sizePercent), (int) (height * sizePercent), 0, 0.8f, 0.8f, 0.8f);
            }
        }
        else
        {
            for (Texture texture : textures)
                Draw.drawTexture(texture, x, y, (int) (width * sizePercent), (int) (height * sizePercent), 0, 0.3f, 0.3f, 0.3f);
        }


        if (isHovered() && level.getPlayer().getSelectedTower() != null && level.getPlayer().getSelectedTower().canUpgrade())
        {
            float sx = x - (GameTexture.DESC_WINDOW.getTexture().getImageWidth() * 0.5f) + 32;
            float sy = y - 172;
            Draw.drawTexture(GameTexture.DESC_WINDOW.getTexture(), sx, sy, 0);
            // Display the price above.
            sy += 6;
            Draw.getTinyFont().drawString(sx + 9, sy, "Type: " + level.getPlayer().getSelectedTower().getTowerType().getTowerName());
            sy += 20;
            Draw.getTinyFont().drawString(sx + 9, sy, "Damage: " + level.getPlayer().getSelectedTower().getTotalDamage());
            Draw.getTinyFont().drawString(sx + GameTexture.DESC_WINDOW.getTexture().getTextureWidth() - 48, sy, "+" + level.getPlayer().getSelectedTower().getTowerType().getProjectileStats().getDamage(), Color.yellow);
            sy += 20;
            Draw.getTinyFont().drawString(sx + 9, sy, "View Distance: " + level.getPlayer().getSelectedTower().getTotalViewDistance());
            Draw.getTinyFont().drawString(sx + GameTexture.DESC_WINDOW.getTexture().getTextureWidth() - 48, sy, "+10", Color.yellow);
            sy += 20;
            Draw.getTinyFont().drawString(sx + 9, sy, "Bullet Speed: " + level.getPlayer().getSelectedTower().getTowerType().getProjectileStats().getSpeed());
            sy += 20;
            Draw.getTinyFont().drawString(sx + 9, sy, "Warm-Up: " + level.getPlayer().getSelectedTower().getTowerType().getTowerStats().getWarmUp() + " sec");
            sy += 20;
            Draw.getTinyFont().drawString(sx + 9, sy, "Cost: " + level.getPlayer().getSelectedTower().getTowerType().getTowerStats().getCost() + " coins");
            if (level.getPlayer().getSelectedTower().getTowerType().getProjectileStats().getHitSlowMultiplier() != 1f)
            {
                sy += 20;
                boolean freeze = level.getPlayer().getSelectedTower().getTowerType().getProjectileStats().getHitSlowMultiplier() < 1f;
                Draw.getTinyFont().drawString(sx + 9, sy, "Special: " + (freeze ? "Ice" : "Heat"));
            }
            else if (level.getPlayer().getSelectedTower().getTowerType().getProjectileStats().hasTracking())
            {
                sy += 20;
                Draw.getTinyFont().drawString(sx + 9, sy, "Special: Following bullets");
            }
        }
    }
}
