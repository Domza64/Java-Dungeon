package com.java.dungeon.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.java.dungeon.rooms.Rooms;
import com.java.dungeon.sounds.SoundEffects;

public class ExitObject extends GameObject {
    public static final Texture texture = new Texture(Gdx.files.internal("textures/objects/exit_texture.png"));
    private final Rooms leadsTo;
    private boolean isUnlocked;
    public SoundEffects effect;


    public ExitObject() {
        this.leadsTo = null;
    }

    public Rooms getLeadsTo() {
        return leadsTo;
    }

    public boolean isUnlocked() {
        return isUnlocked;
    }

    public void unlock() {
        isUnlocked = true;
    }
}
