package com.java.dungeon.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Array;

import java.awt.*;

public class Player extends GameObject {
    private int health, speed;
    private Texture texture;
    private Array<Item> inventory;

    public Player() {
        this.health = 10;
        this.speed = 300;

        inventory = new Array<>();

        texture = new Texture(Gdx.files.internal("textures/character.png")); // TODO - Dispose texture
        // 15 and 21 are pixel dimensions of character.png file (Player texture) // TODO - not hard code this, maybe make it check texture dimensions
        this.x = 1280 / 2 - 21 / 2;
        this.y = 720 / 2 - 15 / 2;
        this.width = 15 * 5;
        this.height = 21 * 5;
    }

    public int getHealth() {
        return health;
    }

    public int getSpeed() {
        return speed - (150 - (health * 15));
    }

    public void render(Batch batch) {
        batch.draw(texture, x, y, width, height);


        // JUST TEMP FOR FUN -----------------------------------
        // Actually in future this will display selected slot but slot selection will be added with UI update
        if (!inventory.isEmpty()) {
            Item item = inventory.get(0);
            batch.draw(item.getTexture(), x + 60, y + 25, item.width, item.height);
        }
        // -----------------------------------------------------

    }

    public Array<Item> getInventory() {
        return inventory;
    }
}
