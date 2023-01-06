package com.java.dungeon.gameObjects.item;

import com.badlogic.gdx.graphics.Texture;
import com.java.dungeon.gameObjects.GameObject;

public class Item extends GameObject {
    private final Texture texture;

    public Item(Texture texture) {
        this.texture = texture;
    }

    public Texture getTexture() {
        return texture;
    }
}
