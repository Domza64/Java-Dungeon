package com.java.dungeon.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.java.dungeon.entity.player.PlayerHorizontalMovment;
import com.java.dungeon.entity.player.PlayerVerticalMovment;

public class KeyboardManager {
    public static boolean checkInput(InputManager inputManager) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) inputManager.pause();
        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) inputManager.useItem();
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) inputManager.startGame();

        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) inputManager.selectSlot(0);
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) inputManager.selectSlot(1);
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) inputManager.selectSlot(2);
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_4)) inputManager.selectSlot(3);
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_5)) inputManager.selectSlot(4);


        boolean isMoving = false;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            isMoving = true;
            inputManager.movePlayer(null, PlayerHorizontalMovment.LEFT, 1f);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            isMoving = true;
            inputManager.movePlayer(null, PlayerHorizontalMovment.RIGHT, 1f);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
            isMoving = true;
            inputManager.movePlayer(PlayerVerticalMovment.UP, null, 1f);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            isMoving = true;
            inputManager.movePlayer(PlayerVerticalMovment.DOWN, null, 1f);
        }
        return isMoving;
    }
}
