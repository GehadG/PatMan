package com.patman.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
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

import javax.xml.soap.Text;

/**
 * Created by Gehad on 25/12/2015.
 */
public class GameStates extends States {
    private Tile[][] map;
    Preferences prefs = Gdx.app.getPreferences("settings");
    private String sound;
    private TiledMaze maze;
    private Texture tab;
    private Batman batman;
    private Texture but;
    private String nextMove;
    private BitmapFont font = new BitmapFont();
    private float timePassed;
    private ReusableBombs pool = ReusableBombs.getInstance();
    private boolean life;
    private String move;
    private int score = 0;
    private ArrayList<Character> enemy;
    private ArrayList<Character> alfred;
    private ArrayList<Character> bombb;
    private ArrayList<Bullets> bullet;
    private DirListener controller;
    private Bullets button;
    private MonsterFactory factory;

    public GameStates(int Width, int Height) {
        maze = new TiledMaze(Width, Height);
        factory = new MonsterFactory(maze);
        map = maze.getTileMap();
        bullet = new ArrayList<>();
        sound = prefs.getString("musics");
        but = new Texture("shoot.png");
        alfred = new ArrayList<>();
        bombb = new ArrayList<>();
        timePassed = 0;
        life = false;
        initControl();
        nextMove = "left";
        tab = new Texture("tab.png");
        move = "right";
        font.setColor(Color.BLACK);
        font.getData().scale(Gdx.graphics.getDensity());
        Gdx.input.setInputProcessor(controller);
        pool.setMaxPoolSize(3);
        randEnem();
        MusicHandler.playing.setLooping(true);
        MusicHandler.playing.setVolume(0.2f);
        this.sound = prefs.getString("musics");
        if (sound.equals("true") && MusicHandler.playing.isPlaying() == false)
            MusicHandler.playing.play();
        else if (sound.equals("false") && MusicHandler.playing.isPlaying() == true)
            MusicHandler.playing.pause();


    }

    private void randEnem() {
        Random rn = new Random();
        int i = Math.abs(rn.nextInt() % maze.getPaths().size());
        batman = Batman.getInstance();
        batman.setPosX(maze.getPaths().get(i).getPosX());
        batman.setPosY(maze.getPaths().get(i).getPosY());
        batman.health = 4;
        enemy = new ArrayList<>();
        bombb = new ArrayList<>();
        int i2 = 1 + Math.abs(rn.nextInt() % 3);

        for (int j = 0; j < i2; j++) {
            int k = 1 + Math.abs(rn.nextInt() % 5);
            enemy.add(factory.addMonster(k));
        }
        int maxHealth = 0;
        for (Character e : enemy) {
            maxHealth += e.health;
        }
        for (int h = 0; h < maxHealth * 2; h++) {
            i = Math.abs(rn.nextInt() % maze.getPaths().size());
            alfred.add(new Alfred(maze.getPaths().get(i).getPosX(), maze.getPaths().get(i).getPosY()));
        }
        for (int h = 0; h < 3; h++) {
            i = Math.abs(rn.nextInt() % maze.getPaths().size());
            bombs b = pool.acquire();
            if (b == null) {
                continue;
            }
            b.isDead = false;
            b.setPosX(maze.getPaths().get(i).getPosX());
            b.setPosY(maze.getPaths().get(i).getPosY());
            bombb.add(b);
            pool.release(b);
        }
        for (int p = 0; p < 3; p++) {
            i = Math.abs(rn.nextInt() % maze.getPaths().size());
            Coinbomb b = pool.acquireC();
            if (b == null) {
                continue;
            }
            b.isDead = false;
            b.setPosX(maze.getPaths().get(i).getPosX());
            b.setPosY(maze.getPaths().get(i).getPosY());
            bombb.add(b);
            pool.releaseC(b);
        }
    }

    private void initControl() {
        controller = new DirListener(new DirectionListener() {
            @Override
            public void onLeft() {
                // if(batman.canMove("left",maze.getWalls()))
                nextMove = "left";
            }

            @Override
            public void onRight() {
                //  System.out.println(batman.canMove("right",maze.getWalls()));
                //   if(batman.canMove("right",maze.getWalls()))
                nextMove = "right";

            }

            @Override
            public void onUp() {
                // if(batman.canMove("up",maze.getWalls()))
                nextMove = "up";

            }

            @Override
            public void onDown() {
                // if(batman.canMove("down",maze.getWalls()))
                nextMove = "down";

            }
        });
    }

