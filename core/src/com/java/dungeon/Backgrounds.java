package com.java.dungeon;

import com.badlogic.gdx.graphics.Texture;

public enum Backgrounds {
    MAIN_MENU("textures/main_menu_background.png"),
    ROOM_1("textures/room_1_background.png"),
    ROOM_2("textures/room_2_background.png"),
    ROOM_3("textures/room_3_background.png");

    private final String path;

    Backgrounds(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public Texture getTexture() {
        return new Texture(path);
    }
}
