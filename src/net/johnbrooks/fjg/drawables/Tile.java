package net.johnbrooks.fjg.drawables;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by ieatl on 6/28/2017.
 */
public class Tile implements IDrawable
{
    private static HashMap<String, Texture> textureHashMap = new HashMap<>();

    public static Texture loadTexture(String name)
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

    private float x, y, width, height;
    private TileType tileType;
    private Texture texture;

    public Tile(float x, float y, float width, float height, TileType tileType)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.tileType = tileType;
        this.texture = loadTexture(tileType.textureName);
    }

    @Override
    public void draw()
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

    @Override
    public void update()
    {

    }
}
