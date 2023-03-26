package com.java.dungeon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.java.dungeon.FontUtils;
import com.java.dungeon.JavaDungeonGame;
import com.java.dungeon.UiManager;
import com.java.dungeon.Utils;
import com.java.dungeon.gameObjects.ExitObject;
import com.java.dungeon.gameObjects.entity.Enemy;
import com.java.dungeon.gameObjects.entity.Entity;
import com.java.dungeon.gameObjects.item.Item;
import com.java.dungeon.rooms.Room;
import com.java.dungeon.rooms.Rooms;
import com.java.dungeon.sounds.SoundEffects;

public class GameScreen implements Screen {
    private final JavaDungeonGame game;
    private final Viewport viewport;
    private Texture background;
    private Room currentRoom;

    public GameScreen(final JavaDungeonGame game, Rooms room) {
        this.game = game;
        viewport = new ExtendViewport(game.GAME_WIDTH, game.GAME_HEIGHT);
        loadRoom(room);
    }

    @Override
    public void show() {

    }

    private void update(float deltaTime) {
        game.inputManager.checkInput();
        if (!game.pause) {
            checkCollision();

            // Update enemies
            for (Entity e : game.entities) {
                if (e.getClass() == Enemy.class) {
                    Enemy enemy = (Enemy) e;
                    if (enemy.shouldDie) {
                        game.entities.removeValue(enemy, true);
                        enemy.texture.dispose();
                        game.soundManager.playEffect(SoundEffects.SUSPEND_EFFECT);
                    } else {
                        enemy.update(game);
                    }
                }
            }
            game.player.update();
            currentRoom.update(game, this);
        }
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);

        viewport.apply(false);
        // Camera follow player
//        viewport.getCamera().position.set(new Vector2(game.player.x + ((float)game.player.width / 2), game.player.y + ((float)game.player.height / 2)), 1);
        game.batch.setProjectionMatrix(viewport.getCamera().combined);

        // Call update
        update(delta);

        // Render stuff
        game.batch.begin();
        // Background
        game.batch.draw(background, 0, 0, game.GAME_WIDTH, game.GAME_HEIGHT);
        // Player
        game.player.render(game.batch);
        // Render Exits
        if (!game.exits.isEmpty()) {
            for (ExitObject e : game.exits) {
                game.batch.draw(ExitObject.texture, e.x, e.y, e.width, e.height);
            }
        }
        // Render Items
        if (!game.items.isEmpty()) {
            for (Item i : game.items) {
                game.batch.draw(i.getTexture(), i.x, i.y, i.width, i.height);
            }
        }
        // Render Entities
        if (!game.entities.isEmpty()) {
            for (Entity e : game.entities) {
                e.render(game.batch);
            }
        }
        if (game.pause) {
            game.batch.draw(new Texture(Gdx.files.internal("textures/ui/pause_menu.png")),
                    (viewport.getWorldWidth() / 2) - (450 / 2f), (viewport.getWorldHeight() / 2) - (270 / 2f), 450, 270);
        }
        // UI
        UiManager.draw(game.batch, viewport, game.player);

        game.batch.end();
    }

    private void checkCollision() {
        // Checks if player collides with anything
        if (!game.exits.isEmpty()) {
            for (ExitObject e : game.exits) {
                if (game.player.intersects(e)) {
                    if (e.getLeadsTo() != null) {
                        if (e.isUnlocked()) {
                            game.soundManager.playEffect(e.effect);
                            loadRoom(e.getLeadsTo());
                        }
                    }
                    else {
                        System.err.println("Exit does not lead anywhere???");
                    }
                }
            }
        }
        if (!game.items.isEmpty()) {
            for (Item i : game.items) {
                if (game.player.intersects(i)) {
                    if (game.player.addItem(i)) {
                        game.items.removeValue(i, true);
                        game.soundManager.playEffect(SoundEffects.POP_EFFECT);
                    }
                }
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {
        game.soundManager.playEffect(SoundEffects.PAUSE_EFFECT);
        game.soundManager.decreaseVolume(30);
        game.pause = true;
    }

    @Override
    public void resume() {
        game.soundManager.playEffect(SoundEffects.PAUSE_EFFECT);
        game.soundManager.resetVolume();
        game.pause = false;
    }

    private void loadRoom(Rooms room) {
        currentRoom = Utils.loadRoomFromJson(Gdx.files.internal(room.getPath()));

        this.game.player.x = (game.GAME_WIDTH / 2) - (this.game.player.width / 2);
        this.game.player.y = (game.GAME_HEIGHT  / 2) - (this.game.player.height / 2);

        currentRoom.onLoad(game, this);
    }

    @Override
    public void hide() {}
    @Override
    public void dispose() {
        background.dispose();
        game.player.dispose();
        UiManager.dispose();
    }

    public void setBackground(Texture texture) {
        this.background = texture;
    }
}
