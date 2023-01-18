package com.java.dungeon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.ScreenUtils;
import com.java.dungeon.Backgrounds;
import com.java.dungeon.FontUtils;
import com.java.dungeon.JavaDungeonGame;
import com.java.dungeon.rooms.Rooms;
import com.java.dungeon.sounds.Sounds;


public class WinScreen implements Screen {
    final JavaDungeonGame game;
    private OrthographicCamera camera;
    private Texture background;
    private BitmapFont title;

    public WinScreen(final JavaDungeonGame game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);

        title = FontUtils.getFont(FontUtils.Fonts.BITMGOTHIC, 150, new Color(0.85f, 0.8f, 0.7f, 1f));

        this.game.soundManager.play(Sounds.MAIN_THEME);
        background = Backgrounds.MAIN_MENU.getTexture();
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
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            dispose();
            game.dispose();
            Gdx.app.exit();
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
        title.dispose();
    }

    private void renderText() {
        // TODO - Actually center the title on the screen
        title.draw(game.batch, "YOU WIN!", (camera.viewportWidth / 2) - 450, 550); // TODO - Replace 450 with font width
    }
}
