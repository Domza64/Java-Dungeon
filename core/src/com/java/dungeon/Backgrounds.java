package com.java.dungeon;

import com.badlogic.gdx.graphics.Texture;

public enum Backgrounds {
    MAIN_MENU("textures/background/main_menu_background.png"),
    ROOM_1("textures/background/room_1_background.png"),
    ROOM_2("textures/background/room_2_background.png"),
    ROOM_3("textures/background/room_3_background.png");

    private final String path;

    Backgrounds(String path) {
        this.path = path;
    }

    public Texture getTexture() {
        return new Texture(path);
    }
}
