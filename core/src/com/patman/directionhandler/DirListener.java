package com.patman.directionhandler;

import com.badlogic.gdx.input.GestureDetector;

/**
 * Created by Gehad on 25/12/2015.
 */
public class DirListener extends GestureDetector {

    public DirListener (DirectionListener directionListener) {
        super(new DirectionGestureListener(directionListener));
    }

    private static class DirectionGestureListener extends GestureDetector.GestureAdapter {
        DirectionListener directionListener;

        public DirectionGestureListener(DirectionListener directionListener){
            this.directionListener = directionListener;
        }

        @Override
        public boolean fling(float velocityX, float velocityY, int button) {
            if(Math.abs(velocityX)>Math.abs(velocityY)){
                if(velocityX>0){
                    directionListener.onRight();
                }else{
                    directionListener.onLeft();
                }
            }else{
                if(velocityY>0){
                    directionListener.onDown();
                }else{
                    directionListener.onUp();
                }
            }
            return super.fling(velocityX, velocityY, button);
        }

    }

}