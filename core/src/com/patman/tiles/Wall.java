package com.patman.tiles;

import com.badlogic.gdx.graphics.Texture;


/**
 * Created by Gehad on 23/12/2015.
 */
public class Wall extends Tile {
    public static Texture img;

    public Wall(int x, int y, int i, int j) {
        super(x, y, i, j);
        isBreakable = true;
    }

    @Override
    public void dispose() {
        this.img.dispose();
    }

    @Override
    public Texture getTexture() {
        return img;
    }

}
