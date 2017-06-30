package net.johnbrooks.fjg.level.levels;

import net.johnbrooks.fjg.Player;
import net.johnbrooks.fjg.drawables.GameTexture;
import net.johnbrooks.fjg.drawables.tower.TowerCannon;
import net.johnbrooks.fjg.level.*;
import net.johnbrooks.fjg.drawables.Draw;
import net.johnbrooks.fjg.drawables.entities.Enemy;

/**
 * Created by ieatl on 6/29/2017.
 */
public class Level1Easy extends Level
{
    private static int[][] map =
            {
                    { 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2 },
                    { 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 2, 2, 2, 2 },
                    { 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 2 },
                    { 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
            };

    TowerCannon testCannon;

    public Level1Easy()
    {
        super();
        tileGrid = new TileGrid(map);
        Checkpoint spawnCheckPoint = new Checkpoint(this, tileGrid.getTile(0, 2), Direction.RIGHT);
        checkpointList.add(spawnCheckPoint);

        Enemy enemyTest = new Enemy(this, tileGrid, GameTexture.ENEMY.getTexture(), 0, 0, 64, 64, 100, 15);
        wave = new Wave(this, 10, enemyTest);
        player = new Player(tileGrid);

        testCannon = new TowerCannon(GameTexture.CANNON_BASE.getTexture(), GameTexture.CANNON_GUN.getTexture(), tileGrid.getTile(1, 1), 10, 12);
    }

    public TileGrid getTileGrid()
    {
        return tileGrid;
    }

    @Override
    public void draw()
    {
        super.draw();
        testCannon.draw();
    }

    @Override
    public void update()
    {
        super.update();
        testCannon.update();
    }
}
