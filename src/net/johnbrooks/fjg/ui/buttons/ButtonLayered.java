package net.johnbrooks.fjg.ui.buttons;

import org.newdawn.slick.opengl.Texture;

/**
 * Created by ieatl on 7/2/2017.
 */
public class ButtonLayered extends Button
{
    public ButtonLayered(int x, int y, Texture... textures)
    {
        super(x, y, textures[0]);
        this.textures = textures;
    }
}
