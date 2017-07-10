package net.johnbrooks.fjg.ui;

import net.johnbrooks.fjg.audio.AudioManager;
import net.johnbrooks.fjg.drawables.DisplayManager;
import net.johnbrooks.fjg.drawables.Draw;
import net.johnbrooks.fjg.level.Level;
import net.johnbrooks.fjg.ui.buttons.Button;
import net.johnbrooks.fjg.ui.buttons.ButtonToggle;
import org.newdawn.slick.opengl.Texture;

/**
 * Created by ieatl on 7/3/2017.
 */
public class SettingsGUI extends UI
{
    private Level level;
    private Texture backgroundTexture;
    private int x, y, width, height;

    public SettingsGUI(Level level)
    {
        super();
        this.level = level;
        this.backgroundTexture = Draw.loadTexture("res/hud/settingsHud.png");
        this.x = DisplayManager.getScreenWidth() - backgroundTexture.getImageWidth();
        this.y = DisplayManager.getScreenHeight() - backgroundTexture.getImageHeight();
        this.width = backgroundTexture.getImageWidth();
        this.height = backgroundTexture.getImageHeight();
        this.visible = false;

        init();
    }

    private void init()
    {
        Button music = new ButtonToggle(DisplayManager.getScreenWidth() - 74, DisplayManager.getScreenHeight() - 268,
                Draw.loadTexture("res/hud/settingsNav_music.png"), Draw.loadTexture("res/hud/settingsNav_musicOff.png"))
                .setOnClickEvent(() ->
                {
                    if (AudioManager.getInstance().isPlaying())
                        AudioManager.getInstance().stopAll();
                    else
                        AudioManager.getInstance().playRandom(true);
                });
        Button sound = new ButtonToggle(DisplayManager.getScreenWidth() - 74, DisplayManager.getScreenHeight() - 203,
                Draw.loadTexture("res/hud/settingsNav_sound.png"), Draw.loadTexture("res/hud/settingsNav_soundOff.png"))
                .setOnClickEvent(() ->
                {
                    AudioManager.getInstance().setMutedSound(!AudioManager.getInstance().isMutedSound());
                });

        addButtons(music, sound);
    }

    @Override
    public void draw()
    {
        if (visible)
        {
            Draw.drawTexture(backgroundTexture, x, y, backgroundTexture.getImageWidth(), backgroundTexture.getImageHeight());
            String health = String.valueOf(level.getPlayer().getHealth());
            String coins = String.valueOf(level.getPlayer().getCoins());
            Draw.getLargeFont().drawString(78, 893, health);
            Draw.getLargeFont().drawString(215, 893, coins);

            super.draw();
        }
    }
}
