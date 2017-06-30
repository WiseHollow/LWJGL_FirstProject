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
        // This is a special flag for if the path has an edge on the edge of the map.
        boolean edge = false;
        // Keep track of the next tile in our direction
        Tile nextTile = tile;
        // Are we on a valid tile type?
        while(nextTile.getTileType() == tile.getTileType())
        {
            // We need to check one space ahead of us.
            int _x = nextTile.getXSlot() + currentDirection.getX();
            int _y = nextTile.getYSlot() + currentDirection.getY();
            // Make our nextTile equal to the ahead tile.
            nextTile = level.tileGrid.getTile(_x, _y);
            // Check for a non-existent tile.
            if (nextTile == null)
            {
                // This must be an edge detection. Let's push it through.
                edge = true;
                // Get the tile before this edge, so we have a valid tile.
                nextTile = level.tileGrid.getTile(_x - currentDirection.getX(), _y - currentDirection.getY());
                break;
            }
        }

        // Set the valid end tile.
        Tile endTile = nextTile;
        if (!edge)
            endTile = level.tileGrid.getTile(nextTile.getXSlot() - currentDirection.getX(), nextTile.getYSlot() - currentDirection.getY());

        // Calculate direction.
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

        // If we cannot find a new checkpoint to make, return null.
        return null;
    }
}
