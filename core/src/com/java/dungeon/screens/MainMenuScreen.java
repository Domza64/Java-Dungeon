package com.java.dungeon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.java.dungeon.FontUtils;
import com.java.dungeon.JavaDungeonGame;
import com.java.dungeon.sounds.Sounds;


public class MainMenuScreen implements Screen {
    final JavaDungeonGame game;
    private OrthographicCamera camera;
    private Texture background;

    public MainMenuScreen(final JavaDungeonGame game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);

        this.game.soundManager.play(Sounds.MAIN_THEME);
        background = new Texture(Gdx.files.internal("textures/main_menu_background.png"));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        update(delta);

        game.batch.begin();
        game.batch.draw(background, 0, 0, 1280, 720);
        renderText();
        game.batch.end();
    }

    private void update(float deltaTime) {
        // Intersector.

        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            game.soundManager.stopPlaying();
            game.setScreen(new GameScreen(game));
            dispose();
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

    private void renderText() {
        // TODO - Actually center the title on the screen
        FontUtils.getFont(FontUtils.Fonts.BITMGOTHIC, 150, new Color(0.85f, 0.8f, 0.7f, 1f)).draw(game.batch, "Java Dungeon", 180, 550);
        FontUtils.getFont(FontUtils.Fonts.MINECRAFT, 24, new Color(1.0f, 1.0f, 1.0f, 1.0f)).draw(game.batch, "Press ENTER to Start!", 500, 250);
    }
}
