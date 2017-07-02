package net.johnbrooks.fjg.ui;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ieatl on 7/2/2017.
 */
public class UI
{
    private List<Button> buttonList;

    public UI()
    {
        buttonList = new ArrayList<>();
    }

    public void addButton(Button button)
    {
        buttonList.add(button);
    }

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
