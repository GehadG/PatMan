package com.patman.tiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Gehad on 23/12/2015.
 */
public abstract class Tile {
    public static int TILE_WIDTH = 160;
    public static int TILE_HEIGHT = 160;
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isBreakable = false;
    public int count = 2;

    public Rectangle getBound() {
        return bound;
    }

    private int posX, posY;
    protected Rectangle bound;

    public Tile(int x, int y, int i, int j) {
        this.posX = x;
        this.posY = y;
        this.x = i;
        this.y = j;

        this.bound = new Rectangle(x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);

    }

    public abstract void dispose();


    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public abstract Texture getTexture();


}
