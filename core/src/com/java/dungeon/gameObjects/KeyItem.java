package com.java.dungeon.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class KeyItem extends Item {
    private final Texture texture;

    public KeyItem() {
        this.texture = new Texture(Gdx.files.internal("textures/items/key_item.png"));
        width = 78;
        height = 30;
    }
}
