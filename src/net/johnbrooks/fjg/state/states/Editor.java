package net.johnbrooks.fjg.state.states;

import net.johnbrooks.fjg.level.levels.LevelEditor;
import net.johnbrooks.fjg.state.IGameState;

/**
 * Created by ieatl on 7/2/2017.
 */
public class Editor implements IGameState
{
    private static Editor instance;
    public static Editor getInstance()
    {
        if (instance == null)
            instance = new Editor();
        return instance;
    }

    private LevelEditor levelEditor;

    private Editor()
    {

        init();
    }

    private void init()
    {
        levelEditor = new LevelEditor();
        levelEditor.init();
    }

    @Override
    public void update()
    {
        levelEditor.update();
    }

    @Override
    public void draw()
    {
        levelEditor.draw();
    }

    @Override
    public void exit()
    {

    }
}
