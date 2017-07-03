package net.johnbrooks.fjg.ui;

import net.johnbrooks.fjg.GameInput;
import net.johnbrooks.fjg.drawables.Draw;
import org.newdawn.slick.opengl.Texture;

/**
 * Created by ieatl on 7/3/2017.
 */
public class ButtonToggle extends Button
{
    private Texture disabledTexture;
    private boolean enabled;

    public ButtonToggle(String name, int x, int y, Texture enabledTexture, Texture disabledTexture)
    {
        super(name, x, y, enabledTexture);
        this.disabledTexture = disabledTexture;
        this.enabled = true;
    }

    @Override
    public void update()
    {
        if (hoverEvent != null && isHovered())
        {
            hoverEvent.run();
        }
        if (isClicked())
        {
            enabled = !enabled;
            if (clickEvent != null)
            {
                GameInput.getInstance().setButtonDown(0, false);
                clickEvent.run();
            }
        }
    }

    @Override
    public void draw()
    {
        if (!isHovered())
            Draw.drawTexture(enabled ? textures[0] : disabledTexture, x, y, width, height);
        else
            Draw.drawTextureWithRGB(enabled ? textures[0] : disabledTexture, x, y, 0, 0.8f, 0.8f, 0.8f);
    }
}
