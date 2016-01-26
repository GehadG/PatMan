package com.patman.states;

import com.patman.mazegeneration.TiledMaze;
import com.patman.sprites.*;

import java.util.Random;

/**
 * Created by Gehad on 19/01/2016.
 */
public class MonsterFactory {
    private TiledMaze maze;

    public MonsterFactory(TiledMaze maze) {
        this.maze = maze;
    }

    public com.patman.sprites.Character addMonster(int k) {
        int i;
        Random rn = new Random();
        i = Math.abs(rn.nextInt() % maze.getPaths().size());
        switch (k) {
            case 1:
                return new Harley(maze.getPaths().get(i).getPosX(), maze.getPaths().get(i).getPosY());

            case 2:
                return new Joker(maze.getPaths().get(i).getPosX(), maze.getPaths().get(i).getPosY());

            case 3:
                return new Freez(maze.getPaths().get(i).getPosX(), maze.getPaths().get(i).getPosY());

            case 4:
                return new Bane(maze.getPaths().get(i).getPosX(), maze.getPaths().get(i).getPosY());

            default:
                return new BlackMask(maze.getPaths().get(i).getPosX(), maze.getPaths().get(i).getPosY());

        }
    }
}
