package com.java.dungeon.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.java.dungeon.rooms.Rooms;

public class ExitObject extends GameObject {
    public static final Texture texture = new Texture(Gdx.files.internal("textures/exit_texture.png"));
    private final Rooms leadsTo;
    private boolean isUnlocked;

    public ExitObject(Rooms room) {
        this.leadsTo = room;
        this.isUnlocked = true;
        this.height = 150;
        this.width = 100;
    }

    // This is needed for making exit objects from JSON, do NOT delete even though Intellij says it is not used
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
