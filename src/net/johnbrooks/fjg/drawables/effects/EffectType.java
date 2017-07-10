package net.johnbrooks.fjg.drawables.effects;

import net.johnbrooks.fjg.drawables.Draw;
import org.newdawn.slick.opengl.Texture;

/**
 * Created by ieatl on 7/9/2017.
 */
public enum EffectType
{
    EXPLOSION(
            Draw.loadTexture("res/effects/explosion-large-01.png"),
            Draw.loadTexture("res/effects/explosion-large-02.png"),
            Draw.loadTexture("res/effects/explosion-large-03.png"),
            Draw.loadTexture("res/effects/explosion-large-04.png"),
            Draw.loadTexture("res/effects/explosion-large-05.png"),
            Draw.loadTexture("res/effects/explosion-large-06.png"),
            Draw.loadTexture("res/effects/explosion-large-07.png"),
            Draw.loadTexture("res/effects/explosion-large-08.png"),
            Draw.loadTexture("res/effects/explosion-large-09.png"),
            Draw.loadTexture("res/effects/explosion-large-10.png")
            );

    private Texture[] textures;

    EffectType(Texture... textures)
    {
        this.textures = textures;
    }

    public Texture[] getTextures()
    {
        return textures;
    }
}
