package com.java.dungeon.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class KeyItem extends Item {
    public KeyItem() {
        super(new Texture(Gdx.files.internal("textures/items/key_item.png")));
        width = 78;
        height = 30;
    }
}
