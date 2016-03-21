package com.patman.sprites;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

/**
 * Created by Mariem on 24/12/2015.
 */
public class Batman extends Character {
    private static ArrayList<Texture> up = new ArrayList<>();
    private static ArrayList<Texture> down = new ArrayList<>();
    private static ArrayList<Texture> left = new ArrayList<>();
    private static ArrayList<Texture> right = new ArrayList<>();
    private static Batman batman = new Batman();
    private int bulletCount = 10;

    private Batman() {
        this.img = new Texture("b1.png");
        if(up.isEmpty()||up.get(0).equals(null))
            loadTextures();
        dir = "right";
    }

    private String dir;

    public int getBulletCount() {
        return bulletCount;
    }

    public String getDirection() {
        return dir;
    }

    public void setBulletCount(int bulletCount) {
        this.bulletCount = bulletCount;
    }

    public void move(String direction) {
        switch (direction) {
            case "up":
                posY = posY + movement;
                if (frameCounterUp++ % 8 == 0)
                    img = up.get((frameCounterUp++) % up.size());
                dir = "up";
                break;
            case "down":
                posY = posY - movement;
                if (frameCounterDown++ % 8 == 0)
                    img = down.get((frameCounterDown++) % down.size());
                dir = "down";
                break;
            case "left":
                posX = posX - movement;
                if (frameCounterLeft++ % 5 == 0)
                    img = left.get((frameCounterLeft++) % left.size());
                dir = "left";
                break;
            case "right":
                posX = posX + movement;
                if (frameCounterRight++ % 5 == 0)
                    img = right.get((frameCounterRight++) % right.size());
                dir = "right";
                break;
        }
        updateBound();
    }

    public static Batman getInstance() {
        return batman;
    }

    protected void loadTextures(){
        up.add(new Texture("b9.png"));
        up.add(new Texture("b10.png"));
        up.add(new Texture("b12.png"));
        down.add(new Texture("b5.png"));
        down.add(new Texture("b6.png"));
        down.add(new Texture("b8.png"));
        right.add(new Texture("b1.png"));
        right.add(new Texture("b2.png"));
        left.add(new Texture("b3.png"));
        left.add(new Texture("b4.png"));
    }


}
