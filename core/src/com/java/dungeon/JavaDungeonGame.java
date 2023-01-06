package com.java.dungeon;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.java.dungeon.gameObjects.ExitObject;
import com.java.dungeon.gameObjects.entity.Entity;
import com.java.dungeon.gameObjects.entity.Player;
import com.java.dungeon.gameObjects.item.Item;
import com.java.dungeon.screens.MainMenuScreen;
import com.java.dungeon.sounds.SoundManager;

public class JavaDungeonGame extends Game {
    public SpriteBatch batch;
    public SoundManager soundManager;
    public Player player;
    public boolean pause;

    // TODO - these arrays probably shouldnt be public because encapsulation'n stuff
    public Array<ExitObject> exits;
    public Array<Item> items;
    public Array<Entity> entities;

    @Override
    public void create() {
        pause = false;
        batch = new SpriteBatch();
        soundManager = new SoundManager();
        player = new Player(this);
        this.setScreen(new MainMenuScreen(this));
    }

    public void render() {
        super.render();
    }

    public void dispose() {
//        batch.dispose(); // TODO - Error when disposing
        soundManager.dispose();
    }

    public void gameOver() {
        pause = false;
        player = new Player(this);
        this.setScreen(new MainMenuScreen(this));
    }
}
