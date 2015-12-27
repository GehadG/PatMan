package com.patman.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.patman.directionhandler.DirListener;
import com.patman.directionhandler.DirectionListener;
import com.patman.mazegeneration.TiledMaze;
import com.patman.sprites.*;
import com.patman.tiles.Tile;
import com.patman.sprites.Character;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Gehad on 25/12/2015.
 */
public class GameState extends State {
   private Tile[][]map;
   private TiledMaze maze ;
    private Batman batman;
    private String nextMove;
    private String move;
    ArrayList<Character> enemy;
    DirListener controller;
    public GameState(int Width,int Height) {
        maze = new TiledMaze(Width,Height);
        map = maze.getTileMap();
        nextMove="left";
        move="left";
        initControl();
        Gdx.input.setInputProcessor(controller);
        Random rn = new Random();
        int i =Math.abs(rn.nextInt() % maze.getPaths().size()) ;
        batman=new Batman(maze.getPaths().get(i).getPosX(),maze.getPaths().get(i).getPosY());
        enemy=new ArrayList<>();
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

        batch.begin();

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


        batch.draw(batman.getTexture(),batman.getPosX(),batman.getPosY(),Character.length,Character.length);

       batch.end();






    }

    @Override
    public void dispose() {

    }
}
