package net.johnbrooks.fjg.drawables;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glLoadIdentity;

/**
 * Created by ieatl on 6/29/2017.
 */
public class Draw
{
    private static TrueTypeFont font;
    public static TrueTypeFont getFont()
    {
        return font;
    }

    public static void initFonts()
    {
        try
        {
            InputStream inputStream = ResourceLoader.getResourceAsStream("res/fonts/AveriaSansLibre-Regular.ttf");

            Font awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            awtFont  = awtFont.deriveFont(48f); // set font size
            font = new TrueTypeFont(awtFont, true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static Texture loadTexture(String path)
    {
        Texture texture = null;
        InputStream inputStream = ResourceLoader.getResourceAsStream(path);
        try
        {
            texture = TextureLoader.getTexture("PNG", inputStream);
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return texture;
    }
    public static void drawTexture(Texture texture, float x, float y, float rotation)
    {
        int width = texture.getTextureWidth();
        int height = texture.getTextureHeight();

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
    public static void drawTextureWithRGB(Texture texture, float x, float y, float rotation, float red, float green, float blue)
    {
        int width = texture.getTextureWidth();
        int height = texture.getTextureHeight();

        texture.bind();
        glTranslatef(x + (texture.getTextureWidth() * 0.5f), y + (texture.getTextureHeight() * 0.5f), 0);
        glRotatef(rotation, 0, 0, 1);
        glTranslatef(-texture.getTextureWidth() * 0.5f, -texture.getTextureHeight() *0.5f, 0);
        GL11.glColor3f(red, green, blue);
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
        GL11.glColor3f(1f, 1f, 1f);
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
