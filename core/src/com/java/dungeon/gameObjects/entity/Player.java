package com.java.dungeon.gameObjects.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.java.dungeon.JavaDungeonGame;
import com.java.dungeon.gameObjects.item.Item;
import com.java.dungeon.gameObjects.item.SwordItem;
import com.java.dungeon.sounds.SoundEffects;

public class Player extends Entity {
    private static final int FRAME_COLS = 4, FRAME_ROWS = 1;
    float stateTime;
    private final Animation<TextureRegion> idleAnimation;
    private final Animation<TextureRegion> walkAnimation;
    private final Texture walkSheet;
    private final Array<Item> inventory;
    private boolean canUseItem;
    private long lastItemUseTime;

    public Player(JavaDungeonGame game) {
        super(10, 300, new Texture(Gdx.files.internal("textures/objects/character_idle.png")), game); // TODO - Dispose texture
        walkSheet = new Texture(Gdx.files.internal("textures/objects/character_walk.png"));

        inventory = new Array<>();
        lastItemUseTime = 0;

        int h = texture.getHeight() / FRAME_ROWS;
        int w = texture.getWidth() / FRAME_COLS;
        this.x = 1280 / 2 - h / 2;
        this.y = 720 / 2 - w / 2;
        this.width = w * 8;
        this.height = h * 8;

        TextureRegion[][] tmp = TextureRegion.split(texture, texture.getWidth() / FRAME_COLS, texture.getHeight() / FRAME_ROWS);
        TextureRegion[] idleFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                idleFrames[index++] = tmp[i][j];
            }
        }
        idleAnimation = new Animation<>(1.0f, idleFrames);

        TextureRegion[][] tmp2 = TextureRegion.split(walkSheet, walkSheet.getWidth() / 2, walkSheet.getHeight());
        TextureRegion[] walkFrames = new TextureRegion[2];
        int index2 = 0;
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 2; j++) {
                walkFrames[index2++] = tmp2[i][j];
            }
        }
        walkAnimation = new Animation<>(0.1f, walkFrames);
        stateTime = 0f;
    }

    @Override
    protected void die() {
        game.gameOver();
    }

    @Override
    public int getSpeed() {
        return speed - (180 - (getHealth() * 18));
    }

    @Override
    public void render(Batch batch) {
        TextureRegion currentFrame;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            currentFrame = walkAnimation.getKeyFrame(stateTime, true);
            if (currentFrame.isFlipX()) {
                currentFrame.flip(true, false);
            }
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            currentFrame = walkAnimation.getKeyFrame(stateTime, true);
            if (!currentFrame.isFlipX()) {
                currentFrame.flip(true, false);
            }
        }
        else {
            currentFrame = idleAnimation.getKeyFrame(stateTime, true);
        }

        batch.draw(currentFrame, x, y, width, height);

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
                    this.game.soundManager.playEffect(SoundEffects.SWORD_EFFECT);
                    for (Entity e : game.entities) {
                        if (e.getClass() == Enemy.class) {
                            if (Vector2.dst(e.x, e.y, this.x, this.y) < 130) {
                                e.damage(((SwordItem) item).power);
                            }
                        }
                    }
                }
            }
            canUseItem = false;
            lastItemUseTime = TimeUtils.millis();
        }
    }

    public void update() {
        stateTime += Gdx.graphics.getDeltaTime();
        if (lastItemUseTime < TimeUtils.millis() - 1000) { // TODO - This is 1 second but should be different depending on item that is being used
            canUseItem = true;
        }
    }

    @Override
    public void damage(int amount) {
        super.damage(amount);
        idleAnimation.setFrameDuration(Math.max(idleAnimation.getFrameDuration() - 0.15f, 0.05f)); // This is just temp test for fun not actual implementation
    }

    public Array<Item> getInventory() {
        return inventory;
    }

    public void dispose() { // TODO - call this
        texture.dispose();
        walkSheet.dispose();
    }
}
