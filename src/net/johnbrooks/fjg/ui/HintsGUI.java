package net.johnbrooks.fjg.ui;

import net.johnbrooks.fjg.drawables.DisplayManager;
import net.johnbrooks.fjg.drawables.Draw;
import net.johnbrooks.fjg.state.states.MainMenu;
import net.johnbrooks.fjg.ui.buttons.Button;
import org.newdawn.slick.opengl.Texture;

/**
 * Created by ieatl on 7/10/2017.
 */
public class HintsGUI extends UI
{
    private MainMenu mainMenu;
    private Texture texture;
    private int x, y;

    public HintsGUI(MainMenu mainMenu)
    {
        this.mainMenu = mainMenu;
        texture = Draw.loadTexture("res/menu/hints.png");
        x = (int) (DisplayManager.getScreenWidth() * 0.5f) - (int) (texture.getImageWidth() * 0.5f);
        y = (int) (DisplayManager.getScreenHeight() * 0.5f) - (int) (texture.getImageHeight() * 0.5f) - 64;

        init();
    }

    private void init()
    {
        Button exit = new Button(x + texture.getImageWidth() - 48, y + 32, Draw.loadTexture("res/buttons/reject.png"));
        exit.setOnClickEvent(() ->
        {
            mainMenu.setShowHints(false);
        });
        addButtons(exit);
    }

    @Override
    public void draw()
    {
        Draw.drawTexture(texture, x, y, 0);
        super.draw();
    }
}