    @Override
    public void input() {
        if (Gdx.input.justTouched()) {

            int gx = Gdx.input.getX();
            int gy = Gdx.input.getY();
            Rectangle rect = new Rectangle();
            Rectangle rect2 = new Rectangle();

            rect2.set((float) (0.8 * Gdx.graphics.getWidth()), 0, (float) (0.2 * Gdx.graphics.getWidth()), Tile.TILE_HEIGHT);

            if (prefs.getString("hands").equals("true"))
                rect.set((float) (0 * Gdx.graphics.getWidth()), Gdx.graphics.getHeight() - 2 * button.length, 2 * button.length, 2 * button.width);

            else {
                rect.set((float) (0.9 * Gdx.graphics.getWidth()), Gdx.graphics.getHeight() - 2 * button.length, 2 * button.length, 2 * button.width);
            }


            if (rect.contains(gx, gy) && batman.getBulletCount() > 0) {
                Bullets temp = null;
                boolean flag = true;
                if (batman.getDirection().equals("right"))
                    temp = new Bullets(batman.getPosX() + Character.length, batman.getPosY(), move);
                if (batman.getDirection().equals("up"))
                    temp = new Bullets(batman.getPosX(), batman.getPosY() + Character.length, move);
                if (batman.getDirection().equals("left"))
                    temp = new Bullets(batman.getPosX() - Character.length, batman.getPosY(), move);
                if (batman.getDirection().equals("down"))
                    temp = new Bullets(batman.getPosX(), batman.getPosY() - Character.length, move);

                if (flag) {
                    bullet.add(temp);
                    batman.setBulletCount(batman.getBulletCount() - 1);
                    if (sound.equals("true"))
                        MusicHandler.slice.play(0.5f);
                }

            }
            if (rect2.contains(gx, gy)) {
                StateManager.push(new PauseState());
                ((PauseState) StateManager.peek()).game = this;


            }

        }
    }

    @Override
    public void render(SpriteBatch batch) {
        update(Gdx.graphics.getDeltaTime(), batch);
        input();
        updateView();
        drawView(batch);
    }

    @Override
    public void dispose() {
        tab.dispose();
        but.dispose();
        MusicHandler.playing.dispose();
        MusicHandler.bonus.dispose();
        MusicHandler.coin.dispose();
        MusicHandler.died.dispose();
        MusicHandler.slice.dispose();
        MusicHandler.background.dispose();
        MusicHandler.bomb.dispose();
    }

