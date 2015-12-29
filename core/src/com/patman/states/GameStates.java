package com.patman.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.patman.directionhandler.DirListener;
import com.patman.directionhandler.DirectionListener;
import com.patman.mazegeneration.TiledMaze;
import com.patman.sprites.*;
import com.patman.tiles.Path;
import com.patman.tiles.Tile;
import com.patman.sprites.Character;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Gehad on 25/12/2015.
 */
public class GameStates extends States {
   private Tile[][]map;
   private TiledMaze maze ;
    private Batman batman;
    private String nextMove;
    private String move;
    private  int score=0;
    ArrayList<Character> enemy;
    ArrayList<Character> alfred;
    ArrayList<Bullets>bullet;
    DirListener controller;
    Bullets button;
    public GameStates(int Width, int Height) {
        maze = new TiledMaze(Width,Height);
        map = maze.getTileMap();
        bullet=new ArrayList<>();
alfred=new ArrayList<>();
        nextMove="left";
        move="right";
        button=new Bullets(0,0,move);
        initControl();
        Gdx.input.setInputProcessor(controller);
        Random rn = new Random();
        int i =Math.abs(rn.nextInt() % maze.getPaths().size()) ;
        batman=new Batman(maze.getPaths().get(i).getPosX(),maze.getPaths().get(i).getPosY());
        enemy=new ArrayList<>();

       int  i2 =Math.abs(rn.nextInt() % 4) ;

        for(int j=0;j<i2;j++){
           int k =1+Math.abs(rn.nextInt() % 5) ;

            i =Math.abs(rn.nextInt() % maze.getPaths().size()) ;
            switch (k){
                case 1:
                    enemy.add(new Harley(maze.getPaths().get(i).getPosX(),maze.getPaths().get(i).getPosY()));
                    break;
                case 2:
                    enemy.add(new Joker(maze.getPaths().get(i).getPosX(),maze.getPaths().get(i).getPosY()));
                    break;
                case 3:
                    enemy.add(new Freez(maze.getPaths().get(i).getPosX(),maze.getPaths().get(i).getPosY()));
                    break;
                case 4:
                    enemy.add(new Bane(maze.getPaths().get(i).getPosX(),maze.getPaths().get(i).getPosY()));
                    break;
                case 5:
                    enemy.add(new BlackMask(maze.getPaths().get(i).getPosX(),maze.getPaths().get(i).getPosY()));
                    break;
            }


            }
        int maxHealth=0;
        for(Character e:enemy){
            maxHealth+=e.health;
        }
        for(int h=0;h<maxHealth*2;h++){
            i =Math.abs(rn.nextInt() % maze.getPaths().size()) ;
            alfred.add(new Alfred(maze.getPaths().get(i).getPosX(),maze.getPaths().get(i).getPosY()));
        }


    }

    private void initControl() {
        controller=new DirListener(new DirectionListener() {
            @Override
            public void onLeft() {
           // if(batman.canMove("left",maze.getWalls()))
                nextMove="left";
            }

            @Override
            public void onRight() {
              //  System.out.println(batman.canMove("right",maze.getWalls()));
             //   if(batman.canMove("right",maze.getWalls()))
                    nextMove="right";

            }

            @Override
            public void onUp() {
               // if(batman.canMove("up",maze.getWalls()))
                    nextMove="up";

            }

            @Override
            public void onDown() {
               // if(batman.canMove("down",maze.getWalls()))
                    nextMove="down";

            }
        });
    }

