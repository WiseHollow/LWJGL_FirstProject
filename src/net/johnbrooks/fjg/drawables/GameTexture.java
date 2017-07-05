package net.johnbrooks.fjg.drawables;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * Created by ieatl on 6/30/2017.
 */
public enum GameTexture
{
    BULLET_BURN("res/projectiles/bulletBurn.png"), BULLET_ICE("res/projectiles/bulletBurn.png"), BULLET_LITE("res/projectiles/bulletBurn.png"),
    BULLET_STANDARD("res/projectiles/bulletBurn.png"), BULLET_TRACKING("res/projectiles/bulletBurn.png"),
    //BULLET("res/towers/bullet.png"),
    CANNON_BASE("res/towers/cannonBase.png"), CANNON_GUN("res/towers/cannonGun.png"), ICE_CANNON_BASE("res/towers/iceCannonBase.png"), ICE_CANNON_GUN("res/towers/iceCannonGun.png"),
    HEALTH_BACKGROUND("res/general/healthBackground.png"), HEALTH_FOREGROUND("res/general/healthForeground.png"), HEATH_BORDER("res/general/healthBorder.png"),
    X2_DAMAGE("res/textImages/x2Damage.png");

    private Texture texture;

    GameTexture(String path)
    {
        this.texture = Draw.loadTexture(path);
    }

    public Texture getTexture()
    {
        return texture;
    }
}
