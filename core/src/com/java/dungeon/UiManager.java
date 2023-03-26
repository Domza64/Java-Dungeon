package com.java.dungeon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.java.dungeon.gameObjects.entity.player.Player;

public class UiManager {
    private static Texture hudBackground = new Texture(Gdx.files.internal("textures/ui/hud_background.png"));
    private static Texture smallSlot = new Texture(Gdx.files.internal("textures/ui/slot_small.png"));
    private static Texture bigSlot = new Texture(Gdx.files.internal("textures/ui/slot_big.png"));
    private static Texture hearth = new Texture(Gdx.files.internal("textures/ui/hearth.png"));
    private static Texture emptyHearth = new Texture(Gdx.files.internal("textures/ui/empty_hearth.png"));
    private static Texture equippedItemTexture, smallSlotItem;
    private static int playerHealth = 0;

    public static void draw(Batch batch, Viewport viewport, Player player) {
        int drawPosX = 20, drawPosY = Math.round(viewport.getWorldHeight()) - 152;
        // Background
        batch.draw(hudBackground, drawPosX, drawPosY, 556, 140);
        drawPosX += 30;
        drawPosY += 8;
        // Big slot
        batch.draw(bigSlot, drawPosX, drawPosY, 120, 120);
        if (equippedItemTexture != null) {
            batch.draw(equippedItemTexture, drawPosX + 28, drawPosY + 28, 64, 64);
        }
        drawPosX += 135;
        drawPosY += 5;

        // Hearts
        int hearthGap = 0;
        int heartSize = 32;
        for (int i = 0; i < 10; i++) {
            if (playerHealth > i) {
                batch.draw(hearth, drawPosX + hearthGap, drawPosY, heartSize, heartSize);
            }
            else {
                batch.draw(emptyHearth, drawPosX + hearthGap, drawPosY, heartSize, heartSize);
            }
            hearthGap += heartSize + 6;
        }
        drawPosY += 42;

        // Small Slots
        int slotGap = 0;
        for (int i = 0; i < 4; i++) {
            batch.draw(smallSlot, drawPosX + slotGap, drawPosY, 72, 72);
            if (player.getSlot(i) != null) {
                smallSlotItem = player.getSlot(i).getTexture();
                batch.draw(smallSlotItem, drawPosX + slotGap + 16, drawPosY + 16, 40, 40);
            }
            slotGap += 82;
        }
    }

    public static void updateEquippedItem(Texture texture) {
        equippedItemTexture = texture;
    }

    public static void  updatePlayerHealth(int health) {
        if (health >= 0 && health <= 10) {
            playerHealth = health;
        }
    }

    public static void dispose() {
        hudBackground.dispose();
        // TODO - dispose all textures
    }
}
