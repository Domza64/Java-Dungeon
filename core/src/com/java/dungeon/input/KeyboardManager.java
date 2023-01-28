package com.java.dungeon.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class KeyboardManager {
    public static void checkInput(InputManager inputManager) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) inputManager.pause();
        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) inputManager.useItem();
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) inputManager.startGame();

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) inputManager.movePlayer(PlayerMoveDir.LEFT, 1f);
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) inputManager.movePlayer(PlayerMoveDir.RIGHT, 1f);
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) inputManager.movePlayer(PlayerMoveDir.UP, 1f);
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) inputManager.movePlayer(PlayerMoveDir.DOWN, 1f);
    }
}
