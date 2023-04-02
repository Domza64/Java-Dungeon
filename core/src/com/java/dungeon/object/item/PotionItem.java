package com.java.dungeon.object.item;

import com.badlogic.gdx.graphics.Texture;
import com.java.dungeon.JavaDungeonGame;

public abstract class PotionItem extends Item {
    public PotionItem(Texture texture, int cooldown) {
        super(texture, cooldown); // TODO - Probably should dispose this texture at some point
    }

    @Override
    public abstract boolean onUse(JavaDungeonGame game);
}
