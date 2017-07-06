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
    TOWER_BASE_A("res/towers/towerBaseA.png"),
    TOWER_BASE_B("res/towers/towerBaseB.png"),
    TOWER_BASE_C("res/towers/towerBaseC.png"),
    TOWER_BASE_D("res/towers/towerBaseD.png"),

    TOWER_CANNON_STANDARD("res/towers/towerCannonStandard.png"),
    TOWER_CANNON_ICE("res/towers/towerCannonIce.png"),
    TOWER_CANNON_BURN("res/towers/towerCannonBurn.png"),
    TOWER_CANNON_FAST("res/towers/towerCannonFast.png"),
    TOWER_CANNON_TRACKING("res/towers/towerCannonTracking.png"),

    BULLET_BURN("res/projectiles/bulletBurn.png"),
    BULLET_ICE("res/projectiles/bulletBurn.png"),
    BULLET_LITE("res/projectiles/bulletBurn.png"),
    BULLET_STANDARD("res/projectiles/bulletBurn.png"),
    BULLET_TRACKING("res/projectiles/bulletBurn.png"),

    HEALTH_BACKGROUND("res/general/healthBackground.png"), HEALTH_FOREGROUND("res/general/healthForeground.png"), HEATH_BORDER("res/general/healthBorder.png"),
    X2_DAMAGE("res/textImages/x2Damage.png"),

    VIEW_DISTANCE("res/towers/viewDistance.png"),
    VIEW_DISTANCE_ILLEGAL("res/towers/viewDistanceIllegalPlacement.png"),

    DESC_WINDOW("res/hud/descWindow.png");

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
