package com.fistbump.patman;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.parse.ParseObject;
import com.patman.mazegeneration.TiledMaze;
import com.patman.states.GameStates;
import com.patman.states.MenuState;
import com.patman.states.StateManager;
import com.patman.tiles.Path;
import com.patman.tiles.Tile;
import com.patman.tiles.Wall;


public class MyGdxGame extends ApplicationAdapter{
	SpriteBatch batch;
	@Override
	public void create() {

		Wall.img = new Texture("Wall.png");
		Path.img= new Texture("Path.png");
		 float height=Gdx.graphics.getHeight();
		int maxH=5;
		Tile.TILE_HEIGHT = (int) (height/((maxH*2)+1));
		height=height-Tile.TILE_HEIGHT;
		Tile.TILE_HEIGHT = (int) (height/((maxH*2)+1));
		batch = new SpriteBatch();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		StateManager.push(new MenuState());


	}

	@Override
	public void dispose() {
		batch.dispose();

	}

	@Override
	public void render() {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if(!StateManager.isEmpty())
		StateManager.peek().render(batch);


	}
}