package com.java.dungeon.gameObjects.item;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.java.dungeon.JavaDungeonGame;
import com.java.dungeon.gameObjects.ExitObject;
import com.java.dungeon.sounds.SoundEffects;

public class KeyItem extends Item {
    public KeyItem() {
        super(new Texture(Gdx.files.internal("textures/item/key_item.png")), 0);
        width = 78;
        height = 30;
    }

    @Override
    public boolean onUse(JavaDungeonGame game) {
        for (ExitObject e : game.exits) {
            if (Vector2.dst(e.x, e.y, game.player.x, game.player.y) < 160) {
                if (e.isUnlocked()) return false;
                e.unlock();
                game.soundManager.playEffect(SoundEffects.GUITAR_EFFECT);
                game.player.getInventory().removeValue(this, true);
                return true;
            }
        }
        return false;
    }
}
