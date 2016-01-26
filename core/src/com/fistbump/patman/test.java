package com.fistbump.patman;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.patman.mazegeneration.TiledMaze;
import com.patman.tiles.Tile;

/**
 * Created by Gehad on 23/12/2015.
 */
public class test  {
   static Tile[][]map;
   static TiledMaze maze ;
    public static void main(String[] args){
        Texture text = new Texture(Gdx.files.internal("Path.png"));
       // maze = new TiledMaze();
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
