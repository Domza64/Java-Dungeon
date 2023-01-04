package com.java.dungeon.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class SwordItem extends Item {
    public SwordItem() {
        super(new Texture(Gdx.files.internal("textures/item/sword_item.png")));
        width = 32;
        height = 100;
    }
}
