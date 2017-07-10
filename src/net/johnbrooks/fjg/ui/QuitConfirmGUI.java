package net.johnbrooks.fjg.ui;

import net.johnbrooks.fjg.drawables.DisplayManager;
import net.johnbrooks.fjg.drawables.Draw;
import net.johnbrooks.fjg.level.Level;
import net.johnbrooks.fjg.level.LevelManager;
import net.johnbrooks.fjg.state.GameState;
import net.johnbrooks.fjg.state.StateManager;
import net.johnbrooks.fjg.ui.buttons.Button;
import org.newdawn.slick.opengl.Texture;

/**
 * Created by ieatl on 7/10/2017.
 */
public class QuitConfirmGUI extends UI
{
    private Texture texture;
    private SettingsGUI settingsGUI;
    private Level level;
    private int x, y;
    private boolean enabled;

    public QuitConfirmGUI(SettingsGUI settingsGUI, Level level)
    {
        this.texture = Draw.loadTexture("res/menu/quitConfirmation.png");
        this.settingsGUI = settingsGUI;
        this.level = level;
        x = (int) (DisplayManager.getScreenWidth() * 0.5f) - (int) (texture.getImageWidth() * 0.5f);
        y = (int) (DisplayManager.getScreenHeight() * 0.5f) - (int) (texture.getImageHeight() * 0.5f) - 64;
        this.enabled = false;
        init();
    }

    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }

    private void init()
    {
        Button accept = new Button(x + 65, y + 168 - 25, Draw.loadTexture("res/buttons/accept.png"));
        Button reject = new Button(x + 225, y + 168 - 25, Draw.loadTexture("res/buttons/reject.png"));

        accept.setOnClickEvent(() ->
        {
            enabled = false;
            StateManager.getInstance().setGameState(GameState.MAIN_MENU);
            level.reset();
        });

        reject.setOnClickEvent(() ->
        enabled = false);

        addButtons(accept, reject);
    }

    @Override
    public void update()
    {
        if (enabled)
        {
            super.update();
        }
    }

    @Override
    public void draw()
    {
        if (enabled)
        {
            Draw.drawTexture(texture, x, y, 0);
            super.draw();
        }
    }
}
