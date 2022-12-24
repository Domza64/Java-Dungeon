package com.java.dungeon;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.*;

public class JavaDungeonGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture background, playerTexture;
	private OrthographicCamera camera;
	private Rectangle player;

	
	@Override
	public void create () {
		createTextures();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1280, 720);
		batch = new SpriteBatch();

		createPlayer();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0.2f, 1);

		camera.update();

		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		batch.draw(background, 0, 0, 1280, 720);
		batch.draw(playerTexture, player.x, player.y, player.width, player.height);
		batch.end();

		procesInput();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		background.dispose();
	}

	private void createTextures() {
		background = new Texture(Gdx.files.internal("background.png"));
		playerTexture = new Texture(Gdx.files.internal("character.png"));
	}

	private void createPlayer() {
		player = new Rectangle();
		player.x = 1280 / 2 - 21 / 2;
		player.y = 720 / 2 - 15 / 2;
		player.width = 15 * 5;
		player.height = 21 * 5;
	}

	private void procesInput() {
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) player.x -= 200 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) player.x += 200 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.UP)) player.y += 200 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) player.y -= 200 * Gdx.graphics.getDeltaTime();

		if(player.x < 0) player.x = 0;
		if(player.y < 0) player.y = 0;

		if(player.x > 1280 - player.width) player.x = 1280 - player.width;

		if(player.y > 720 - player.height) player.y = 720 - player.height;
	}
}
