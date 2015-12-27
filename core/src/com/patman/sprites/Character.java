package com.patman.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.patman.tiles.Tile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Mariem on 24/12/2015.
 */
public abstract class Character {
    public static int length=160;
    public static int width=160;
    protected int posX,posY;

    protected Rectangle bound;
    protected int movement;
    protected int health;
    protected int Count=0;
    protected String oldMove;
    protected String olderMove;
    protected int frameCounterLeft=0;
    protected int frameCounterRight=0;
    protected int frameCounterUp=0;
    protected int frameCounterDown=0;

    protected Texture img;


    public Character(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        movement=Tile.TILE_HEIGHT/15;
        this.oldMove="right";
        this.olderMove="right";
        bound=new Rectangle();
    }

    public boolean canMove(String direction,ArrayList<Tile> walls){
        Rectangle testBound=new Rectangle();
        switch(direction){
            case "up":
                testBound.set(posX,posY+movement,length,width);
                break;
            case "down":
                testBound.set(posX,posY-movement,length,width);
                break;
            case "left":
                testBound.set(posX-movement,posY,length,width);
                break;
            case "right":
                testBound.set(posX+movement,posY,length,width);
                break;

        }
        for(Tile f:walls){
            if(testBound.overlaps(f.getBound())){
                System.out.println(f.getPosX()+","+f.getPosY());
                return  false;
            }
        }
        return true;

    }
    public abstract void move(String direction);
    protected void updateBound(){
        bound.set(posX, posY, length, width);
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void randomMove(ArrayList<Tile>walls){
if(Count==0)
    initFirst(walls);
        Count++;

        if(compDirection(oldMove,walls)){


            oldMove=getCom(oldMove,walls);

        }
        else if(canMove(oldMove,walls)){

        }
        else{
            oldMove=reverseDirection(oldMove);

        }
move(oldMove);

    }
    public  Texture getTexture(){
        return img;
    }
    private String reverseDirection(String direction){
        switch(direction){
            case"up":
                return "down";

            case"left":
                return "right";

            case"right":
                return "left";

            case"down":
                return "up";

        }
        return "";
    }
    private boolean compDirection(String direction,ArrayList<Tile>Walls){
        switch (direction){
            case"up":
                return canMove("right",Walls)||canMove("left",Walls);
            case"down":
                return canMove("right",Walls)||canMove("left",Walls);
            case"left":
                return canMove("up",Walls)||canMove("down",Walls);
            case"right":
                return canMove("up",Walls)||canMove("down",Walls);
        }
        return false;
    }
    private String getCom(String direction,ArrayList<Tile>Walls){
        ArrayList<String> dir=new ArrayList<>();
        switch (direction){
            case"up":
                if(canMove("right",Walls))
                    dir.add("right");
                if(canMove("left", Walls))
                    dir.add("left");

            case"down":
                if(canMove("right",Walls))
                    dir.add("right");
                if(canMove("left", Walls))
                    dir.add("left");

            case"left":
                if(canMove("up",Walls))
                    dir.add("up");
                if(canMove("down", Walls))
                    dir.add("down");
            case"right":
                if(canMove("up",Walls))
                    dir.add("up");
                if(canMove("down", Walls))
                    dir.add("down");        }

        Collections.shuffle(dir);
        return dir.get(0);
    }
protected void initFirst(ArrayList<Tile> walls){
    ArrayList<String> directions=new ArrayList<>();

    int s=0;


    if (canMove("up", walls)) {

        directions.add("up");
        s++;
    }
    if (canMove("down", walls)) {
        directions.add("down");
        s++;
    }
    if (canMove("left", walls)) {
        directions.add("left");
        s++;
    }
    if (canMove("right", walls)) {
        directions.add("right");
        s++;
    }

    Collections.shuffle(directions);

    Random rn = new Random();
    int i = Math.abs(rn.nextInt() % s);

    move(directions.get(i));
    oldMove=directions.get(i);

}


}
