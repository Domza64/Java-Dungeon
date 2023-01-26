package com.java.dungeon.gameObjects.item;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.java.dungeon.JavaDungeonGame;

public class KeyItem extends Item {
    public KeyItem() {
        super(new Texture(Gdx.files.internal("textures/item/key_item.png")), 0);
        width = 78;
        height = 30;
    }
}
