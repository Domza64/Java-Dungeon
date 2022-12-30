package com.java.dungeon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.java.dungeon.InputManager;
import com.java.dungeon.JavaDungeonGame;
import com.java.dungeon.gameObjects.ExitObject;
import com.java.dungeon.gameObjects.Item;
import com.java.dungeon.sounds.Sounds;

public class GameScreen implements Screen {
    private final JavaDungeonGame game;
    private OrthographicCamera camera;
    private Texture background;

    private Array<ExitObject> exits;
    private Array<Item> items;

    public GameScreen(final JavaDungeonGame game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);

        background = new Texture(Gdx.files.internal("textures/room_1_background.png"));
        this.game.soundManager.play(Sounds.LEVEL_THEME);
    }

    @Override
    public void show() {

    }

    private void update(float deltaTime) {
        InputManager.movePlayer(game.player);
        InputManager.checkInput(game);
        checkCollision();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        update(delta);

        game.batch.begin();

        game.batch.draw(background, 0, 0, 1280, 720);
        game.batch.draw(game.player.getTexture(), game.player.x, game.player.y, game.player.width, game.player.height);

        game.batch.end();
    }

    private void checkCollision() {
        // Checks if player collides with anything
        for (ExitObject e : exits) {
            if (game.player.intersects(e)) {
                // Do something
            }
        }

        for (Item i : items) {
            if (game.player.intersects(i)) {
                // Do something
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

    @Override
    public void dispose() {
        background.dispose();
    }
}
