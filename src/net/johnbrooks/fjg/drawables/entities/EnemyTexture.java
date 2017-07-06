package net.johnbrooks.fjg.drawables.entities;

import net.johnbrooks.fjg.drawables.Draw;
import org.newdawn.slick.opengl.Texture;

/**
 * Created by ieatl on 7/5/2017.
 */
public enum EnemyTexture
{
    MOUSE("res/entities/mouse.png", "res/entities/mouse_walk.png", "res/entities/mouse_dead.png"), SPINNER("res/entities/spinner.png", "res/entities/spinner_spin.png", "res/entities/spinner_dead.png"),
    SLIME("res/entities/slimeGreen.png", "res/entities/slimeGreen_walk.png", "res/entities/slimeGreen_dead.png"), SLIME_PINK("res/entities/slimePink.png", "res/entities/slimePink_walk.png", "res/entities/slimePink_dead.png"),
    SLIME_BLUE("res/entities/slimeBlue.png", "res/entities/slimeBlue_walk.png", "res/entities/slimeBlue_dead.png"),
    SPIDER("res/entities/spider.png", "res/entities/spider_walk.png", "res/entities/spider_dead.png");

    Texture texture;
    Texture textureAlt;
    Texture textureDead;

    EnemyTexture(String texture, String textureAlt, String textureDead)
    {
        this.texture = Draw.loadTexture(texture);
        this.textureAlt = Draw.loadTexture(textureAlt);
        this.textureDead = Draw.loadTexture(textureDead);
    }

    public Texture getTexture() { return texture; }
    public Texture getTextureAlt() { return textureAlt; }
    public Texture getTextureDead() { return textureDead; }
}
