package com.java.dungeon.gameObjects.item;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;
import com.java.dungeon.JavaDungeonGame;
import com.java.dungeon.gameObjects.GameObject;

public abstract class Item extends GameObject {
    private final Texture texture;
    private long timeSinceLastUse;
    private final int cooldownTime;

    public Item(Texture texture, int cooldownTimeMills) {
        this.texture = texture;
        this.timeSinceLastUse = 0;
        this.cooldownTime = Math.max(cooldownTimeMills, 0);
    }

    public Texture getTexture() {
        return texture;
    }

    /**
     * MUST!!! call super.onUse(); when overriding
     **/
    public void onUse(JavaDungeonGame game) {;
        timeSinceLastUse = TimeUtils.millis();
    }

    protected boolean canUse() {
        return timeSinceLastUse < TimeUtils.millis() - cooldownTime;
    }
}