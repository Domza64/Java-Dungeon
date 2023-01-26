package com.java.dungeon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.java.dungeon.Backgrounds;
import com.java.dungeon.FontUtils;
import com.java.dungeon.JavaDungeonGame;
import com.java.dungeon.sounds.Sounds;


public class MainMenuScreen implements Screen {
    final JavaDungeonGame game;
    private final FillViewport viewport;
    private final TextureRegion background;
    private final Texture texture;
    private final BitmapFont title;
    private final BitmapFont startText;

    public MainMenuScreen(final JavaDungeonGame game) {
        this.game = game;

        viewport = new FillViewport(1280, 720, new OrthographicCamera());

        startText = FontUtils.getFont(FontUtils.Fonts.MINECRAFT, 24, new Color(1.0f, 1.0f, 1.0f, 1.0f));
        title = FontUtils.getFont(FontUtils.Fonts.BITMGOTHIC, 150, new Color(0.85f, 0.8f, 0.7f, 1f));

        this.game.soundManager.play(Sounds.MAIN_THEME);
        texture = Backgrounds.MAIN_MENU.getTexture();
        background = new TextureRegion(texture);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);

        viewport.apply(true);
        game.batch.setProjectionMatrix(viewport.getCamera().combined);

        update(delta);

        game.batch.begin();
        game.batch.draw(background, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
        renderText();
        game.batch.end();
    }

    private void update(float deltaTime) {
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            game.soundManager.stopPlaying();
            game.start();
            dispose();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            dispose();
            game.dispose();
            Gdx.app.exit();
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
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
        title.dispose();
        startText.dispose();
        texture.dispose();
    }

    private void renderText() {
        // TODO - Actually center the title on the screen
        String startText = "Press ENTER to Start!";
        if (game.isControllerConnected()) {
            startText = "Press X to Start!";
        }
        title.draw(game.batch, "Java Dungeon", ((float) viewport.getScreenX() / 2) + 180, 580); // TODO - Replace 180 with font width
        this.startText.draw(game.batch, startText, ((float) viewport.getScreenX() / 2) + 500, 250); // TODO - Replace 500 with font width
    }
}
