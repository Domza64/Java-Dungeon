package com.java.dungeon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.java.dungeon.InputManager;
import com.java.dungeon.JavaDungeonGame;
import com.java.dungeon.Utils;
import com.java.dungeon.gameObjects.ExitObject;
import com.java.dungeon.gameObjects.Item;
import com.java.dungeon.gameObjects.KeyItem;
import com.java.dungeon.rooms.JsonBaseRoom;
import com.java.dungeon.rooms.Rooms;
import com.java.dungeon.sounds.Sounds;

public class GameScreen implements Screen {
    private final JavaDungeonGame game;
    private final OrthographicCamera camera;
    private Texture background;
    private Array<ExitObject> exits;
    private Array<Item> items;
    long startTime;
    long time = 0;

    public GameScreen(final JavaDungeonGame game, Rooms room) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);

        loadRoom(room);
        startTime = System.currentTimeMillis();
    }

    @Override
    public void show() {

    }

    private void update(float deltaTime) {
        InputManager.movePlayer(game.player);
        InputManager.checkInput(game);
        checkCollision();

        long temp = (System.currentTimeMillis() - startTime) / 1000;
        if (time < temp) {
            time = temp;
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
        if (!exits.isEmpty()) {
            for (ExitObject e : exits) {
                game.batch.draw(ExitObject.texture, e.x, e.y, e.width, e.height);
            }
        }
        // Items
        if (!items.isEmpty()) {
            for (Item i : items) {
                game.batch.draw(i.getTexture(), i.x, i.y, i.width, i.height);
            }
        }

        game.batch.end();
    }

    private void checkCollision() {
        // Checks if player collides with anything

        if (!exits.isEmpty()) {
            for (ExitObject e : exits) {
                if (game.player.intersects(e)) {
                    if (e.getLeadsTo() != null) {
                        if (e.isUnlocked()) {
                            loadRoom(e.getLeadsTo());
                        }
                        else {
                            boolean keyFound = false;
                            for (Item i : game.player.getInventory()) {
                                if (i.getClass() == KeyItem.class) {
                                    game.player.getInventory().removeValue(i, true);
                                    e.unlock();
                                    keyFound = true;
                                    break;
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

        if (!items.isEmpty()) {
            for (Item i : items) {
                if (game.player.intersects(i)) {
                    if (items.removeValue(i, true)) {
                        game.player.getInventory().add(i);
                    }
                    else System.err.println("Item not removed???");
                }
            }
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    private void loadRoom(Rooms room) {
        JsonBaseRoom roomToLoad = Utils.loadRoomFromJson(Gdx.files.internal(room.getPath()));

        this.game.player.x = (1280 / 2) - (this.game.player.width / 2);
        this.game.player.y = (720 / 2) - (this.game.player.height / 2);


        background = roomToLoad.background.getTexture();
        Sounds musicTheme = roomToLoad.music;
        if (musicTheme != null) {
            this.game.soundManager.play(musicTheme);
        }
        spawnItems(roomToLoad);
        createExits(roomToLoad);
    }

    private void spawnItems(JsonBaseRoom room) {
        items = new Array<>(room.items);
    }

    private void createExits(JsonBaseRoom room) {
        exits = new Array<>(room.exits);
    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
