package net.johnbrooks.fjg.state.states;

import net.johnbrooks.fjg.audio.AudioManager;
import net.johnbrooks.fjg.audio.Music;
import net.johnbrooks.fjg.drawables.DisplayManager;
import net.johnbrooks.fjg.drawables.Draw;
import net.johnbrooks.fjg.drawables.MenuTexture;
import net.johnbrooks.fjg.state.GameState;
import net.johnbrooks.fjg.state.IGameState;
import net.johnbrooks.fjg.state.StateManager;
import net.johnbrooks.fjg.ui.buttons.Button;
import net.johnbrooks.fjg.ui.UI;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.Log;

/**
 * Created by ieatl on 7/2/2017.
 */
public class MainMenu implements IGameState
{
    private Texture background;
    private UI ui;

    public MainMenu()
    {
        background = MenuTexture.MAINMENU_BACKGROUND.getTexture();
        ui = new UI();
        init();
    }

    private void init()
    {
        Texture buttonTextureGame = Draw.loadTexture("res/buttons/play.png");
        Texture buttonTextureEditor = Draw.loadTexture("res/buttons/editor.png");
        Texture buttonTextureQuit = Draw.loadTexture("res/buttons/quit.png");

        Button playButton = new Button((int) (DisplayManager.getScreenWidth() * 0.5f - (buttonTextureGame.getImageWidth() * 0.5f)), (int) (DisplayManager.getScreenHeight() * 0.65f), buttonTextureGame);
        Button editorButton = new Button((int) (DisplayManager.getScreenWidth() * 0.5f - (buttonTextureEditor.getImageWidth() * 0.5f)), (int) (DisplayManager.getScreenHeight() * 0.75f), buttonTextureEditor);
        Button quitButton = new Button((int) (DisplayManager.getScreenWidth() * 0.5f - (buttonTextureQuit.getImageWidth() * 0.5f)), (int) (DisplayManager.getScreenHeight() * 0.85f), buttonTextureQuit);

        playButton.setOnClickEvent(() ->
        {
            StateManager.getInstance().setGameState(GameState.GAME);
            Game.getInstance().getCurrentLevel().init();
        });

        editorButton.setOnClickEvent(() ->
        {
            StateManager.getInstance().setGameState(GameState.EDITOR);
        });

        quitButton.setOnClickEvent(() ->
        {
            System.exit(0);
            Log.info("Exiting application...");
        });

        ui.addButtons(playButton);
        ui.addButtons(editorButton);
        ui.addButtons(quitButton);

        AudioManager.getInstance().play(Music.COUNTRY_ADVENTURE, true);
    }

    @Override
    public void update()
    {
        ui.update();
    }

    @Override
    public void draw()
    {
        Draw.drawTexture(background, 0, 0, 2048, 1024);
        ui.draw();
    }

    @Override
    public void exit()
    {

    }
}
