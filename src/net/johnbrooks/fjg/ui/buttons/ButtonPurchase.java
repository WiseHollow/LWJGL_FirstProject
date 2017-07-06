package net.johnbrooks.fjg.ui.buttons;

import net.johnbrooks.fjg.Player;
import net.johnbrooks.fjg.drawables.Draw;
import net.johnbrooks.fjg.drawables.GameTexture;
import net.johnbrooks.fjg.drawables.shapes.Rectangle;
import net.johnbrooks.fjg.drawables.tower.TowerType;
import org.newdawn.slick.opengl.Texture;

/**
 * Created by ieatl on 7/3/2017.
 */
public class ButtonPurchase extends Button
{
    private int cost;
    private Player player;
    private Rectangle previewBox;

    private TowerType purchaseType;

    public ButtonPurchase(int x, int y, Texture[] textures, Player player, int cost)
    {
        super(x, y, textures[0]);
        this.textures = textures;
        this.player = player;
        this.cost = cost;
        this.previewBox = new Rectangle(0, 0, 512, 512);
        this.previewBox.setColor(0.2117f, 0.2117f, 0.2117f);
        this.previewBox.setAlpha(0.4f);
    }

    public void setPurchaseType(TowerType towerType)
    {
        this.purchaseType = towerType;
    }

    @Override
    public void draw()
    {
        if (player.getCoins() >= cost)
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

        if (isHovered())
        {
            float sx = x - (GameTexture.DESC_WINDOW.getTexture().getImageWidth() * 0.5f) + 32;
            float sy = y - 172;
            Draw.drawTexture(GameTexture.DESC_WINDOW.getTexture(), sx, sy, 0);
            // Display the price above.
            sy += 6;
            Draw.getTinyFont().drawString(sx + 9, sy, "Type: " + purchaseType.getTowerName());
            sy += 20;
            Draw.getTinyFont().drawString(sx + 9, sy, "Damage: " + purchaseType.getProjectileStats().getDamage());
            sy += 20;
            Draw.getTinyFont().drawString(sx + 9, sy, "View Distance: " + purchaseType.getTowerStats().getViewDistance());
            sy += 20;
            Draw.getTinyFont().drawString(sx + 9, sy, "Bullet Speed: " + purchaseType.getProjectileStats().getSpeed());
            sy += 20;
            Draw.getTinyFont().drawString(sx + 9, sy, "Warm-Up: " + purchaseType.getTowerStats().getWarmUp() + " sec");
            sy += 20;
            Draw.getTinyFont().drawString(sx + 9, sy, "Cost: " + purchaseType.getTowerStats().getCost() + " coins");
            if (purchaseType.getProjectileStats().getHitSlowMultiplier() != 1f)
            {
                sy += 20;
                boolean freeze = purchaseType.getProjectileStats().getHitSlowMultiplier() < 1f;
                Draw.getTinyFont().drawString(sx + 9, sy, "Special: " + (freeze ? "Ice" : "Heat"));
            }
            else if (purchaseType.getProjectileStats().hasTracking())
            {
                sy += 20;
                Draw.getTinyFont().drawString(sx + 9, sy, "Special: Following bullets");
            }
        }
    }
}
