package com.santidls.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.santidls.game.Screens.BaseScreen;
import com.santidls.game.Screens.GameOverScreen;
import com.santidls.game.Screens.GameScreen;
import com.santidls.game.Screens.LoadingScreen;
import com.santidls.game.Screens.MenuScreen;

public class SpaceMonkey extends Game {
	public SpriteBatch batch;
	private AssetManager manager;

	private GameOverScreen gameOverScreen;
	public BaseScreen gameScreen, menuScreen, loadingScreen;
	@Override
	public void create() {
		manager = new AssetManager();
		manager.load("roca1.png", Texture.class);
		manager.load("roca2.png", Texture.class);
		manager.load("roca3.png", Texture.class);
		manager.load("banana.png", Texture.class);
		manager.load("space-monkey-solo.png", Texture.class);
		manager.load("space-monkey-animation.png", Texture.class);
		manager.load("pj_dead.png", Texture.class);
		manager.load("fondogalaxia.png", Texture.class);
		manager.load("rock_destroying.png", Texture.class);
		loadingScreen = new LoadingScreen(this);
		batch = new SpriteBatch();
		setScreen(loadingScreen);
	}

	public void gameOver(int score) {
		gameOverScreen.setScoreLabel(score);
		setScreen(gameOverScreen);
	}

	public void finishLoading() {
		gameOverScreen = new GameOverScreen(this);
		menuScreen = new MenuScreen(this);
		setScreen(menuScreen);
	}

	public void newGame() {
		if(gameScreen != null)
			gameScreen.dispose();
		gameScreen = new GameScreen(this);
		setScreen(gameScreen);
	}

	public AssetManager getManager() {
		return manager;
	}
}