    @Override
    public void render(SpriteBatch batch) {
        update();
        batch.begin();


        if(Gdx.input.justTouched()){

            int gx = Gdx.input.getX();
            int gy = Gdx.input.getY();
            Rectangle rect = new Rectangle();
            rect.set(0, Gdx.graphics.getHeight() - 2 * button.length, 2 * button.length, 2 * button.width);

          if(rect.contains(gx, gy) && batman.getBulletCount()>0) {
              Bullets temp=null;
              boolean flag=true;
              if(batman.getDirection().equals("right"))
                  temp=new Bullets(batman.getPosX() + Character.length, batman.getPosY(), move);
              if(batman.getDirection().equals("up"))
                  temp=new Bullets(batman.getPosX(), batman.getPosY()+Character.length, move);
              if(batman.getDirection().equals("left"))
                  temp=new Bullets(batman.getPosX() - Character.length, batman.getPosY(), move);
              if(batman.getDirection().equals("down"))
                  temp=new Bullets(batman.getPosX() , batman.getPosY()-Character.length, move);

              if(flag){
                  bullet.add(temp);
                  batman.setBulletCount(batman.getBulletCount()-1);
              }
              
          }
      }
        for(int i=0;i<map.length;i++)
        {
            for(int j =0;j<map[0].length;j++)
                batch.draw(map[i][j].getTexture(), map[i][j].getPosX(), map[i][j].getPosY(),Tile.TILE_HEIGHT,Tile.TILE_HEIGHT);
        }

        if(batman.canMove(nextMove,maze.getWalls())) {
            batman.move(nextMove);
            move= nextMove;
        }
        else if(batman.canMove(move,maze.getWalls()))
            batman.move(move);


        batch.draw(batman.getTexture(), batman.getPosX(), batman.getPosY(), Character.length,Character.length );
        ArrayList<Bullets> temp=new ArrayList<>();
        ArrayList<Character> tempCharacter=new ArrayList<>();
        for(Character e:enemy){
            if(batman.getBound().overlaps(e.getBound())){
                batman.isDead=true;
                batman.health--;
            }
        }

        for(Bullets b:bullet) {

            if (!b.canMove(b.getDirection(), maze.getWalls()) ) {
                b.isDead=true;


            }else
            {
                b.move(b.getDirection());
            }
        }
              for(Character a:alfred){
                  float x=a.getPosX()+a.width/2;
                  float y=a.getPosY()+a.length/2;
                  if(batman.getBound().contains(x,y)){
                      a.isDead=true;
                      score+=10;
                      batman.setBulletCount(batman.getBulletCount()+1);

                  }

              }
        for(Character e:enemy){

            for(Bullets b:bullet){
                System.out.println(e.health);
                if(b.getBound().overlaps(e.getBound())){
                    e.health--;
                   b.isDead=true;
         }



            }
            if (e.health<=0){
               e.isDead=true;
            }
        }

        for(Character e:enemy){
            if(!e.isDead)
            batch.draw(e.getTexture(),e.getPosX(),e.getPosY(),Character.length,Character.length);
            e.randomMove(maze.getWalls());
        }
        for(Bullets b:bullet) {
            if(!b.isDead)
            batch.draw(b.getTexture(), b.getPosX(), b.getPosY(), Character.length, Character.length);

        }
        for(Character a:alfred) {
            if(!a.isDead)
                batch.draw(a.getTexture(), a.getPosX(), a.getPosY(), Character.length, Character.length);

        }

        batch.draw(button.getTexture(),button.getPosX(),button.getPosY(),2*Character.length,2*Character.length);
        batch.end();






    }

    @Override
    public void dispose() {
        batman.dispose();
        for(Character e:enemy)
            e.dispose();
        for(Character a:alfred)
            a.dispose();


    }
    public void update(){
        ArrayList<Bullets> tempB =new ArrayList<>();
        ArrayList<Character> tempE=new ArrayList<>();
        ArrayList<Character> tempAlfred=new ArrayList<>();
        ArrayList<Tile> tempWall=new ArrayList<>();
        if(batman.isDead){
            if(batman.health>0)
            {   batman.isDead=false;
                Random rn = new Random();
                int i =Math.abs(rn.nextInt() % maze.getPaths().size()) ;
                batman.setPosX(maze.getPaths().get(i).getPosX());
                batman.setPosY(maze.getPaths().get(i).getPosY());

            }
            else{
                StateManager.pop();
                StateManager.push(new GameStates(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()));
            }

        }
        for(Tile w:maze.getWalls()){
            if(w.count==0){
                Tile t=new Path(w.getPosX(),w.getPosY(),w.getX(),w.getY()) ;
                ArrayList<Tile> k=maze.getPaths();
                k.add(t);
                map[w.getX()][w.getY()]=t;
                maze.setPaths(k);


            }
            else {
                tempWall.add(w);
            }

        }


        for(Character e:enemy){
            if(!e.isDead)
                tempE.add(e);
        }
        for(Bullets b:bullet){
            if(!b.isDead)
                tempB.add(b);
        }
        for(Character a:alfred){
            if(!a.isDead)
                tempAlfred.add(a);
        }
        maze.getWalls().clear();
        maze.setWalls(tempWall);
        bullet.clear();
        enemy.clear();
        bullet.addAll(tempB);
        enemy.addAll(tempE);
        alfred.clear();
        alfred.addAll(tempAlfred);
    }
}
