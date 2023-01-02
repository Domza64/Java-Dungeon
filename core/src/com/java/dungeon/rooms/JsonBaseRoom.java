package com.java.dungeon.rooms;

import com.badlogic.gdx.utils.Array;
import com.java.dungeon.Backgrounds;
import com.java.dungeon.gameObjects.ExitObject;
import com.java.dungeon.gameObjects.Item;
import com.java.dungeon.sounds.Sounds;

public class JsonBaseRoom {
    public Sounds music;
    public Backgrounds background;
    public int number;
    public Array<ExitObject> exits;
    public Array<Item> items;
}
