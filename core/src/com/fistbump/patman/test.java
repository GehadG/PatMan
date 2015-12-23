package com.fistbump.patman;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.patman.mazegeneration.ImageHandle;
import com.patman.mazegeneration.Maze2D;
import com.patman.mazegeneration.TiledMaze;
import com.patman.tiles.Tile;

/**
 * Created by Gehad on 23/12/2015.
 */
public class test  {
   static Tile[][]map;
   static TiledMaze maze ;
    public static void main(String[] args){
        maze = new TiledMaze();
        map = maze.getTileMap();
      /*  for(int i=0;i<19;i++)
        {
            for(int j=0;j<11;j++)
            {
                System.out.println("" + map[i][j].getPosY() + map[i][j].getPosX());
            }
        }*/
    }
}
