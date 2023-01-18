package com.java.dungeon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.ScreenUtils;
import com.java.dungeon.FontUtils;
import com.java.dungeon.JavaDungeonGame;
import com.java.dungeon.Utils;
import com.java.dungeon.gameObjects.ExitObject;
import com.java.dungeon.gameObjects.entity.Enemy;
import com.java.dungeon.gameObjects.entity.Entity;
import com.java.dungeon.gameObjects.item.Item;
import com.java.dungeon.gameObjects.item.KeyItem;
import com.java.dungeon.rooms.BaseRoom;
import com.java.dungeon.rooms.Rooms;
import com.java.dungeon.sounds.SoundEffects;

public class GameScreen implements Screen {
    private final JavaDungeonGame game;
    private final OrthographicCamera camera;
    private Texture background;
    private BaseRoom currentRoom;

    // TODO - This is temp for testing it will be done in UI update
    private final BitmapFont playerHealthDisplay;

    public GameScreen(final JavaDungeonGame game, Rooms room) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);

        playerHealthDisplay = FontUtils.getFont(FontUtils.Fonts.BITMGOTHIC, 64, new Color(0.85f, 0.8f, 0.7f, 1f));

        loadRoom(room);
    }

    @Override
    public void show() {

    }

    private void update(float deltaTime) {
        game.inputManager.checkKeyboardInput();
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
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        update(delta);

        game.batch.begin();
        // Background
        game.batch.draw(background, 0, 0, 1280, 720);
        // Player
        game.player.render(game.batch);
        // Exits
        if (!game.exits.isEmpty()) {
            for (ExitObject e : game.exits) {
                game.batch.draw(ExitObject.texture, e.x, e.y, e.width, e.height);
            }
        }
        // Items
        if (!game.items.isEmpty()) {
            for (Item i : game.items) {
                game.batch.draw(i.getTexture(), i.x, i.y, i.width, i.height);
            }
        }
        // Entities
        if (!game.entities.isEmpty()) {
            for (Entity e : game.entities) {
                e.render(game.batch);
            }
        }
        if (game.pause) game.batch.draw(new Texture(Gdx.files.internal("textures/ui/pause_menu.png")), 415,225, 450, 270);

        playerHealthDisplay.draw(game.batch, "Health: " + game.player.getHealth(), 50, 680);

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
                        else {
                            boolean keyFound = false;
                            for (Item i : game.player.getInventory()) {
                                if (i.getClass() == KeyItem.class) {
                                    if (game.entities.isEmpty()) { // TODO - this if is only TEMP for testing / basically need to kill enemy before entering
                                        game.player.getInventory().removeValue(i, true);
                                        e.unlock();
                                        keyFound = true;
                                        break;
                                    }
                                }
                            }
                            if (!keyFound) System.out.println("This exit is locked... Need to find a key first");
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
                    if (game.items.removeValue(i, true)) {
                        game.player.getInventory().add(i);
                        game.soundManager.playEffect(SoundEffects.POP_EFFECT);
                    }
                    else System.err.println("Item not removed???");
                }
            }
        }
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {
        game.soundManager.playEffect(SoundEffects.PAUSE_EFFECT);
        game.soundManager.decreaseVolume(25);
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

        this.game.player.x = (1280 / 2) - (this.game.player.width / 2);
        this.game.player.y = (720 / 2) - (this.game.player.height / 2);

        currentRoom.onLoad(game, this);
    }

    @Override
    public void hide() {}
    @Override
    public void dispose() {
        background.dispose();
        game.player.dispose();
    }

    public void setBackground(Texture texture) {
        this.background = texture;
    }
}
