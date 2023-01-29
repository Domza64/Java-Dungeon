package com.java.dungeon.gameObjects.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.java.dungeon.Inventory;
import com.java.dungeon.JavaDungeonGame;
import com.java.dungeon.PlayerMoveDirection;
import com.java.dungeon.gameObjects.item.Item;

public class Player extends Entity {
    private static final int FRAME_COLS = 4, FRAME_ROWS = 1;
    public final int INVENTORY_SIZE = 4;
    float stateTime;
    private final Animation<TextureRegion> idleAnimation, walkAnimation;
    private final Texture walkTexture;
    private final Inventory inventory;
    public PlayerMoveDirection playerMoveDirection;
    private int selectedSlot;

    public Player(JavaDungeonGame game) {
        super(10, 300, new Texture(Gdx.files.internal("textures/objects/character_idle.png")), game); // TODO - Dispose texture
        walkTexture = new Texture(Gdx.files.internal("textures/objects/character_walk.png"));
        inventory = new Inventory(INVENTORY_SIZE);
        playerMoveDirection = PlayerMoveDirection.IDLE;

        selectedSlot = 0;
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
        idleAnimation = new Animation<>(2.0f, idleFrames);

        TextureRegion[][] tmp2 = TextureRegion.split(walkTexture, walkTexture.getWidth() / 2, walkTexture.getHeight());
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
        return super.getSpeed() - (180 - (getHealth() * 18));
    }

    @Override
    public void render(Batch batch) {
        // Render player
        batch.draw(getCurrentFrame(), x, y, width, height);

        // Render selected item in players hand
        Item item = inventory.getItem(selectedSlot);
        if (item != null) {
            batch.draw(item.getTexture(), x + 60, y + 25, item.width, item.height);
        }


        // JUST TEMP FOR FUN -----------------------------------
        // Actually in future this will display selected slot but slot selection will be added with UI update
        // Render all items in inventory top-right on screen
        int tempNum = 1200;
        for (Item i : inventory.getInventory()) {
            if (i == null) continue;
            batch.draw(i.getTexture(), tempNum, 720 - i.height - 25, i.width / 2f, i.height / 2f);
            tempNum -= 25 + i.width / 2;
        }
        // -----------------------------------------------------
    }

    private TextureRegion getCurrentFrame() {
        TextureRegion currentFrame;
        if (playerMoveDirection == PlayerMoveDirection.LEFT) {
            currentFrame = walkAnimation.getKeyFrame(stateTime, true);
            if (currentFrame.isFlipX()) {
                currentFrame.flip(true, false);
            }
        } else if (playerMoveDirection == PlayerMoveDirection.RIGHT) {
            currentFrame = walkAnimation.getKeyFrame(stateTime, true);
            if (!currentFrame.isFlipX()) {
                currentFrame.flip(true, false);
            }
        } else {
            currentFrame = idleAnimation.getKeyFrame(stateTime, true);
        }
        return currentFrame;
    }

    public boolean useItem() {
        Item item = inventory.getItem(selectedSlot);
        if (item == null) return false;
        return item.onUse(game);
    }

    public void update() {
        stateTime += Gdx.graphics.getDeltaTime();
    }

    @Override
    public void damage(int amount) {
        super.damage(amount);
    }

    public int getSelectedSlot() {
        return selectedSlot;
    }

    // TODO - addItem and removeItem are temp solution
    public boolean addItem(Item item) {
        return inventory.addItem(item);
    }
    public void removeItem(Item item) {
        inventory.removeItem(item);
    }

    public void selectSlot(int slot) {
        if (slot < INVENTORY_SIZE) {
            selectedSlot = slot;
        }
    }

    public void dispose() {
        texture.dispose();
        walkTexture.dispose();
    }
}
