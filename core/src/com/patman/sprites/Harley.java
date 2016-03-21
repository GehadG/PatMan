package com.patman.sprites;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

/**
 * Created by Mariem on 24/12/2015.
 */
public class Harley extends Character {
    private static ArrayList<Texture> up = new ArrayList<>();
    private static ArrayList<Texture> down = new ArrayList<>();
    private static ArrayList<Texture> left = new ArrayList<>();
    private static ArrayList<Texture> right = new ArrayList<>();

    public Harley(int posX, int posY) {
        super(posX, posY);
        this.img = new Texture("harley1.png");
        if(up.isEmpty()||up.get(0).equals(null))
            loadTextures();
        health = 2;
        type = 1;

    }

    public void move(String direction) {
        switch (direction) {
            case "up":
                posY = posY + movement;
                if (frameCounterUp++ % 8 == 0)
                    img = up.get((frameCounterUp++) % up.size());

                break;
            case "down":
                posY = posY - movement;
                if (frameCounterDown++ % 8 == 0)
                    img = down.get((frameCounterDown++) % down.size());

                break;
            case "left":
                posX = posX - movement;
                if (frameCounterLeft++ % 5 == 0)
                    img = left.get((frameCounterLeft++) % left.size());
                break;
            case "right":
                posX = posX + movement;
                if (frameCounterRight++ % 5 == 0)
                    img = right.get((frameCounterRight++) % right.size());

                break;
        }
        updateBound();
    }

    protected void loadTextures() {
        up.add(new Texture("harley9.png"));
        up.add(new Texture("harley10.png"));
        up.add(new Texture("harley12.png"));
        down.add(new Texture("harley5.png"));
        down.add(new Texture("harley6.png"));
        down.add(new Texture("harley8.png"));
        right.add(new Texture("harley1.png"));
        right.add(new Texture("harley2.png"));
        left.add(new Texture("harley3.png"));
        left.add(new Texture("harley4.png"));
    }
}
