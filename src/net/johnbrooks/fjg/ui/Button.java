package net.johnbrooks.fjg.ui;

import net.johnbrooks.fjg.drawables.DisplayManager;
import net.johnbrooks.fjg.drawables.Draw;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.Log;

/**
 * Created by ieatl on 7/2/2017.
 */
public class Button
{
    private String name;
    private Texture[] textures;
    private int x, y, width, height;
    private Runnable clickEvent;

    public Button(String name, int x, int y, int width, int height, Texture... textures)
    {
        this.name = name;
        this.textures = textures;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Button(String name, int x, int y, Texture... textures)
    {
        this.name = name;
        this.textures = textures;
        this.x = x;
        this.y = y;
        this.width = textures[0].getImageWidth();
        this.height = textures[0].getImageHeight();
    }

    public void update()
    {
        if (isClicked())
        {
            if (clickEvent != null)
                clickEvent.run();
        }
    }

    public void draw()
    {
        for (Texture texture : textures)
            Draw.drawTexture(texture, x, y, texture.getImageWidth(), texture.getImageHeight());
    }

    public Button setOnClickEvent(Runnable runnable)
    {
        this.clickEvent = runnable;
        return this;
    }

    public boolean isClicked()
    {
        if (Mouse.isButtonDown(0))
        {
            int clickX = Mouse.getX();
            int clickY = Mouse.getY();

            if (clickX >= x && clickX <= x + width &&
                    DisplayManager.getScreenHeight() - clickY >= y && DisplayManager.getScreenHeight() - clickY <= y + height)
                return true;
        }

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

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }
}
