package com.java.dungeon.gameObjects.item;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.java.dungeon.JavaDungeonGame;

public class HealthPotion extends PotionItem {
    public HealthPotion() {
        super(new Texture(Gdx.files.internal("textures/item/health_potion.png")), 0);
        width = 72;
        height = 72;
    }

    @Override
    public boolean onUse(JavaDungeonGame game) {
        game.player.heal(2);
        game.player.removeItem(this);
        return true;
    }
}
