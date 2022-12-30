package com.java.dungeon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.java.dungeon.gameObjects.Player;
import com.java.dungeon.sounds.SoundEffects;

public class InputManager {
    public static void movePlayer(Player player) {
        int tempX = player.x;
        int tempY = player.y;
        int speed = player.getSpeed();

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) tempX -= speed * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) tempX += speed * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) tempY += speed * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) tempY -= speed * Gdx.graphics.getDeltaTime();

        // TODO - This checks if player is out of screen, need to rewrite this somewhere better
        if (tempX < 0) tempX = 0;
        if (tempY < 0) tempY = 0;
        if (tempX > 1280 - player.width) tempX = 1280 - player.width;
        if (tempY > 720 - player.height) tempY = 720 - player.height;

        player.x = tempX;
        player.y = tempY;
    }

    public static void checkInput(JavaDungeonGame game) {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) game.soundManager.playEffect(SoundEffects.SUSPEND_EFFECT);
    }
}
