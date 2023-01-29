package com.java.dungeon.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controllers;
import com.java.dungeon.JavaDungeonGame;
import com.java.dungeon.PlayerMoveDirection;
import com.java.dungeon.gameObjects.entity.Player;
import com.java.dungeon.screens.MainMenuScreen;

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

    /**
     *
     * @param speed
     * Speed range is from 0f to 1f
     */
    public void movePlayer(PlayerMoveDirection playerMoveDirection, float speed) {
        if (game.pause) return;
        Player player = game.player;
        int tempX = player.x;
        int tempY = player.y;
        int playerSpeed = player.getSpeed();

        player.playerMoveDirection = playerMoveDirection;

        if (playerMoveDirection == PlayerMoveDirection.LEFT) tempX -= playerSpeed * Gdx.graphics.getDeltaTime() * speed;
        if (playerMoveDirection == PlayerMoveDirection.RIGHT) tempX += playerSpeed * Gdx.graphics.getDeltaTime() * speed;
        if (playerMoveDirection == PlayerMoveDirection.UP) tempY += playerSpeed * Gdx.graphics.getDeltaTime() * speed;
        if (playerMoveDirection == PlayerMoveDirection.DOWN) tempY -= playerSpeed * Gdx.graphics.getDeltaTime() * speed;

        // TODO - This checks if player is out of screen, need to rewrite this somewhere better
        if (tempX < 0) tempX = 0;
        if (tempY < 0) tempY = 0;
        if (tempX > 1280 - player.width) tempX = 1280 - player.width;
        if (tempY > 720 - player.height) tempY = 720 - player.height;

        player.x = tempX;
        player.y = tempY;
    }

    public void checkInput() {
        boolean isMoving = KeyboardManager.checkInput(this);
        if(game.isControllerConnected()) {
            ControllerManager.checkAxis(this);
            ControllerManager.checkDpad(this);
        }
        if (isMoving) game.player.playerMoveDirection = PlayerMoveDirection.IDLE;
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

    public void selectSlot(int slot) {
        game.player.selectSlot(slot);
    }

    /**
     *
     * @param upOrDown
     * True - Go up one slot, False - Go down one slot
     */
    public void changeSlot(boolean upOrDown) {
        int slot;
        if (upOrDown) {
            slot = Math.min(game.player.INVENTORY_SIZE, game.player.getSelectedSlot() + 1);
        }
        else {
            slot = Math.max(0, game.player.getSelectedSlot() - 1);
        }
        game.player.selectSlot(slot);
    }

    public boolean startGame() {
        if (game.getScreen().getClass() == MainMenuScreen.class) {
            game.start();
            return true;
        }
        return false;
    }
}
