package com.patman.tiles;

import com.badlogic.gdx.graphics.Texture;


/**
 * Created by Gehad on 23/12/2015.
 */
public class Path extends Tile {
    public static Texture img;

    @Override
    public void dispose() {
        this.img.dispose();
    }

    public Path(int x, int y, int i, int j) {
        super(x, y, i, j);
    }

    @Override
    public Texture getTexture() {
        return img;
    }
}
