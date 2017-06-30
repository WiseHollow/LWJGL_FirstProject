package net.johnbrooks.fjg.level;

import net.johnbrooks.fjg.drawables.tiles.Tile;

/**
 * Created by ieatl on 6/30/2017.
 */
public class Checkpoint
{
    private Level level;
    private Tile tile;
    private Direction currentDirection;
    private Direction nextDirection;

    public Checkpoint(Level level, Tile tile, Direction currentDirection)
    {
        this.level = level;
        this.tile = tile;
        this.currentDirection = currentDirection;
        this.nextDirection = null;
    }

    public Tile getTile()
    {
        return tile;
    }

    public Direction getDirection() { return currentDirection; }

    public Checkpoint calculateNextCheckpoint()
    {
        Tile nextTile = tile;
        while(nextTile.getTileType() == tile.getTileType())
        {
            nextTile = level.tileGrid.getTile(nextTile.getXSlot() + currentDirection.getX(), nextTile.getYSlot() + currentDirection.getY());
            if (nextTile == null)
                return null;
        }

        Tile endTile = level.tileGrid.getTile(nextTile.getXSlot() - currentDirection.getX(), nextTile.getYSlot() - currentDirection.getY());

        if (currentDirection == Direction.LEFT || currentDirection == Direction.RIGHT)
        {
            Tile up = level.tileGrid.getTile(endTile.getXSlot(), endTile.getYSlot() - 1);
            Tile down = level.tileGrid.getTile(endTile.getXSlot(), endTile.getYSlot() + 1);

            if (up != null && up.getTileType() == tile.getTileType())
            {
                nextDirection = Direction.UP;
                Checkpoint nextCheckpoint = new Checkpoint(level, endTile, nextDirection);
                return nextCheckpoint;
            }
            else if (down != null && down.getTileType() == tile.getTileType())
            {
                nextDirection = Direction.DOWN;
                Checkpoint nextCheckpoint = new Checkpoint(level, endTile, nextDirection);
                return nextCheckpoint;
            }
        }
        else
        {
            Tile left = level.tileGrid.getTile(endTile.getXSlot() - 1, endTile.getYSlot());
            Tile right = level.tileGrid.getTile(endTile.getXSlot() + 1, endTile.getYSlot());

            if (left != null && left.getTileType() == tile.getTileType())
            {
                nextDirection = Direction.LEFT;
                Checkpoint nextCheckpoint = new Checkpoint(level, endTile, nextDirection);
                return nextCheckpoint;
            }
            else if (right != null && right.getTileType() == tile.getTileType())
            {
                nextDirection = Direction.RIGHT;
                Checkpoint nextCheckpoint = new Checkpoint(level, endTile, nextDirection);
                return nextCheckpoint;
            }
        }

        return null;
    }
}
