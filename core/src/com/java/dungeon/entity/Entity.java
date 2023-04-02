package com.java.dungeon.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.java.dungeon.JavaDungeonGame;

import java.awt.*;

public abstract class Entity extends Rectangle {
    protected int health;
    private int speed;
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

    public abstract void heal(int amount);

    protected abstract void die();

    public int getSpeed() {
        return speed;
    }

    public void render(Batch batch) {
        batch.draw(texture, x, y, width, height);
    }

    public void setSpeed(int speed) {
        if (speed > 0) {
            this.speed = speed;
        }
    }
}
