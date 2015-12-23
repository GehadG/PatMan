package com.patman.mazegeneration;

import com.patman.tiles.Path;
import com.patman.tiles.Tile;
import com.patman.tiles.Wall;

import java.util.Arrays;

/**
 * Created by Gehad on 23/12/2015.
 */
public class TiledMaze {
    private Tile[][] tileMap;

    public TiledMaze() {
        ImageHandle map = new ImageHandle(9,5);
       String [][] mapArray= map.getMap();
       tileMap= new Tile[mapArray.length][mapArray[0].length];
        for(int i=0;i<11;i++)
        {
            for(int j=0;j<19;j++)
            {Wall wall = new Wall(i*Tile.TILE_WIDTH,480-j*Tile.TILE_HEIGHT+Tile.TILE_HEIGHT);
                Path path = new Path(i*Tile.TILE_WIDTH,480-j*Tile.TILE_HEIGHT+Tile.TILE_HEIGHT);

                if (mapArray[j][i].equals("w"))
                tileMap[j][i]=path;
                else{
                    tileMap[j][i]=wall;
                }

            }
        }
    }

    public Tile[][] getTileMap() {
        return tileMap;
    }
}
