package net.johnbrooks.fjg.state;

import net.johnbrooks.fjg.state.states.Editor;
import net.johnbrooks.fjg.state.states.Game;
import net.johnbrooks.fjg.state.states.MainMenu;

/**
 * Created by ieatl on 7/2/2017.
 */
public class StateManager
{
    private static StateManager instance;
    public static StateManager getInstance()
    {
        if (instance == null)
            instance = new StateManager();
        return instance;
    }

    private MainMenu mainMenu;
    private Game game;
    private Editor editor;

    private IGameState currentGameState;

    private StateManager()
    {
        mainMenu = new MainMenu();
        editor = new Editor();
        game = Game.getGame();
        setGameState(GameState.MAIN_MENU);
    }

    public void update()
    {
        currentGameState.update();
    }

    public void draw()
    {
        currentGameState.draw();
    }

    public void setGameState(GameState gameState)
    {
        switch(gameState)
        {
            case MAIN_MENU:
                currentGameState = mainMenu;
                break;
            case GAME:
                currentGameState = game;
                break;
            case EDITOR:
                currentGameState = editor;
                break;
        }
    }
}
