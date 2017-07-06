package net.johnbrooks.fjg.drawables.tiles;

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
    GRASS("res/tiles/grass.png"),
    DIRT("res/tiles/dirt.png"), DIRT_GRASS_BOTTOM("res/tiles/dirt_grassBottom.png"), DIRT_GRASS_BOTTOM_LEFT("res/tiles/dirt_grassBottomLeft.png"), DIRT_GRASS_BOTTOM_RIGHT("res/tiles/dirt_grassBottomRight.png"),
    DIRT_GRASS_LEFT("res/tiles/dirt_grassLeft.png"), DIRT_GRASS_RIGHT("res/tiles/dirt_grassRight.png"), DIRT_GRASS_TOP("res/tiles/dirt_grassTop.png"),
    DIRT_GRASS_TOP_LEFT("res/tiles/dirt_grassTopLeft.png"), DIRT_GRASS_TOP_RIGHT("res/tiles/dirt_grassTopRight.png"),
    WATER("res/tiles/water.png"),
    SAND("res/tiles/sand.png"),
    STONE("res/tiles/stone.png");

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
