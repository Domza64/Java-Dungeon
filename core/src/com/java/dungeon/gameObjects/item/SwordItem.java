package com.java.dungeon.gameObjects.item;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class SwordItem extends Item {
    public final int power;
    public SwordItem() {
        super(new Texture(Gdx.files.internal("textures/item/sword_item.png")));
        width = 32;
        height = 100;
        power = 2;
    }
}
