package com.java.dungeon.rooms;

import com.badlogic.gdx.utils.Array;
import com.java.dungeon.Backgrounds;
import com.java.dungeon.JavaDungeonGame;
import com.java.dungeon.gameObjects.ExitObject;
import com.java.dungeon.gameObjects.entity.Entity;
import com.java.dungeon.gameObjects.item.Item;
import com.java.dungeon.screens.GameScreen;
import com.java.dungeon.sounds.Sounds;

public class Room {
    public Sounds music;
    public Backgrounds background;
    public int number;
    public Array<ExitObject> exits;
    public Array<Item> items;
    public Array<Entity> entities;

    /**
     * When overriding must call super.onLoad();
     **/
    public void onLoad(JavaDungeonGame game, GameScreen screen) {
        screen.setBackground(background.getTexture());

        Sounds musicTheme = music;
        if (musicTheme != null) {
            game.soundManager.play(musicTheme);
        }

        game.items = new Array<>(items);
        game.exits = new Array<>(exits);
        game.entities = new Array<>(entities);
    }

    public void update(JavaDungeonGame game, GameScreen screen) {

    }
}
