package com.patman.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.patman.tiles.Tile;

/**
 * Created by Gehad on 29/12/2015.
 */
public class MenuState extends States {
   private Sprite sprite ;
    private Texture start;
    private Texture settings;
    private Texture quit;
    private Texture bg;
    public MenuState() {
        bg=new Texture("menu.jpg");
        sprite=new Sprite(bg);
        sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        start=new Texture("start.png");
        settings=new Texture("settings.png");
        quit=new Texture("exit.png");

    }
    @Override
    public void input() {

        if(Gdx.input.justTouched()) {
            int gx = Gdx.input.getX();
            int gy = Gdx.input.getY();

            Rectangle rect1 = new Rectangle();
            Rectangle rect2 = new Rectangle();

            Rectangle rect3 = new Rectangle();

            rect1.set((float) (0.63 * Gdx.graphics.getWidth()), (float) (0.5 * Gdx.graphics.getHeight() - 2 * Tile.TILE_HEIGHT), 6 * Tile.TILE_HEIGHT, 2 * Tile.TILE_HEIGHT);
            rect2.set((float) (0.63 * Gdx.graphics.getWidth()), (float) (0.5 * Gdx.graphics.getHeight() + 0.5 * Tile.TILE_HEIGHT), 6 * Tile.TILE_HEIGHT, 2 * Tile.TILE_HEIGHT);
            rect3.set((float) (0.63 * Gdx.graphics.getWidth()), (float) (0.5 * Gdx.graphics.getHeight() + 2.9 * Tile.TILE_HEIGHT), 6 * Tile.TILE_HEIGHT, 2 * Tile.TILE_HEIGHT);
                       System.out.println(rect1);

            if(rect1.contains(gx,gy)){

                StateManager.pop();
                StateManager.push(new GameStates(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()));
            }
            if(rect2.contains(gx,gy)){

                StateManager.push(new Settings());
            }
            if(rect3.contains(gx,gy)){

                Gdx.app.exit();
            }

        }
    }

    @Override
    public void render(SpriteBatch batch) {
        input();
       batch.begin();
        sprite.draw(batch);
        batch.draw(start, (float)(0.63 * Gdx.graphics.getWidth()),(float)(0.5*Gdx.graphics.getHeight()),6* Tile.TILE_HEIGHT,2*Tile.TILE_HEIGHT);
        batch.draw(settings, (float)(0.63 * Gdx.graphics.getWidth()),(float)(0.5*Gdx.graphics.getHeight()-2.5*Tile.TILE_HEIGHT),6* Tile.TILE_HEIGHT,2*Tile.TILE_HEIGHT );
        batch.draw(quit, (float)(0.63 * Gdx.graphics.getWidth()),(float)(0.5*Gdx.graphics.getHeight()-4.90*Tile.TILE_HEIGHT),6* Tile.TILE_HEIGHT,2*Tile.TILE_HEIGHT);
        batch.end();
    }

    @Override
    public void dispose() {
        settings.dispose();
        start.dispose();
        quit.dispose();
        bg.dispose();



    }
}
