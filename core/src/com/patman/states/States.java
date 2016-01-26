package com.patman.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Gehad on 25/12/2015.
 */
public abstract class States {

    public abstract void input();

    public abstract void render(SpriteBatch batch);

    public abstract void dispose();
}
