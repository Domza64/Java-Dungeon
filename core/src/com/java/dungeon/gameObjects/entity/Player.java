package com.java.dungeon.gameObjects.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.java.dungeon.JavaDungeonGame;
import com.java.dungeon.gameObjects.item.Item;
import com.java.dungeon.gameObjects.item.SwordItem;
import com.java.dungeon.sounds.SoundEffects;

public class Player extends Entity {
    private Array<Item> inventory;
    private boolean canUseItem;
    private long lastItemUseTime;

    public Player(JavaDungeonGame game) {
        super(10, 300, new Texture(Gdx.files.internal("textures/objects/character.png")), game); // TODO - Dispose texture

        inventory = new Array<>();
        lastItemUseTime = 0;

        // 15 and 21 are pixel dimensions of character.png file (Player texture) // TODO - not hard code this, maybe make it check texture dimensions
        this.x = 1280 / 2 - 21 / 2;
        this.y = 720 / 2 - 15 / 2;
        this.width = 15 * 5;
        this.height = 21 * 5;
    }

    @Override
    protected void die() {
        game.gameOver();
    }

    @Override
    public int getSpeed() {
        return speed - (150 - (getHealth() * 15));
    }

    @Override
    public void render(Batch batch) {
        super.render(batch);

        // JUST TEMP FOR FUN -----------------------------------
        // Actually in future this will display selected slot but slot selection will be added with UI update
        if (!inventory.isEmpty()) {
            Item item = inventory.get(0);
            batch.draw(item.getTexture(), x + 60, y + 25, item.width, item.height);
        }
        // -----------------------------------------------------

    }

    public void useItem() {
        if (canUseItem) {
            if (!inventory.isEmpty()) {
                Item item = inventory.get(0);
                if(item.getClass() == SwordItem.class) {
                    for (Entity e : game.entities) {
                        if (e.getClass() == Enemy.class) {
                            if (Vector2.dst(e.x, e.y, this.x, this.y) < 130) {
                                e.damage(((SwordItem) item).power);
                            }
                        }
                    }
                    this.game.soundManager.playEffect(SoundEffects.GUITAR_EFFECT);
                }
            }
            canUseItem = false;
            lastItemUseTime = TimeUtils.millis();
        }
    }

    public void update() {
        if (lastItemUseTime < TimeUtils.millis() - 3000) { // TODO - This is 3 seconds but should be different depending on item that is being used
            canUseItem = true;
        }
    }

    public Array<Item> getInventory() {
        return inventory;
    }
}
