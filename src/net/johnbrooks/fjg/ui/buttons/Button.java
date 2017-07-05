package net.johnbrooks.fjg.ui.buttons;

import net.johnbrooks.fjg.GameInput;
import net.johnbrooks.fjg.drawables.DisplayManager;
import net.johnbrooks.fjg.drawables.Draw;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

/**
 * Created by ieatl on 7/2/2017.
 */
public class Button
{
    protected int x, y, width, height;
    protected float sizePercent;
    protected Runnable clickEvent, hoverEvent;
    protected Texture[] textures;

    public Button(int x, int y, Texture texture)
    {
        this.textures = new Texture[] { texture };
        this.x = x;
        this.y = y;
        this.width = texture.getTextureWidth();
        this.height = texture.getTextureHeight();
        this.sizePercent = 1f;
    }

    public void update()
    {
        if (hoverEvent != null && isHovered())
        {
            hoverEvent.run();
        }
        if (clickEvent != null && isClicked())
        {
            GameInput.getInstance().setButtonDown(0, false);
            clickEvent.run();
        }
    }

    public void draw()
    {
        for (Texture texture : textures)
        {
            if (!isHovered())
                Draw.drawTexture(texture, x, y, (int) (width * sizePercent), (int) (height * sizePercent));
            else
                Draw.drawTextureWithRGB(texture, x, y, (int) (width * sizePercent), (int) (height * sizePercent), 0, 0.8f, 0.8f, 0.8f);
        }
    }

    public Button setOnClickEvent(Runnable runnable)
    {
        this.clickEvent = runnable;
        return this;
    }

    public Button setOnHoverEvent(Runnable runnable)
    {
        this.hoverEvent = runnable;
        return this;
    }

    public void setSizePercent(float sizePercent)
    {
        this.sizePercent = sizePercent;
    }

    public boolean isClicked()
    {
        if (GameInput.getInstance().isButtonDown(0))
        {
            int clickX = Mouse.getX();
            int clickY = Mouse.getY();

            if (clickX >= x && clickX <= x + textures[0].getImageWidth() &&
                    DisplayManager.getScreenHeight() - clickY >= y && DisplayManager.getScreenHeight() - clickY <= y + textures[0].getImageHeight())
                return true;
        }

        return false;
    }

    public boolean isHovered()
    {
        int clickX = Mouse.getX();
        int clickY = Mouse.getY();

        if (clickX >= x && clickX <= x + textures[0].getImageWidth() &&
                DisplayManager.getScreenHeight() - clickY >= y && DisplayManager.getScreenHeight() - clickY <= y + textures[0].getImageHeight())
            return true;

        return false;
    }

    public Texture[] getTextures()
    {
        return textures;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public float getWidth()
    {
        return width;
    }

    public float getHeight()
    {
        return height;
    }
}
