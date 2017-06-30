package net.johnbrooks.fjg.level;

/**
 * Created by ieatl on 6/30/2017.
 */
public enum Direction
{
    UP, DOWN, LEFT, RIGHT;

    public int getX()
    {
        if (this == LEFT)
            return -1;
        else if (this == RIGHT)
            return 1;
        else
            return 0;
    }

    public int getY()
    {
        if (this == UP)
            return -1;
        else if (this == DOWN)
            return 1;
        else
            return 0;
    }

    /*
    Returns the opposite direction from this direction. Returns itself if x and y both equal 0.
     */
    public Direction getOpposite()
    {
        for (Direction direction : Direction.values())
        {
            if (getX() != 0 && getX() != direction.getX())
                return direction;
            else if (getY() != 0 && getY() != direction.getY())
                return direction;
        }

        return this;
    }
}
