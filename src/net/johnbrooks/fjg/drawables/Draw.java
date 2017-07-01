package net.johnbrooks.fjg.drawables;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glLoadIdentity;

/**
 * Created by ieatl on 6/29/2017.
 */
public class Draw
{
    public static void drawTexture(Texture texture, int x, int y)
    {
        int width = texture.getImageWidth();
        int height = texture.getImageHeight();

        texture.bind();
        glTranslatef(x, y, 0);
        glBegin(GL_QUADS);

        glTexCoord2f(0, 0);
        glVertex2f(0, 0);

        glTexCoord2f(1, 0);
        glVertex2f(width, 0);

        glTexCoord2f(1, 1);
        glVertex2f(width, height);

        glTexCoord2f(0, 1);
        glVertex2f(0, height);

        glEnd();
        glLoadIdentity();
    }
    public static void drawTextureRotation(Texture texture, float x, float y, float rotation)
    {
        int width = texture.getImageWidth();
        int height = texture.getImageHeight();

        texture.bind();
        glTranslatef(x + (texture.getTextureWidth() * 0.5f), y + (texture.getTextureHeight() * 0.5f), 0);
        glRotatef(rotation, 0, 0, 1);
        glTranslatef(-texture.getTextureWidth() * 0.5f, -texture.getTextureHeight() *0.5f, 0);
        glBegin(GL_QUADS);

        glTexCoord2f(0, 0);
        glVertex2f(0, 0);

        glTexCoord2f(1, 0);
        glVertex2f(width, 0);

        glTexCoord2f(1, 1);
        glVertex2f(width, height);

        glTexCoord2f(0, 1);
        glVertex2f(0, height);

        glEnd();
        glLoadIdentity();
    }

    public static void drawTexture(Texture texture, int x, int y, int width, int height)
    {
        texture.bind();
        glTranslatef(x, y, 0);
        glBegin(GL_QUADS);

        glTexCoord2f(0, 0);
        glVertex2f(0, 0);

        glTexCoord2f(1, 0);
        glVertex2f(width, 0);

        glTexCoord2f(1, 1);
        glVertex2f(width, height);

        glTexCoord2f(0, 1);
        glVertex2f(0, height);

        glEnd();
        glLoadIdentity();
    }
}
