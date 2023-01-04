package com.java.dungeon.gameObjects;

import com.badlogic.gdx.graphics.Texture;

public class Item extends GameObject {
    private final Texture texture;

    public Item(Texture texture) {
        this.texture = texture;
    }

    public Texture getTexture() {
        return texture;
    }
}
