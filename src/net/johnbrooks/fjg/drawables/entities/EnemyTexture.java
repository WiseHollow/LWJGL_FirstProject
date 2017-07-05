package net.johnbrooks.fjg.drawables.entities;

import net.johnbrooks.fjg.drawables.Draw;
import org.newdawn.slick.opengl.Texture;

/**
 * Created by ieatl on 7/5/2017.
 */
public enum EnemyTexture
{
    MOUSE("res/entities/mouse.png", "res/entities/mouse_walk.png"), SLIME("res/entities/slimeGreen.png", "res/entities/slimeGreen_walk.png");

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
