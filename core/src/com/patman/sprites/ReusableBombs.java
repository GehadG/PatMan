package com.patman.sprites;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Mahmoud on 1/24/2016.
 */
public class ReusableBombs {
    private ArrayList<bombs> usedbombs;
    private ArrayList<bombs> freebombs;
    private ArrayList<Coinbomb>usedCbombs;
    private ArrayList<Coinbomb>freeCbombs;
    private int size;
    private static ReusableBombs instance;
    private int x;
    private int y;

    public ReusableBombs() {
        this.usedbombs = new ArrayList<bombs>();
        this.freebombs = new ArrayList<bombs>();
        this.usedCbombs= new ArrayList<Coinbomb>();
        this.freeCbombs= new ArrayList<Coinbomb>();
    }

    public static ReusableBombs getInstance() {
        if (instance == null)
            instance = new ReusableBombs();
        return instance;
    }

    public bombs acquire() {
        if (!freebombs.isEmpty()) {
            bombs object = freebombs.remove(0);
            usedbombs.add(object);
            return object;
        } else
            return null;
    }
    public Coinbomb acquireC() {
        if (!freeCbombs.isEmpty()) {
            Coinbomb object = freeCbombs.remove(0);
            usedCbombs.add(object);
            return object;
        } else
            return null;
    }

    public void release(bombs object) {
        if (usedbombs.remove(object))
            freebombs.add(object);
        else
            System.err.println("Error, no such object in the pool");
    }
    public void releaseC(Coinbomb object) {
        if (usedCbombs.remove(object))
            freeCbombs.add(object);
        else
            System.err.println("Error, no such object in the pool");
    }

    public void setMaxPoolSize(int size) {
        this.size = size;
        for (int i = 0; i < size; i++){
            freebombs.add(new bombs());
            freeCbombs.add(new Coinbomb());
    }}

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

    public ArrayList<bombs> getUsedbombs() {
        return usedbombs;
    }

    public void setUsedbombs(ArrayList<bombs> usedbombs) {
        this.usedbombs = usedbombs;
    }

    public ArrayList<bombs> getFreebombs() {
        return freebombs;
    }

    public void setFreebombs(ArrayList<bombs> freebombs) {
        this.freebombs = freebombs;
    }
}


