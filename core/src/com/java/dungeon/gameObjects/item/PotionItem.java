package com.java.dungeon.gameObjects.item;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.java.dungeon.JavaDungeonGame;
import com.java.dungeon.gameObjects.entity.Enemy;
import com.java.dungeon.gameObjects.entity.Entity;
import com.java.dungeon.sounds.SoundEffects;

public abstract class PotionItem extends Item {
    public PotionItem(Texture texture, int cooldown) {
        super(texture, cooldown); // TODO - Probably should dispose this texture at some point
    }

    @Override
    public abstract boolean onUse(JavaDungeonGame game);
}
