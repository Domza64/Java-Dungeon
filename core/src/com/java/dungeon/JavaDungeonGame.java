package com.java.dungeon;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.java.dungeon.gameObjects.Player;
import com.java.dungeon.screens.MainMenuScreen;
import com.java.dungeon.sounds.SoundManager;

public class JavaDungeonGame extends Game {
    public SpriteBatch batch;
    public SoundManager soundManager;
    public Player player;

    @Override
    public void create() {
        batch = new SpriteBatch();
        soundManager = new SoundManager();
        player = new Player();
        this.setScreen(new MainMenuScreen(this));
    }

    public void render() {
        super.render();
    }

    public void dispose() {
        batch.dispose();
        soundManager.dispose();
    }
}
