package net.johnbrooks.fjg.drawables.entities;

import net.johnbrooks.fjg.drawables.Draw;
import org.newdawn.slick.opengl.Texture;

/**
 * Created by ieatl on 7/5/2017.
 */
public enum EnemyTexture
{
    MOUSE("res/entities/mouse.png", "res/entities/mouse_walk.png"), SPINNER("res/entities/spinner.png", "res/entities/spinner_spin.png"),
    SLIME("res/entities/slimeGreen.png", "res/entities/slimeGreen_walk.png"), SLIME_PINK("res/entities/slimePink.png", "res/entities/slimePink_walk.png"), SLIME_BLUE("res/entities/slimeBlue.png", "res/entities/slimeBlue_walk.png");

    Texture texture;
    Texture textureAlt;

    EnemyTexture(String texture, String textureAlt)
    {
        this.texture = Draw.loadTexture(texture);
        this.textureAlt = Draw.loadTexture(textureAlt);
    }

    public Texture getTexture() { return texture; }
    public Texture getTextureAlt() { return textureAlt; }
}
