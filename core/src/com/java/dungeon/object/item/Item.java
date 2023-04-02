package com.java.dungeon.object.item;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;
import com.java.dungeon.JavaDungeonGame;
import com.java.dungeon.object.GameObject;
import com.java.dungeon.screens.GameScreen;

public abstract class Item extends GameObject {
    private long timeSinceLastUse;
    private final int cooldownTime;

    public Item(Texture texture, int cooldownTimeMills) {
        super(texture);
        this.timeSinceLastUse = 0;
        this.cooldownTime = Math.max(cooldownTimeMills, 0);
    }

    public Texture getTexture() {
        return texture;
    }

    public boolean onUse(JavaDungeonGame game) {
        timeSinceLastUse = TimeUtils.millis();
        return true;
    }

    protected boolean canUse() {
        return timeSinceLastUse < TimeUtils.millis() - cooldownTime;
    }

    @Override
    public boolean onCollisionWith(JavaDungeonGame game, GameScreen gameScreen) {
        // TODO
        return false;
    }
}