package net.johnbrooks.fjg.ui.buttons;

import net.johnbrooks.fjg.GameInput;
import net.johnbrooks.fjg.drawables.Draw;
import org.newdawn.slick.opengl.Texture;

/**
 * Created by ieatl on 7/3/2017.
 */
public class ButtonToggle extends Button
{
    private Texture disabledTexture;
    private boolean pressed;

    public ButtonToggle(int x, int y, Texture enabledTexture, Texture disabledTexture)
    {
        super(x, y, enabledTexture);
        this.disabledTexture = disabledTexture;
        this.pressed = false;
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
            pressed = !pressed;
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
            Draw.drawTexture(!pressed ? textures[0] : disabledTexture, x, y, width, height);
        else
            Draw.drawTexture(!pressed ? textures[0] : disabledTexture, x, y, (int) (width * sizePercent), (int) (height * sizePercent), 0, 0.8f, 0.8f, 0.8f);
    }
}
