package net.johnbrooks.fjg.state.states;

import net.johnbrooks.fjg.Updater;
import net.johnbrooks.fjg.audio.AudioManager;
import net.johnbrooks.fjg.audio.Music;
import net.johnbrooks.fjg.drawables.DisplayManager;
import net.johnbrooks.fjg.drawables.Draw;
import net.johnbrooks.fjg.drawables.MenuTexture;
import net.johnbrooks.fjg.level.levels.LevelSurvival;
import net.johnbrooks.fjg.state.GameState;
import net.johnbrooks.fjg.state.IGameState;
import net.johnbrooks.fjg.state.StateManager;
import net.johnbrooks.fjg.ui.HintsGUI;
import net.johnbrooks.fjg.ui.buttons.Button;
import net.johnbrooks.fjg.ui.UI;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.Log;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by ieatl on 7/2/2017.
 */
public class MainMenu implements IGameState
{
    private Texture background;
    private UI ui;
    private HintsGUI hintsGUI;
    private boolean showHints;

    public MainMenu()
    {
        background = MenuTexture.MAINMENU_BACKGROUND.getTexture();
        ui = new UI();
        hintsGUI = new HintsGUI(this);
        showHints = false;
        init();
    }

    private void init()
    {
        Texture buttonTextureGame = Draw.loadTexture("res/buttons/play.png");
        Texture buttonTextureSurvival = Draw.loadTexture("res/buttons/survival.png");
        Texture buttonTextureEditor = Draw.loadTexture("res/buttons/editor.png");
        Texture buttonTextureHints = Draw.loadTexture("res/buttons/hints.png");
        Texture buttonTextureQuit = Draw.loadTexture("res/buttons/quit.png");

        Button editorButton = new Button(DisplayManager.getScreenWidth() - (buttonTextureGame.getImageWidth()) - 20, DisplayManager.getScreenHeight() - buttonTextureEditor.getImageHeight() - 20, buttonTextureEditor);
        Button hintsButton = new Button(DisplayManager.getScreenWidth() - (buttonTextureHints.getImageWidth()) - 20, DisplayManager.getScreenHeight() - buttonTextureHints.getImageHeight() - 20 - buttonTextureEditor.getImageHeight(), buttonTextureHints);
        Button playButton = new Button((int) (DisplayManager.getScreenWidth() * 0.5f - (buttonTextureGame.getImageWidth() * 0.5f)), (int) (DisplayManager.getScreenHeight() * 0.65f), buttonTextureGame);
        Button survivalButton = new Button((int) (DisplayManager.getScreenWidth() * 0.5f - (buttonTextureEditor.getImageWidth() * 0.5f)), (int) (DisplayManager.getScreenHeight() * 0.75f), buttonTextureSurvival);
        Button quitButton = new Button((int) (DisplayManager.getScreenWidth() * 0.5f - (buttonTextureQuit.getImageWidth() * 0.5f)), (int) (DisplayManager.getScreenHeight() * 0.85f), buttonTextureQuit);

        playButton.setOnClickEvent(() ->
        {
            StateManager.getInstance().setGameState(GameState.GAME);
            Game.getInstance().getLevelManager().getCurrentLevel().init();
            Game.getInstance().getLevelManager().getCurrentLevel().calculateWaypoints();
        });

        survivalButton.setOnClickEvent(() ->
        {
            Game.getInstance().resetLevelManager(new LevelSurvival());
            StateManager.getInstance().setGameState(GameState.GAME);
            Game.getInstance().getLevelManager().getCurrentLevel().init();
            Game.getInstance().getLevelManager().getCurrentLevel().calculateWaypoints();
        });

        editorButton.setOnClickEvent(() ->
        {
            StateManager.getInstance().setGameState(GameState.EDITOR);
        });

        hintsButton.setOnClickEvent(() ->
        {
            showHints = true;
        });

        quitButton.setOnClickEvent(() ->
        {
            System.exit(0);
            Log.info("Exiting application...");
        });

        ui.addButtons(playButton, editorButton, quitButton, survivalButton, hintsButton);

        if (Updater.isUpdateAvailable())
        {
            Texture updateTexture = Draw.loadTexture("res/hud/updateAvailable.png");
            Button updateButton = new Button(DisplayManager.getScreenWidth() - (updateTexture.getImageWidth()) - 20, DisplayManager.getScreenHeight() - updateTexture.getImageHeight() - 20 - buttonTextureEditor.getImageHeight() - buttonTextureHints.getImageHeight(), updateTexture);
            ui.addButtons(updateButton);

            updateButton.setOnClickEvent(() ->
            {
                if(Desktop.isDesktopSupported())
                {
                    try
                    {
                        Desktop.getDesktop().browse(new URI("http://www.johnbrooks.net"));
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    } catch (URISyntaxException e)
                    {
                        e.printStackTrace();
                    }
                }
            });
        }

        AudioManager.getInstance().play(Music.RAIL_JET, true);
    }

    @Override
    public void update()
    {
        if (!showHints)
            ui.update();
        else
            hintsGUI.update();
    }

    @Override
    public void draw()
    {
        Draw.drawTexture(background, 0, 0, 0);
        ui.draw();

        Draw.getTinyFont().drawString(0, 0, "Programming, development, and design: John Brooks (johnbrooks.net)");
        Draw.getTinyFont().drawString(0, 18, "Art: Kenney.nl, and others at opengameart.org.");
        Draw.getTinyFont().drawString(0, 36, "Music: BlueBeat-ERH, RailJet-DST, ");
        Draw.getTinyFont().drawString(57, 54, "TowerDefenseTheme-DST");

        if (showHints)
            hintsGUI.draw();
    }

    @Override
    public void exit()
    {

    }

    public void setShowHints(boolean showHints)
    {
        this.showHints = showHints;
    }
}
