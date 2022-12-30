package com.java.dungeon.rooms;

import com.badlogic.gdx.graphics.Texture;
import com.java.dungeon.sounds.Sounds;

import java.util.Map;

public class BaseRoom {
    private final Texture background;
    private final Sounds music;
    private final int number;
    public final BaseRoom exitN;
    public final BaseRoom exitE;
    public final BaseRoom exitS;
    public final BaseRoom exitW;
    public int spawnX;
    public int spawnY;


    public BaseRoom(Texture background, int number, BaseRoom n, BaseRoom e, BaseRoom s, BaseRoom w, int spawnX, int spawnY) {
        this(background, number, null, n, e, s, w, spawnX, spawnY);
    }

    public BaseRoom(Texture background, int number, Sounds music, BaseRoom n, BaseRoom e, BaseRoom s, BaseRoom w, int spawnX, int spawnY) {
        this.background = background;
        this.number = number;
        this.music = music;
        exitN = n;
        exitE = e;
        exitS = s;
        exitW = w;
        this.spawnX = spawnX;
        this.spawnY = spawnY;
    }

    public Texture getBackground() {
        return background;
    }

    public int getNumber() {
        return number;
    }

    public Sounds getMusicTheme() {
        return music;
    }
}
