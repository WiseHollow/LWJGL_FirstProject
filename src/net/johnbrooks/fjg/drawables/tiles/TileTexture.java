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

    DIRT("res/tiles/dirt.png"),
    DIRT_GRASS_BOTTOM("res/tiles/dirt_grassBottom.png"), DIRT_GRASS_BOTTOM_LEFT("res/tiles/dirt_grassBottomLeft.png"), DIRT_GRASS_BOTTOM_RIGHT("res/tiles/dirt_grassBottomRight.png"),
    DIRT_GRASS_LEFT("res/tiles/dirt_grassLeft.png"), DIRT_GRASS_RIGHT("res/tiles/dirt_grassRight.png"), DIRT_GRASS_TOP("res/tiles/dirt_grassTop.png"),
    DIRT_GRASS_TOP_LEFT("res/tiles/dirt_grassTopLeft.png"), DIRT_GRASS_TOP_RIGHT("res/tiles/dirt_grassTopRight.png"),
    DIRT_GRASS_CORNER_A("res/tiles/dirt_grassCornerA.png"), DIRT_GRASS_CORNER_B("res/tiles/dirt_grassCornerB.png"),
    DIRT_GRASS_CORNER_C("res/tiles/dirt_grassCornerC.png"), DIRT_GRASS_CORNER_D("res/tiles/dirt_grassCornerD.png"),
    
    WATER("res/tiles/water.png"),

    SAND("res/tiles/sand.png"),
    SAND_GRASS_BOTTOM("res/tiles/sand_grassBottom.png"), SAND_GRASS_BOTTOM_LEFT("res/tiles/sand_grassBottomLeft.png"), SAND_GRASS_BOTTOM_RIGHT("res/tiles/sand_grassBottomRight.png"),
    SAND_GRASS_LEFT("res/tiles/sand_grassLeft.png"), SAND_GRASS_RIGHT("res/tiles/sand_grassRight.png"), SAND_GRASS_TOP("res/tiles/sand_grassTop.png"),
    SAND_GRASS_TOP_LEFT("res/tiles/sand_grassTopLeft.png"), SAND_GRASS_TOP_RIGHT("res/tiles/sand_grassTopRight.png"),
    SAND_GRASS_CORNER_A("res/tiles/sand_grassCornerA.png"), SAND_GRASS_CORNER_B("res/tiles/sand_grassCornerB.png"),
    SAND_GRASS_CORNER_C("res/tiles/sand_grassCornerC.png"), SAND_GRASS_CORNER_D("res/tiles/sand_grassCornerD.png"),
    
    SAND_WATER_BOTTOM("res/tiles/sand_waterBottom.png"), SAND_WATER_BOTTOM_LEFT("res/tiles/sand_waterBottomLeft.png"), SAND_WATER_BOTTOM_RIGHT("res/tiles/sand_waterBottomRight.png"),
    SAND_WATER_LEFT("res/tiles/sand_waterLeft.png"), SAND_WATER_RIGHT("res/tiles/sand_waterRight.png"), SAND_WATER_TOP("res/tiles/sand_waterTop.png"),
    SAND_WATER_TOP_LEFT("res/tiles/sand_waterTopLeft.png"), SAND_WATER_TOP_RIGHT("res/tiles/sand_waterTopRight.png"),
    SAND_WATER_CORNER_A("res/tiles/sand_waterCornerA.png"), SAND_WATER_CORNER_B("res/tiles/sand_waterCornerB.png"),
    SAND_WATER_CORNER_C("res/tiles/sand_waterCornerC.png"), SAND_WATER_CORNER_D("res/tiles/sand_waterCornerD.png"),

    STONE("res/tiles/stone.png"),
    STONE_GRASS_BOTTOM("res/tiles/stone_grassBottom.png"), STONE_GRASS_BOTTOM_LEFT("res/tiles/stone_grassBottomLeft.png"), STONE_GRASS_BOTTOM_RIGHT("res/tiles/stone_grassBottomRight.png"),
    STONE_GRASS_LEFT("res/tiles/stone_grassLeft.png"), STONE_GRASS_RIGHT("res/tiles/stone_grassRight.png"), STONE_GRASS_TOP("res/tiles/stone_grassTop.png"),
    STONE_GRASS_TOP_LEFT("res/tiles/stone_grassTopLeft.png"), STONE_GRASS_TOP_RIGHT("res/tiles/stone_grassTopRight.png"),
    STONE_GRASS_CORNER_A("res/tiles/stone_grassCornerA.png"), STONE_GRASS_CORNER_B("res/tiles/stone_grassCornerB.png"),
    STONE_GRASS_CORNER_C("res/tiles/stone_grassCornerC.png"), STONE_GRASS_CORNER_D("res/tiles/stone_grassCornerD.png"),

    GRASS_WATER_BOTTOM("res/tiles/grass_waterBottom.png"), GRASS_WATER_BOTTOM_LEFT("res/tiles/grass_waterBottomLeft.png"), GRASS_WATER_BOTTOM_RIGHT("res/tiles/grass_waterBottomRight.png"),
    GRASS_WATER_LEFT("res/tiles/grass_waterLeft.png"), GRASS_WATER_RIGHT("res/tiles/grass_waterRight.png"), GRASS_WATER_TOP("res/tiles/grass_waterTop.png"),
    GRASS_WATER_TOP_LEFT("res/tiles/grass_waterTopLeft.png"), GRASS_WATER_TOP_RIGHT("res/tiles/grass_waterTopRight.png"),
    GRASS_WATER_CORNER_A("res/tiles/grass_waterCornerA.png"), GRASS_WATER_CORNER_B("res/tiles/grass_waterCornerB.png"),
    GRASS_WATER_CORNER_C("res/tiles/grass_waterCornerC.png"), GRASS_WATER_CORNER_D("res/tiles/grass_waterCornerD.png"),



    ;

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
