package com.java.dungeon.gameObjects.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.java.dungeon.JavaDungeonGame;
import com.java.dungeon.gameObjects.GameObject;

public abstract class Entity extends GameObject {
    private int health;
    protected int speed;
    public final Texture texture;
    protected final JavaDungeonGame game;

    public Entity(int health, int speed, Texture texture, JavaDungeonGame game) {
        this.health = health;
        this.speed = speed;
        this.texture = texture;
        this.game = game;
    }

    public int getHealth() {
        return health;
    }

    public void damage(int amount) {
        if (health - amount > 0) {
            health -= amount;
        }
        else {
            health = 0;
            die();
        }
    }

    protected abstract void die();

    public int getSpeed() {
        return speed;
    }

    public void render(Batch batch) {
        batch.draw(texture, x, y, width, height);
    }
}
