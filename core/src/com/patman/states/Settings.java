package com.patman.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.patman.tiles.Tile;

/**
 * Created by Gehad on 29/12/2015.
 */
public class Settings extends States {

    private Texture music;
    private Texture leftRight;
    private Texture on;
    private Texture off;
    private Texture bg;
     static String musiiic;
    private String haaand;
    private Texture save;
    private Texture back;
    Preferences prefs = Gdx.app.getPreferences("settings");


    public Settings() {
        on = new Texture("on.png");
        off = new Texture("off.png");
        save = new Texture("save.png");
        back = new Texture("back.png");


        musiiic = prefs.getString("musics");
        haaand = prefs.getString("hands");
        if (musiiic.equals("true"))
            music = off;
        else
            music = on;
        if (haaand.equals("true"))
            leftRight = on;
        else
            leftRight = off;
        bg = new Texture("settingsMenu.png");

    }

    @Override
    public void input() {
        if (Gdx.input.justTouched()) {
            int gx = Gdx.input.getX();
            int gy = Gdx.input.getY();

            Rectangle save = new Rectangle();
            Rectangle back = new Rectangle();
            Rectangle music = new Rectangle();
            Rectangle hand = new Rectangle();
            save.set((float) (0.53 * Gdx.graphics.getWidth()), (float) (0.95 * Gdx.graphics.getHeight() - 2 * Tile.TILE_HEIGHT), 2 * Tile.TILE_HEIGHT, 2 * Tile.TILE_HEIGHT);
            back.set((float) (0.842 * Gdx.graphics.getWidth()), (float) (0.95 * Gdx.graphics.getHeight() - 2 * Tile.TILE_HEIGHT), 2 * Tile.TILE_HEIGHT, 2 * Tile.TILE_HEIGHT);
            hand.set((float) (0.713 * Gdx.graphics.getWidth()), (float) (0.328 * Gdx.graphics.getHeight() + 2 * Tile.TILE_HEIGHT), 2 * Tile.TILE_HEIGHT, 1 * Tile.TILE_HEIGHT);
            music.set((float) (0.713 * Gdx.graphics.getWidth()), (float) (0.46 * Gdx.graphics.getHeight() + 2 * Tile.TILE_HEIGHT), 2 * Tile.TILE_HEIGHT, 1 * Tile.TILE_HEIGHT);

            if (save.contains(gx, gy)) {
                prefs.putString("musics", musiiic);
                prefs.putString("hands", haaand);
                prefs.flush();

                StateManager.pop();
            }
            if (music.contains(gx, gy)) {

                if (this.music.getTextureData().equals(on.getTextureData())) {
                    this.music = off;
                    musiiic = "true";
                } else {
                    this.music = on;
                    musiiic = "false";
                }
            }
            if (back.contains(gx, gy)) {
                StateManager.pop();

            }
            if (hand.contains(gx, gy)) {
                if (leftRight.getTextureData().equals(on.getTextureData())) {
                    leftRight = off;
                    haaand = "false";
                } else {
                    leftRight = on;
                    haaand = "true";
                }
            }
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        input();
        batch.begin();
        batch.draw(bg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        batch.draw(save, (float) (0.53 * Gdx.graphics.getWidth()), (float) (0.05 * Gdx.graphics.getHeight()), 2 * Tile.TILE_HEIGHT, 2 * Tile.TILE_HEIGHT);
        batch.draw(back, (float) (0.842 * Gdx.graphics.getWidth()), (float) (0.05 * Gdx.graphics.getHeight()), 2 * Tile.TILE_HEIGHT, 2 * Tile.TILE_HEIGHT);
        batch.draw(music, (float) (0.713 * Gdx.graphics.getWidth()), (float) (0.328 * Gdx.graphics.getHeight()), (float) (1.8 * Tile.TILE_HEIGHT), (float) (0.9 * Tile.TILE_HEIGHT));
        batch.draw(leftRight, (float) (0.713 * Gdx.graphics.getWidth()), (float) (0.46 * Gdx.graphics.getHeight()), (float) (1.8 * Tile.TILE_HEIGHT), (float) (0.9 * Tile.TILE_HEIGHT));

        batch.end();
    }

    @Override
    public void dispose() {
        music.dispose();
        leftRight.dispose();
        on.dispose();
        off.dispose();
        bg.dispose();
        back.dispose();
        save.dispose();

    }
}
