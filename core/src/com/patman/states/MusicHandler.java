package com.patman.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by Gehad on 17/01/2016.
 */
public class MusicHandler {

    public static Music playing;
    public static Sound coin;
    public static Sound slice;
    public static Sound bonus;
    public static Sound died;
    public static Music background;
    public static Sound bomb;

    static {
        playing = Gdx.audio.newMusic(Gdx.files.internal("playingtheme.mp3"));
        coin = Gdx.audio.newSound(Gdx.files.internal("coincollected.wav"));
        slice = Gdx.audio.newSound(Gdx.files.internal("slice.wav"));
        bonus = Gdx.audio.newSound(Gdx.files.internal("bonus.wav"));
        died = Gdx.audio.newSound(Gdx.files.internal("die.wav"));
        background = Gdx.audio.newMusic(Gdx.files.internal("theme.mp3"));
        bomb = Gdx.audio.newSound(Gdx.files.internal("bomb.wav"));
    }
}
