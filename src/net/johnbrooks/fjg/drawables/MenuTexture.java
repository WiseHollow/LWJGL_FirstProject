package net.johnbrooks.fjg.drawables;

import org.newdawn.slick.opengl.Texture;

import java.util.HashMap;

/**
 * Created by ieatl on 7/2/2017.
 */
public enum MenuTexture
{
    MAINMENU_BACKGROUND("res/menu/mainmenu.png");

    private static HashMap<String, Texture> textureHashMap = new HashMap<>();

    private String path;

    MenuTexture(String path)
    {
        this.path = path;
    }

    public Texture getTexture()
    {
        if (textureHashMap.containsKey(name()))
            return textureHashMap.get(name());

        Texture texture = Draw.loadTexture(path);
        if (texture != null)
            textureHashMap.put(name(), texture);

        return texture;
    }
}
