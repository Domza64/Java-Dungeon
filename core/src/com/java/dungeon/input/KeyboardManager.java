package com.java.dungeon.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class KeyboardManager {
    public static void checkInput(InputManager inputManager) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) inputManager.pause();
        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) inputManager.useItem();

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) inputManager.movePlayer(PlayerMoveDir.LEFT);
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) inputManager.movePlayer(PlayerMoveDir.RIGHT);
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) inputManager.movePlayer(PlayerMoveDir.UP);
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) inputManager.movePlayer(PlayerMoveDir.DOWN);
    }
}
