package com.patman.sprites;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

/**
 * Created by Mariem on 24/12/2015.
 */
public class BlackMask extends Character {
    private static ArrayList<Texture> up = new ArrayList<>();
    private static ArrayList<Texture> down = new ArrayList<>();
    private static ArrayList<Texture> left = new ArrayList<>();
    private static ArrayList<Texture> right = new ArrayList<>();

    public BlackMask(int posX, int posY) {
        super(posX, posY);
        this.img = new Texture("blackmask1.png");
        if(up.isEmpty()||up.get(0).equals(null))
            loadTextures();
        health = 3;
        type = 5;
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
        up.add(new Texture("blackmask9.png"));
        up.add(new Texture("blackmask10.png"));
        up.add(new Texture("blackmask12.png"));
        down.add(new Texture("blackmask5.png"));
        down.add(new Texture("blackmask6.png"));
        down.add(new Texture("blackmask8.png"));
        right.add(new Texture("blackmask1.png"));
        right.add(new Texture("blackmask2.png"));
        left.add(new Texture("blackmask3.png"));
        left.add(new Texture("blackmask4.png"));
    }
}
