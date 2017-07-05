package net.johnbrooks.fjg.ui.buttons;

import net.johnbrooks.fjg.Player;
import net.johnbrooks.fjg.drawables.Draw;
import org.newdawn.slick.opengl.Texture;

/**
 * Created by ieatl on 7/3/2017.
 */
public class ButtonPurchase extends Button
{
    private int cost;
    private Player player;

    public ButtonPurchase(int x, int y, Texture[] textures, Player player, int cost)
    {
        super(x, y, textures[0]);
        this.textures = textures;
        this.player = player;
        this.cost = cost;
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
                    Draw.drawTextureWithRGB(texture, x, y, 0, 0.8f, 0.8f, 0.8f);
            }
        }
        else
        {
            for (Texture texture : textures)
                Draw.drawTextureWithRGB(texture, x, y, 0, 0.3f, 0.3f, 0.3f);
        }

    }
}