    public void update(float deltaTime, SpriteBatch batch) {
        timePassed += deltaTime;
        ArrayList<Bullets> tempB = new ArrayList<>();
        ArrayList<Character> tempE = new ArrayList<>();
        ArrayList<Character> tempAlfred = new ArrayList<>();
        ArrayList<Tile> tempWall = new ArrayList<>();
        if (timePassed > 20 && !life) {
            life = true;
            Random rn = new Random();
            int i = Math.abs(rn.nextInt() % maze.getPaths().size());
            tempAlfred.add(new BatLife(maze.getPaths().get(i).getPosX(), maze.getPaths().get(i).getPosY()));


        }

        if (batman.isDead) {

            if (batman.health > 0) {
                batman.isDead = false;
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Random rn = new Random();
                int i = Math.abs(rn.nextInt() % maze.getPaths().size());
                batman.setPosX(maze.getPaths().get(i).getPosX());
                batman.setPosY(maze.getPaths().get(i).getPosY());

            } else {
                StateManager.pop();
                StateManager.push(new GameStates(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
            }

        }
        for (Tile w : maze.getWalls()) {
            if (w.count == 0) {
                Tile t = new Path(w.getPosX(), w.getPosY(), w.getX(), w.getY());
                ArrayList<Tile> k = maze.getPaths();
                k.add(t);
                map[w.getX()][w.getY()] = t;
                maze.setPaths(k);


            } else {
                tempWall.add(w);
            }

        }


        for (Character e : enemy) {
            if (!e.isDead)
                tempE.add(e);

        }
        for (Bullets b : bullet) {
            if (!b.isDead)
                tempB.add(b);
        }
        for (Character a : alfred) {
            if (!a.isDead)
                tempAlfred.add(a);
            if (a instanceof BatLife && timePassed > 35) {
                tempAlfred.remove(a);
                life = false;
                timePassed = 0;
            }
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

    public void drawView(SpriteBatch batch) {
        batch.begin();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++)
                batch.draw(map[i][j].getTexture(), map[i][j].getPosX(), map[i][j].getPosY(), Tile.TILE_HEIGHT, Tile.TILE_HEIGHT);
        }
        for (Character a : alfred) {
            if (!a.isDead)
                batch.draw(a.getTexture(), a.getPosX(), a.getPosY(), Character.length, Character.length);

        }
        for (Character b : bombb) {
            if (!b.isDead)
                batch.draw(b.getTexture(), b.getPosX(), b.getPosY(), Character.length, Character.length);

        }

        for (Bullets b : bullet) {
            if (!b.isDead)
                batch.draw(b.getTexture(), b.getPosX(), b.getPosY(), Character.length, Character.length);

        }
        for (Character e : enemy) {
            if (!e.isDead)
                batch.draw(e.getTexture(), e.getPosX(), e.getPosY(), Character.length, Character.length);

        }

        batch.draw(batman.getTexture(), batman.getPosX(), batman.getPosY(), Character.length, Character.length);
        batch.setColor(1, 1, 1, 0.7f);
        if (prefs.getString("hands").equals("true"))
            batch.draw(but
                    , (float) (0 * Gdx.graphics.getWidth()), 0, 2 * Character.length, 2 * Character.length);
        else
            batch.draw(but, (float) (0.9 * Gdx.graphics.getWidth()), 0, 2 * Character.length, 2 * Character.length);
        batch.setColor(1, 1, 1, 1f);
        batch.draw(tab, 0, Gdx.graphics.getHeight() - Tile.TILE_HEIGHT, Gdx.graphics.getWidth(), Tile.TILE_HEIGHT);
        font.draw(batch, "" + score, (float) (0.1314 * Gdx.graphics.getWidth()), Gdx.graphics.getHeight() - Tile.TILE_HEIGHT / 4);
        font.draw(batch, "x" + batman.getBulletCount(), (float) (0.407 * Gdx.graphics.getWidth()), Gdx.graphics.getHeight() - Tile.TILE_HEIGHT / 4);
        font.draw(batch, "x" + batman.health, (float) (0.627 * Gdx.graphics.getWidth()), Gdx.graphics.getHeight() - Tile.TILE_HEIGHT / 4);
        batch.end();
    }

    public void updateView() {
        if (batman.canMove(nextMove, maze.getWalls())) {
            batman.move(nextMove);
            move = nextMove;
        } else if (batman.canMove(move, maze.getWalls()))
            batman.move(move);
        for (Character e : enemy) {
            if (batman.isDead) {
                batman.isDead = false;
                break;
            }
            if (batman.getBound().overlaps(e.getBound())) {
                batman.health--;
                e.isDead = true;
                batman.isDead = true;
                enemy.add(factory.addMonster(e.getType()));
                break;
            }
        }

        for (Bullets b : bullet) {

            if (!b.canMove(b.getDirection(), maze.getWalls())) {
                b.isDead = true;


            } else {
                b.move(b.getDirection());
            }
        }
        for (Character a : alfred) {

            float x = a.getPosX() + a.width / 2;
            float y = a.getPosY() + a.length / 2;
            if (batman.getBound().contains(x, y)) {
                if (!(a instanceof BatLife)) {
                    a.isDead = true;
                    score += 10;
                    batman.setBulletCount(batman.getBulletCount() + 1);
                    if (sound.equals("true"))
                        MusicHandler.coin.play(0.5f);
                } else {
                    timePassed = 0;
                    a.isDead = true;
                    batman.health++;
                    if (sound.equals("true"))
                        MusicHandler.bonus.play(0.5f);
                    life = false;
                }

            }

        }
        for (Character a : bombb) {

            float x = a.getPosX() + a.width / 2;
            float y = a.getPosY() + a.length / 2;
            if (batman.getBound().contains(x, y)) {
                if (a instanceof bombs) {
                    a.isDead = true;
                    batman.isDead = true;
                    batman.health--;//
                    if (sound.equals("true"))
                        MusicHandler.bomb.play(0.5f);
                } else if (a instanceof Coinbomb) {
                    a.isDead = true;
                    batman.setBulletCount(batman.getBulletCount() - 2);
                    if (sound.equals("true"))
                        MusicHandler.bomb.play(0.5f);
                }
            }

        }
        for (Character a : bombb) {
            float x = a.getPosX() + a.width / 2;
            float y = a.getPosY() + a.length / 2;
            for (Bullets b : bullet) {
                if (b.getBound().contains(x,y)) {
                    a.isDead = true;
                    b.isDead = true;
                    if (sound.equals("true"))
                        MusicHandler.died.play();
                }
            }
        }
        for (Character e : enemy) {
            for (Bullets b : bullet) {
                System.out.println(e.health);
                if (b.getBound().overlaps(e.getBound())) {
                    e.health--;
                    b.isDead = true;
                }
            }
            if (e.health <= 0) {
                e.isDead = true;
                score += 100;
                if (sound.equals("true"))
                    MusicHandler.died.play();

            }
        }
        for (Character e : enemy) {
            if (!e.isDead)
                e.randomMove(maze.getWalls());
        }
    }

}
