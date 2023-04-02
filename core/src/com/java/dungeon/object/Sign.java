package com.java.dungeon.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.java.dungeon.JavaDungeonGame;
import com.java.dungeon.entity.player.Player;
import com.java.dungeon.screens.GameScreen;

public class Sign extends GameObject {
    private String message = "aaaaaaaaaaaaaaaaaaaaaa";
    public Sign() {
        super(new Texture(Gdx.files.internal("textures/objects/sign.png")));
    }

    @Override
    public boolean onCollisionWith(JavaDungeonGame game, GameScreen gameScreen) {
        System.out.println("Sign message: " + message);
        return false;
    }
}
