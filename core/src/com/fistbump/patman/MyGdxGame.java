package com.fistbump.patman;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.patman.mazegeneration.*;

import com.patman.sprites.Batman;
import com.patman.sprites.Harley;
import com.patman.tiles.Path;
import com.patman.tiles.Tile;
import com.patman.tiles.Wall;

import java.util.Random;


public class MyGdxGame extends ApplicationAdapter  {
	SpriteBatch batch;
   Tile[][]map;
   TiledMaze maze ;


Harley batman;
	Batman harley;
	@Override
	public void create() {
		batch = new SpriteBatch();
        Path.img= new Texture("Path.png");
        Wall.img = new Texture("Wall.png");
       maze = new TiledMaze(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
       map = maze.getTileMap();
		Random rn = new Random();
		int i =Math.abs(rn.nextInt() % maze.getPaths().size()) ;

		batman=new Harley(maze.getPaths().get(i).getPosX(),maze.getPaths().get(i).getPosY());
		 i =Math.abs(rn.nextInt() % maze.getPaths().size()) ;

		harley=new Batman(maze.getPaths().get(i).getPosX(),maze.getPaths().get(i).getPosY());

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
        for(int i=0;i<map.length;i++)
		{
			for(int j =0;j<map[0].length;j++)
			batch.draw(map[i][j].getTexture(), map[i][j].getPosX(), map[i][j].getPosY(),Tile.TILE_HEIGHT,Tile.TILE_HEIGHT);
		}
		batch.draw(batman.getTexture(),batman.getPosX(),batman.getPosY(),Tile.TILE_HEIGHT,Tile.TILE_WIDTH);
		batch.draw(harley.getTexture(),harley.getPosX(),harley.getPosY(),Tile.TILE_HEIGHT,Tile.TILE_WIDTH);
		batman.randomMove(maze.getWalls());
        harley.randomMove(maze.getWalls());
		batch.end();
	}





}