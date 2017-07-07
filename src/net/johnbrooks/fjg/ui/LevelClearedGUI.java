package net.johnbrooks.fjg.ui;

import net.johnbrooks.fjg.Player;
import net.johnbrooks.fjg.drawables.DisplayManager;
import net.johnbrooks.fjg.drawables.Draw;
import net.johnbrooks.fjg.level.Level;
import net.johnbrooks.fjg.state.GameState;
import net.johnbrooks.fjg.state.StateManager;
import net.johnbrooks.fjg.state.states.Game;
import net.johnbrooks.fjg.ui.buttons.Button;
import org.newdawn.slick.opengl.Texture;

/**
 * Created by ieatl on 7/7/2017.
 */
public class LevelClearedGUI extends UI
{
    private Texture background;
    private int x, y;
    private Player player;
    private Level level;

    public LevelClearedGUI(Level level)
    {
        this.background = Draw.loadTexture("res/menu/levelCleared.png");
        this.x = (int) ((DisplayManager.getScreenWidth() * 0.5f) - (background.getImageWidth() * 0.5f));
        this.y = (int) ((DisplayManager.getScreenHeight() * 0.5f) - (background.getImageHeight() * 0.5f));
        this.level = level;
        this.player = level.getPlayer();
        init();
    }

    private void init()
    {
        Button nextLevelButton = new Button(x + 355, y + 405, Draw.loadTexture("res/buttons/nextLevel.png"));
        nextLevelButton.setOnClickEvent(() ->
        {
            Game.getInstance().getLevelManager().nextLevel();
            Game.getInstance().getLevelManager().getCurrentLevel().calculateWaypoints();
        });
        Button reloadLevelButton = new Button(x + 225, y + 405, Draw.loadTexture("res/buttons/reload.png"));
        reloadLevelButton.setOnClickEvent(() ->
        {
            // Reset current level
            level.reset();
        });
        Button menuButton = new Button(x + 95, y + 405, Draw.loadTexture("res/buttons/menu.png"));
        menuButton.setOnClickEvent(() ->
        {
            StateManager.getInstance().setGameState(GameState.MAIN_MENU);
            Game.getInstance().resetLevelManager();
        });
        addButtons(nextLevelButton, reloadLevelButton, menuButton);
    }

    @Override
    public void draw()
    {
        Draw.drawTexture(background, x, y, 0);
        super.draw();
    }

    @Override
    public void update()
    {
        super.update();
    }
}
