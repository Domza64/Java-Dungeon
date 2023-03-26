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

import java.util.Vector;

public class Enemy extends Entity {
    private boolean canAttack;
    public boolean shouldDie;
    private long timeSinceLastAttack;
    private final int attackSpeed; // Attack every x seconds
    private final BitmapFont healthDisplay; // TODO - dispose this
    public Enemy() {
        super(10, 100, new Texture(Gdx.files.internal("textures/objects/enemy.png")), null); // TODO - Dispose texture

        // 9 and 16 are pixel dimensions of enemy.png file (Enemy texture) // TODO - not hard code this, maybe make it check texture dimensions
        this.width = 9 * 5;
        this.height = 16 * 5;

        this.x = (1280 / 2) - (height / 2);
        this.y = 720 - width;

        attackSpeed = 3;
        healthDisplay = FontUtils.getFont(FontUtils.Fonts.MINECRAFT, 20, Color.WHITE);

        canAttack = true;
        shouldDie = false;
    }

    public void update(JavaDungeonGame game) {
        // TODO - This is a temp solution, will be replaced with LibGDX AI in future
        moveTowardsPlayer(game.player.x, game.player.y);

        if (this.intersects(game.player) && canAttack) {
            attack(game.player, game);
            canAttack = false;
            timeSinceLastAttack = TimeUtils.millis();
        }
        if (timeSinceLastAttack < TimeUtils.millis() - attackSpeed * 1000L) {
            canAttack = true;
        }
    }

    @Override
    public void render(Batch batch) {
        super.render(batch);

        // TODO -----------------------------------
        healthDisplay.draw(batch, "Health: " + getHealth(), x, y + height);
        // -----------------------------------------------------
    }

    private void moveTowardsPlayer(int playerX, int playerY) {
        if (playerX > x + 40) {
            x += getSpeed() * Gdx.graphics.getDeltaTime();
        }
        else if (playerX < x - 40){
            x -= getSpeed() * Gdx.graphics.getDeltaTime();
        }

        if (playerY > y + 50) {
            y += getSpeed() * Gdx.graphics.getDeltaTime();
        }
        else if (playerY < y - 50){
            y -= getSpeed() * Gdx.graphics.getDeltaTime();
        }
    }

    private void attack(Entity entity, JavaDungeonGame game) {
        game.soundManager.playEffect(SoundEffects.WHIP_EFFECT);
        entity.damage(1);
    }

    @Override
    public void heal(int amount) {

    }

    @Override
    protected void die() {
        shouldDie = true;
    }
}
