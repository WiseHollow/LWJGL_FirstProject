package net.johnbrooks.fjg.ui;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ieatl on 7/2/2017.
 */
public class UI
{
    protected List<Button> buttonList;

    public UI()
    {
        buttonList = new ArrayList<>();
    }

    public void addButtons(Button... buttons) { for (Button b : buttons) buttonList.add(b); }

    public void update()
    {
        for (Button button : buttonList)
            button.update();
    }

    public void draw()
    {
        for (Button button : buttonList)
            button.draw();
    }
}
