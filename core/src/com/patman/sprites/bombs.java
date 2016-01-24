package com.patman.sprites;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

/**
 * Created by Mahmoud on 1/24/2016.
 */
public class bombs extends Character {
    public bombs(int posX, int posY) {
        super(posX, posY);
        img = new Texture("bomb.png");
    }

    @Override
    public void move(String direction) {

    }
}
