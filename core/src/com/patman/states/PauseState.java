package com.patman.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.patman.tiles.Tile;

/**
 * Created by Gehad on 29/12/2015.
 */
public class PauseState extends States {
    public static GameStates game;
    private Sprite sprite;
    Texture play;
    Texture menu;
    Texture bg;


    public PauseState() {
        bg = new Texture("background.png");
        sprite = new Sprite(bg);
        sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sprite.setAlpha(0.8f);
        play = new Texture("resume.png");
        menu = new Texture("backmenu.png");
    }

    @Override
    public void input() {
        if (Gdx.input.justTouched()) {
            int gx = Gdx.input.getX();
            int gy = Gdx.input.getY();
            Rectangle rect = new Rectangle();
            Rectangle rect2 = new Rectangle();
            rect2.set((float) (0.3146 * Gdx.graphics.getWidth()), (float) ((1 - 0.437) * Gdx.graphics.getHeight() - (0.3 * Gdx.graphics.getHeight())), (float) (0.3737 * Gdx.graphics.getWidth()), (float) (0.15 * Gdx.graphics.getHeight()));
            rect.set((float) (0.3146 * Gdx.graphics.getWidth()), (float) ((1 - 0.682) * Gdx.graphics.getHeight() + (0.15 * Gdx.graphics.getHeight())), (float) (0.3737 * Gdx.graphics.getWidth()), (float) (0.15 * Gdx.graphics.getHeight()));
            if (rect.contains(gx, gy)) {
                StateManager.pop();
                StateManager.pop();
                StateManager.push(new MenuState());


            }
            if (rect2.contains(gx, gy)) {
                StateManager.pop();
            }

        }
    }

    @Override
    public void render(SpriteBatch batch) {
        input();
        game.drawView(batch);
        batch.begin();
        sprite.draw(batch);
        batch.draw(play, (float) (0.3146 * Gdx.graphics.getWidth()), (float) ((1 - 0.437) * Gdx.graphics.getHeight()), (float) (0.3737 * Gdx.graphics.getWidth()), (float) (0.15 * Gdx.graphics.getHeight()));
        batch.draw(menu, (float) (0.3146 * Gdx.graphics.getWidth()), (float) ((1 - 0.682) * Gdx.graphics.getHeight()), (float) (0.3737 * Gdx.graphics.getWidth()), (float) (0.15 * Gdx.graphics.getHeight()));
        batch.end();
    }

    @Override
    public void dispose() {
        menu.dispose();
        bg.dispose();

    }
}
