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
    BARNACLE("res/entities/barnacle.png", "res/entities/barnacle_bite.png", "res/entities/barnacle_dead.png"),
    BEE("res/entities/bee.png", "res/entities/bee_fly.png", "res/entities/bee_dead.png"),
    FLY("res/entities/fly.png", "res/entities/fly_fly.png", "res/entities/fly_dead.png"),
    FROG("res/entities/frog.png", "res/entities/frog_leap.png", "res/entities/frog_dead.png"),
    GHOST("res/entities/ghost.png", "res/entities/ghost_normal.png", "res/entities/ghost_dead.png"),
    LADY_BUG("res/entities/ladyBug.png", "res/entities/ladyBug_walk.png", "res/entities/ladyBug_dead.png"),
    SNAIL("res/entities/snail.png", "res/entities/snail_walk.png", "res/entities/snail_shell.png"),
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
