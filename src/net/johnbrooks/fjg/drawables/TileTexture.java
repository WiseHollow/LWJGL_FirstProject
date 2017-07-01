package net.johnbrooks.fjg.drawables;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * Created by ieatl on 6/30/2017.
 */
public enum TileTexture
{
    GRASS("res/tiles/grass.png"), DIRT("res/tiles/dirt.png"), WATER("res/tiles/water.png");

    private static HashMap<String, Texture> textureHashMap = new HashMap<>();

    private String path;

    TileTexture(String path)
    {
        this.path = path;
    }

    public Texture getTexture()
    {
        if (textureHashMap.containsKey(name()))
            return textureHashMap.get(name());

        Texture texture = null;

        InputStream inputStream = ResourceLoader.getResourceAsStream(path);
        try
        {
            texture = TextureLoader.getTexture("PNG", inputStream);
            textureHashMap.put(name(), texture);
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return texture;
    }
}