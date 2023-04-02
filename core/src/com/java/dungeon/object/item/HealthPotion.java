package com.java.dungeon.object.item;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.java.dungeon.JavaDungeonGame;
import com.java.dungeon.sounds.SoundEffects;

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
        game.soundManager.playEffect(SoundEffects.POTION_EFFECT);
        return true;
    }
}
