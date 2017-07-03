package net.johnbrooks.fjg.ui;

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
    protected String name;
    protected int x, y, width, height;
    protected Runnable clickEvent, hoverEvent;
    protected Texture[] textures;

    public Button(String name, int x, int y, Texture texture)
    {
        this.name = name;
        this.textures = new Texture[] { texture };
        this.x = x;
        this.y = y;
        this.width = texture.getImageWidth();
        this.height = texture.getImageHeight();
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
                Draw.drawTexture(texture, x, y, width, height);
            else
                Draw.drawTextureWithRGB(texture, x, y, 0, 0.8f, 0.8f, 0.8f);
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

    public boolean isClicked()
    {
        if (GameInput.getInstance().isButtonDown(0))
        {
            int clickX = Mouse.getX();
            int clickY = Mouse.getY();

            if (clickX >= x && clickX <= x + width &&
                    DisplayManager.getScreenHeight() - clickY >= y && DisplayManager.getScreenHeight() - clickY <= y + height)
                return true;
        }

        return false;
    }

    public boolean isHovered()
    {
        int clickX = Mouse.getX();
        int clickY = Mouse.getY();

        if (clickX >= x && clickX <= x + width &&
                DisplayManager.getScreenHeight() - clickY >= y && DisplayManager.getScreenHeight() - clickY <= y + height)
            return true;

        return false;
    }

    public String getName()
    {
        return name;
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
