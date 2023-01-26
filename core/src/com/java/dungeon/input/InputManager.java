package com.java.dungeon.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controllers;
import com.java.dungeon.JavaDungeonGame;
import com.java.dungeon.gameObjects.entity.Player;

// PlayerMoveDir is just a TEMP SOLUTION!!!
enum PlayerMoveDir {
    RIGHT,
    LEFT,
    UP,
    DOWN
}
public class InputManager {
    private final JavaDungeonGame game;

    public InputManager(JavaDungeonGame game) {
        this.game = game;
        Controllers.addListener(new ControllerManager(this).getListener());
    }

    public void controllerConnected() {
        game.updateControllerConnection(true);
        System.out.println("Controller connected");
    }

    public void controllerDisconnected() {
        game.updateControllerConnection(false);
        System.out.println("Controller disconnected");
    }

    public void movePlayer(PlayerMoveDir moveDir, float speed) {
        if (game.pause) return;
        Player player = game.player;
        int tempX = player.x;
        int tempY = player.y;
        int playerSpeed = player.getSpeed();

        if (moveDir == PlayerMoveDir.LEFT) tempX -= playerSpeed * Gdx.graphics.getDeltaTime() * speed;
        if (moveDir == PlayerMoveDir.RIGHT) tempX += playerSpeed * Gdx.graphics.getDeltaTime() * speed;
        if (moveDir == PlayerMoveDir.UP) tempY += playerSpeed * Gdx.graphics.getDeltaTime() * speed;
        if (moveDir == PlayerMoveDir.DOWN) tempY -= playerSpeed * Gdx.graphics.getDeltaTime() * speed;

        // TODO - This checks if player is out of screen, need to rewrite this somewhere better
        if (tempX < 0) tempX = 0;
        if (tempY < 0) tempY = 0;
        if (tempX > 1280 - player.width) tempX = 1280 - player.width;
        if (tempY > 720 - player.height) tempY = 720 - player.height;

        player.x = tempX;
        player.y = tempY;
    }

    public void checkInput() {
        KeyboardManager.checkInput(this);
        if(game.isControllerConnected()) {
            ControllerManager.checkAxis(this);
            ControllerManager.checkDpad(this);
        }
    }

    public boolean useItem() {
        return game.player.useItem();
    }

    public void pause() {
        if (game.pause) {
            game.resume();
        }
        else {
            game.pause();
        }
    }
}