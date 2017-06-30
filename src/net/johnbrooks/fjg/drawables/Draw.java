package net.johnbrooks.fjg.drawables;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glLoadIdentity;

/**
 * Created by ieatl on 6/29/2017.
 */
public class Draw
{
    private static HashMap<String, Texture> textureHashMap = new HashMap<>();

    public static Texture loadTileTexture(String name)
    {
        if (textureHashMap.containsKey(name))
            return textureHashMap.get(name);

        Texture texture = null;

        InputStream inputStream = ResourceLoader.getResourceAsStream("res/tiles/" + name + ".png");
        try
        {
            texture = TextureLoader.getTexture("PNG", inputStream);
            textureHashMap.put(name, texture);
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return texture;
    }

    public static Texture loadEntityTexture(String name)
    {
        if (textureHashMap.containsKey(name))
            return textureHashMap.get(name);

        Texture texture = null;

        InputStream inputStream = ResourceLoader.getResourceAsStream("res/entities/" + name + ".png");
        try
        {
            texture = TextureLoader.getTexture("PNG", inputStream);
            textureHashMap.put(name, texture);
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return texture;
    }

    public static void drawTexture(Texture texture, int x, int y)
    {
        int width = texture.getImageWidth();
        int height = texture.getImageHeight();

        texture.bind();
        glTranslatef(x, y, 0);
        glBegin(GL_QUADS);

        glTexCoord2f(0, 0);
        glVertex2f(0, 0);

        glTexCoord2f(1, 0);
        glVertex2f(width, 0);

        glTexCoord2f(1, 1);
        glVertex2f(width, height);

        glTexCoord2f(0, 1);
        glVertex2f(0, height);

        glEnd();
        glLoadIdentity();
    }

    public static void drawTexture(Texture texture, int x, int y, int width, int height)
    {
        texture.bind();
        glTranslatef(x, y, 0);
        glBegin(GL_QUADS);

        glTexCoord2f(0, 0);
        glVertex2f(0, 0);

        glTexCoord2f(1, 0);
        glVertex2f(width, 0);

        glTexCoord2f(1, 1);
        glVertex2f(width, height);

        glTexCoord2f(0, 1);
        glVertex2f(0, height);

        glEnd();
        glLoadIdentity();
    }
}
