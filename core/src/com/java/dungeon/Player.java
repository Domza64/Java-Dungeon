package com.java.dungeon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.awt.*;

public class Player extends Rectangle {
    private int health, speed;
    private Texture texture;

    public Player(int health) {
        this.health = health;
        this.speed = 300;

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

    public Texture getTexture() {
        return texture;
    }

    public int getSpeed() {
        return speed;
    }
}
