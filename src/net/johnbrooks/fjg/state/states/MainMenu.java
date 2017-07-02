package net.johnbrooks.fjg.state.states;

import net.johnbrooks.fjg.drawables.Draw;
import net.johnbrooks.fjg.drawables.MenuTexture;
import net.johnbrooks.fjg.state.IGameState;
import org.newdawn.slick.opengl.Texture;

/**
 * Created by ieatl on 7/2/2017.
 */
public class MainMenu implements IGameState
{
    private Texture background;

    public MainMenu()
    {
        background = MenuTexture.MAINMENU_BACKGROUND.getTexture();
    }

    @Override
    public void update()
    {

    }

    @Override
    public void draw()
    {
        Draw.drawTexture(background, 0, 0, 2048, 1024);
    }

    @Override
    public void exit()
    {

    }
}
