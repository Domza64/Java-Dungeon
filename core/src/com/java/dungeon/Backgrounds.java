package com.java.dungeon;

import com.badlogic.gdx.graphics.Texture;

public enum Backgrounds {
    MAIN_MENU("textures/background/main_menu_background.png"),
    ROOM_BACKGROUND("textures/background/background.png"),
    ROOM_BACKGROUND_2("textures/background/background_2.png");

    private final String path;

    Backgrounds(String path) {
        this.path = path;
    }

    public Texture getTexture() {
        return new Texture(path);
    }
}
