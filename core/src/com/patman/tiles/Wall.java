package com.patman.tiles;

import com.badlogic.gdx.graphics.Texture;


/**
 * Created by Gehad on 23/12/2015.
 */
public class Wall extends Tile {
    public static Texture img;

    public Wall(int x, int y) {
        super(x, y);

    }

    @Override
    public Texture getTexture() {
        return img;
    }

}
