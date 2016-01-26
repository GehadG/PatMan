package com.patman.mazegeneration;

import com.patman.sprites.*;
import com.patman.sprites.Character;
import com.patman.tiles.Path;
import com.patman.tiles.Tile;
import com.patman.tiles.Wall;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Gehad on 23/12/2015.
 */
public class TiledMaze {
    private Tile[][] tileMap;
    private ArrayList<Tile> walls = new ArrayList<>();

    public ArrayList<Tile> getPaths() {
        return paths;
    }

    public ArrayList<Tile> getWalls() {
        return walls;
    }

    private ArrayList<Tile> paths = new ArrayList<>();

    public void setWalls(ArrayList<Tile> walls) {
        this.walls = walls;
    }

    public void setPaths(ArrayList<Tile> paths) {
        this.paths = paths;
    }

    public TiledMaze(int width, int height) {


        int maxH = 5;
        Tile.TILE_HEIGHT = (int) (height / ((maxH * 2) + 1));
        height = height - Tile.TILE_HEIGHT;
        Tile.TILE_HEIGHT = (int) (height / ((maxH * 2) + 1));
        Tile.TILE_WIDTH = Tile.TILE_HEIGHT;
        Character.length = (int) (Tile.TILE_HEIGHT);
        Character.width = (int) (Tile.TILE_HEIGHT);
        int maxW = width / Tile.TILE_HEIGHT;
        maxW -= 1;
        maxW = maxW / 2;
        int xBias = (width - ((((maxW) * 2) + 1) * Tile.TILE_HEIGHT)) / 2;
        int yBias = (height - ((((maxH) * 2) + 1) * Tile.TILE_HEIGHT)) / 2;
        Maze.MazeBuilder builder = new Maze.MazeBuilder();

        Maze maze = builder.setSize(maxH, maxW).setDifficulty(10).saveCorrectPath(true).build();

        String[][] print = maze.getAsciiMaze("#", ".");

        for (int i = 0; i < print[0].length; i++) {
            if (!print[0][i].equals("#")) {
                print[0][i] = "#";
            }
            if (!print[print.length - 1][i].equals("#")) {
                print[print.length - 1][i] = "#";
            }
        }
        for (int i = 0; i < print.length; i++) {
            if (!print[i][0].equals("#")) {
                print[i][0] = "#";
            }
            if (!print[i][print[0].length - 1].equals("#")) {
                print[i][print[0].length - 1] = "#";
            }
        }


        tileMap = new Tile[print.length][print[0].length];

        for (int i = 0; i < print.length; i++) {
            for (int j = 0; j < print[0].length; j++) {

                Wall wall = new Wall(i * Tile.TILE_WIDTH + xBias, j * Tile.TILE_HEIGHT - yBias, i, j);
                Path path = new Path(i * Tile.TILE_WIDTH + xBias, j * Tile.TILE_HEIGHT - yBias, i, j);
                if (j == 0 || j == print[0].length - 1 || i == 0 || i == print.length - 1) {
                    wall.isBreakable = false;
                }

                if (print[i][j].equals("#")) {
                    tileMap[i][j] = wall;
                    walls.add(wall);
                } else {
                    tileMap[i][j] = path;
                    paths.add(path);
                }

            }
        }
    }

    public Tile[][] getTileMap() {
        return tileMap;
    }
}
