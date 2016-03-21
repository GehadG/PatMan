package com.patman.sprites;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Gehad on 28/12/2015.
 */
public class Alfred extends Character {
    public Alfred(int posX, int posY) {
        super(posX, posY);
        img = new Texture("alfred.png");
    }

    @Override
    public void move(String direction) {

    }

    @Override
    protected void loadTextures() {

    }
}
