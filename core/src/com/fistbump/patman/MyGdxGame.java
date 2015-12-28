package com.fistbump.patman;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.patman.states.GameState;
import com.patman.states.StateManager;
import com.patman.tiles.Path;
import com.patman.tiles.Wall;



public class MyGdxGame extends ApplicationAdapter  {
	SpriteBatch batch;

	@Override
	public void create() {
		Wall.img = new Texture("Wall.png");
		Path.img= new Texture("Path.png");
		batch = new SpriteBatch();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		StateManager.push(new GameState(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()));
	}

	@Override
	public void dispose() {
		batch.dispose();

	}

	@Override
	public void render() {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		StateManager.peek().render(batch);


	}





}