package com.fistbump.patman;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.patman.mazegeneration.*;
import com.patman.tiles.Path;
import com.patman.tiles.Tile;
import com.patman.tiles.Wall;


public class MyGdxGame extends ApplicationAdapter  {
	SpriteBatch batch;
   Tile[][]map;
    TiledMaze maze ;
	@Override
	public void create() {
		batch = new SpriteBatch();
        maze = new TiledMaze();
        map = maze.getTileMap();
		Gdx.gl.glClearColor(1, 1, 1, 1);


	}

	@Override
	public void dispose() {
		batch.dispose();


	}

	@Override
	public void render() {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
        for(int i=0;i<19;i++)
        {
            for(int j=0;j<11;j++)
            {  batch.draw(map[i][j].getTexture(),map[i][j].getPosX(),map[i][j].getPosY());
            }
        }
		batch.end();
	}


}