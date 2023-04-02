package com.java.dungeon.object.item;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.java.dungeon.JavaDungeonGame;
import com.java.dungeon.object.ExitObject;
import com.java.dungeon.object.GameObject;
import com.java.dungeon.sounds.SoundEffects;

public class KeyItem extends Item {
    public KeyItem() {
        super(new Texture(Gdx.files.internal("textures/item/key_item.png")), 0);
        width = 78;
        height = 78;
    }

    @Override
    public boolean onUse(JavaDungeonGame game) {
        for (GameObject o : game.gameObjects) {
            if (o.getClass() != ExitObject.class) return false;
            ExitObject exit = (ExitObject) o;
            if (Vector2.dst(exit.x, exit.y, game.player.x, game.player.y) < 160) {
                if (exit.isUnlocked()) return false;
                exit.unlock();
                game.soundManager.playEffect(SoundEffects.GUITAR_EFFECT);
                game.player.removeItem(this);
                return true;
            }
        }
        return false;
    }
}
