package com.patman.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.patman.tiles.Tile;

import java.util.ArrayList;

/**
 * Created by Gehad on 28/12/2015.
 */
public class Bullets extends Character {

    private String direction;

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Bullets(int posX, int posY, String direction) {
        super(posX, posY);
        img = new Texture("batrang.png");
        this.direction = direction;
        bound.set(posX, posY, length, width);
    }

    public void move(String direction) {
        switch (direction) {
            case "up":
                posY = posY + 3 * movement;
                break;
            case "down":
                posY = posY - 3 * movement;
                break;
            case "left":
                posX = posX - 3 * movement;
                break;
            case "right":
                posX = posX + 3 * movement;
                break;
        }
        updateBound();
    }

    @Override
    protected void loadTextures() {

    }

    @Override
    public boolean canMove(String direction, ArrayList<Tile> walls) {
        Rectangle testBound = new Rectangle();
        switch (direction) {
            case "up":
                testBound.set(posX, posY + movement, (float) (length - 0.1 * length), (float) (width - 0.1 * width));
                break;
            case "down":
                testBound.set(posX, posY - movement, (float) (length - 0.1 * length), (float) (width - 0.1 * width));
                break;
            case "left":
                testBound.set(posX - movement, posY, (float) (length - 0.1 * length), (float) (width - 0.1 * width));
                break;
            case "right":
                testBound.set(posX + movement, posY, (float) (length - 0.1 * length), (float) (width - 0.1 * width));
                break;

        }
        for (Tile f : walls) {
            if (testBound.overlaps(f.getBound())) {
                if (f.isBreakable) {
                    f.count--;

                }
                return false;
            }
        }
        return true;

    }
}
