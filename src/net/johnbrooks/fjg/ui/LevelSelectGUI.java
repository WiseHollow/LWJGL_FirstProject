package net.johnbrooks.fjg.ui;

import net.johnbrooks.fjg.drawables.DisplayManager;
import net.johnbrooks.fjg.drawables.Draw;
import net.johnbrooks.fjg.ui.buttons.Button;
import org.newdawn.slick.opengl.Texture;

import java.util.List;

/**
 * Created by ieatl on 7/7/2017.
 */
public class LevelSelectGUI extends UI
{
    private Texture background;
    private float x, y;

    private List<Button> levelButtonList;

    public LevelSelectGUI()
    {
        background = Draw.loadTexture("");
        x = (DisplayManager.getScreenWidth() * 0.5f) - (background.getTextureWidth() * 0.5f);
        y = (DisplayManager.getScreenHeight() * 0.5f) - (background.getTextureHeight() * 0.5f);
    }

    private void init()
    {

    }
}
