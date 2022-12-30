package com.java.dungeon.rooms;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.java.dungeon.sounds.Sounds;

public class Rooms {
    public static final BaseRoom ROOM_3 = new BaseRoom(new Texture(Gdx.files.internal("textures/room_3_background.png")), 3, Sounds.BOSS_THEME, null, null, null, null, 500, 300);
    public static final BaseRoom ROOM_2 = new BaseRoom(new Texture(Gdx.files.internal("textures/room_2_background.png")), 2, ROOM_3, null, null, null, 500, 300);
    public static final BaseRoom ROOM_1 = new BaseRoom(new Texture(Gdx.files.internal("textures/room_1_background.png")), 1, Sounds.LEVEL_THEME, ROOM_2, null, null, null, 500, 300);
}
