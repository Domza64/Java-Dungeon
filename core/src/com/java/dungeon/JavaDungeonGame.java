package com.java.dungeon;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.java.dungeon.gameObjects.ExitObject;
import com.java.dungeon.gameObjects.entity.Entity;
import com.java.dungeon.gameObjects.entity.Player;
import com.java.dungeon.gameObjects.item.Item;
import com.java.dungeon.input.InputManager;
import com.java.dungeon.rooms.Rooms;
import com.java.dungeon.screens.GameScreen;
import com.java.dungeon.screens.MainMenuScreen;
import com.java.dungeon.screens.WinScreen;
import com.java.dungeon.sounds.SoundManager;

public class JavaDungeonGame extends Game {
    public SpriteBatch batch;
    public SoundManager soundManager;
    public InputManager inputManager;
    public Player player;
    private boolean controllerConnected;
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
        inputManager = new InputManager(this);
        controllerConnected = false;
    }

    public void render() {
        super.render();
    }

    public void dispose() {
        batch.dispose();
        soundManager.dispose();
    }

    public void gameOver() {
        this.setScreen(new MainMenuScreen(this));
    }

    public void winGame() {
        this.setScreen(new WinScreen(this));
    }

    public void changeScreen(Screen screen) {
        this.setScreen(screen);
    }

    public void start() {
        player = new Player(this);
        changeScreen(new GameScreen(this, Rooms.ROOM_1));
    }

    public void updateControllerConnection(boolean b) {
        controllerConnected = b;
    }

    public boolean isControllerConnected() {
        return controllerConnected;
    }
}
