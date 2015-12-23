package com.fistbump.patman;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;




public class MyGdxGame extends ApplicationAdapter  {
	SpriteBatch batch;

	@Override
	public void create() {
		batch = new SpriteBatch();
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

		batch.end();
	}


}