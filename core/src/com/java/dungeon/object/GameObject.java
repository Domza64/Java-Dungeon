package com.java.dungeon.object;

import com.badlogic.gdx.graphics.Texture;
import com.java.dungeon.JavaDungeonGame;
import com.java.dungeon.entity.player.Player;
import com.java.dungeon.screens.GameScreen;

import java.awt.*;

public abstract class GameObject extends Rectangle {
    public final Texture texture;

    public GameObject(Texture texture) {
        this.texture = texture;
    }

    public abstract boolean onCollisionWith(JavaDungeonGame game, GameScreen gameScreen);
}
