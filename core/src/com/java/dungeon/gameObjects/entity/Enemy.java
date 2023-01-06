package com.java.dungeon.gameObjects.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.TimeUtils;
import com.java.dungeon.FontUtils;
import com.java.dungeon.JavaDungeonGame;
import com.java.dungeon.sounds.SoundEffects;

public class Enemy extends Entity {
    private long attackTime;
    private boolean canAttack;
    private final int attackSpeed = 2; // Time needed to recharge attack, example 4 means enemy will attack every 4 seconds
    public boolean shouldDie;

    private BitmapFont healthDisplay; // TODO - dispose this
    public Enemy() {
        super(10, 100, new Texture(Gdx.files.internal("textures/objects/enemy.png")), null); // TODO - Dispose texture

        // 9 and 16 are pixel dimensions of enemy.png file (Enemy texture) // TODO - not hard code this, maybe make it check texture dimensions
        this.width = 9 * 5;
        this.height = 16 * 5;

        this.x = (1280 / 2) - (height / 2);
        this.y = 720 - width;

        healthDisplay = FontUtils.getFont(FontUtils.Fonts.MINECRAFT, 20, new Color(0.85f, 0.8f, 0.7f, 1f));

        canAttack = true;
        shouldDie = false;
    }

    public void update(JavaDungeonGame game) {
        // TODO - This is a temp solution, will be replaced with LibGDX AI in future
        moveTowardsPlayer(game.player.x, game.player.y);

        if (this.intersects(game.player) && canAttack) {
            game.soundManager.playEffect(SoundEffects.WHIP_EFFECT);
            attack(game.player);
            canAttack = false;
            attackTime = TimeUtils.millis();
        }
        if (attackTime < TimeUtils.millis() - attackSpeed * 1000) {
            canAttack = true;
        }
    }

    @Override
    public void render(Batch batch) {
        super.render(batch);

        // JUST TEMP FOR FUN -----------------------------------
        // Actually in future this will display selected slot but slot selection will be added with UI update
        healthDisplay.draw(batch, "Health: " + getHealth(), x, y + height);
        // -----------------------------------------------------
    }

    private void moveTowardsPlayer(int playerX, int playerY) {
        if (playerX > x + 40) {
            x += speed * Gdx.graphics.getDeltaTime();
        }
        else if (playerX < x - 40){
            x -= speed * Gdx.graphics.getDeltaTime();
        }

        if (playerY > y + 50) {
            y += speed * Gdx.graphics.getDeltaTime();
        }
        else if (playerY < y - 50){
            y -= speed * Gdx.graphics.getDeltaTime();
        }
    }

    private void attack(Entity entity) {
        entity.damage(1);
    }

    @Override
    protected void die() {
        shouldDie = true;
    }
}
