package net.johnbrooks.fjg.ui;

import net.johnbrooks.fjg.ui.buttons.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ieatl on 7/2/2017.
 */
public class UI
{
    protected List<Button> buttonList;
    protected boolean visible;

    public UI()
    {
        buttonList = new ArrayList<>();
        visible = true;
    }

    public void addButtons(Button... buttons) { for (Button b : buttons) buttonList.add(b); }
    public void clearButtons()
    {
        buttonList.clear();
    }

    public boolean isVisible() { return visible; }

    public void update()
    {
        if (visible)
        {
            for (Button button : buttonList)
                button.update();
        }
    }

    public void draw()
    {
        if (visible)
        {
            for (Button button : buttonList)
                button.draw();
        }
    }

    public void setVisible(boolean visible)
    {
        this.visible = visible;
    }
}
