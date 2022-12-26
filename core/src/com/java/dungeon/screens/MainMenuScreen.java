package com.java.dungeon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.ScreenUtils;
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

        game.batch.begin();
        game.batch.draw(background, 0, 0, 1280, 720);
        renderTitle();
        renderStartText();
        game.batch.end();

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

    private void renderTitle() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/bitmgothic/Bitmgothic.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 150;
        parameter.color = new Color(0.85f, 0.8f, 0.7f, 1f);
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();

        font.draw(game.batch, "Java Dungeon", 180, 550); // TODO - Actually center the title on the screen
    }
    private void renderStartText() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/minecraft/Minecraft.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 24;
        parameter.color = new Color(1.0f, 1.0f, 1.0f, 1f);
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();

        font.draw(game.batch, "Press ENTER to Start!", 500, 250); // TODO - Actually center the title on the screen
    }
}
