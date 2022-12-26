package com.java.dungeon.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.java.dungeon.InputManager;
import com.java.dungeon.JavaDungeonGame;
import com.java.dungeon.sounds.Sounds;

public class GameScreen implements Screen {
    private final JavaDungeonGame game;
    private OrthographicCamera camera;
    private Texture background;

    public GameScreen(final JavaDungeonGame game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);

        this.game.soundManager.play(Sounds.LEVEL_THEME);
        background = new Texture(Gdx.files.internal("textures/background.png"));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();

        // Actually do stuff
        InputManager.movePlayer(game.player);
        InputManager.checkInput(game);

        game.batch.draw(background, 0, 0, 1280, 720);
        game.batch.draw(game.player.getTexture(), game.player.x, game.player.y, game.player.width, game.player.height);

        game.batch.end();
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
