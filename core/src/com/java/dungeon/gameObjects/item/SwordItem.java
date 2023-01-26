package com.java.dungeon.gameObjects.item;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.java.dungeon.JavaDungeonGame;
import com.java.dungeon.gameObjects.entity.Enemy;
import com.java.dungeon.gameObjects.entity.Entity;
import com.java.dungeon.sounds.SoundEffects;

public class SwordItem extends Item {
    public final int power;
    public SwordItem() {
        super(new Texture(Gdx.files.internal("textures/item/sword_item.png")), 1500);
        width = 32;
        height = 100;
        power = 2;
    }

    @Override
    public void onUse(JavaDungeonGame game) {
        if (super.canUse()) {
            super.onUse(game);
            for (Entity e : game.entities) {
                if (e.getClass() == Enemy.class) {
                    if (Vector2.dst(e.x, e.y, game.player.x, game.player.y) < 130) {
                        System.out.println("Whaaaaaa");
                        e.damage(power);
                    }
                }
            }
            game.soundManager.playEffect(SoundEffects.SWORD_EFFECT);
        }
    }
}
