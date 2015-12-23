package com.patman.tiles;

import com.badlogic.gdx.graphics.Texture;


/**
 * Created by Gehad on 23/12/2015.
 */
public class Wall extends Tile {
    private Texture img;

    public Wall(int x, int y) {
        super(x, y);
        img = new Texture("Wall.png");
    }

    @Override
    public Texture getTexture() {
        return img;
    }

}
