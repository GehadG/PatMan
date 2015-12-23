package com.fistbump.patman;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;




public class MyGdxGame extends ApplicationAdapter implements InputProcessor {
	SpriteBatch batch;
	BitmapFont font;
	int screenWidth,screenHeight;
	String text ="Touch me";
	Texture img;
    GlyphLayout layout;

	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.GREEN);
        font.getData().scale(5);
        layout= new GlyphLayout();


    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();

    }

    @Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
        layout.setText(font, " day");
        font.draw(batch, layout, 100 + layout.width / 3, 200 + layout.height / 3);

        batch.end();
	}


	@Override
	public boolean touchDown(int i, int i1, int i2, int i3) {
		return false;
	}

	@Override
	public boolean touchUp(int i, int i1, int i2, int i3) {
		return false;
	}

	@Override
	public boolean touchDragged(int i, int i1, int i2) {
		return false;
	}

	@Override
	public boolean keyDown(int i) {
		return false;
	}

	@Override
	public boolean keyUp(int i) {
		return false;
	}

	@Override
	public boolean keyTyped(char c) {
		return false;
	}


	@Override
	public boolean mouseMoved(int i, int i1) {
		return false;
	}

	@Override
	public boolean scrolled(int i) {
		return false;
	}
}
