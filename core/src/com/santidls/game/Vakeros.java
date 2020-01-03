package com.santidls.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.santidls.game.Screens.GameScreen;

public class Vakeros extends Game {
	public static final int PIXELES_POR_METRO=100;
	public static final int GAME_WIDTH=1200;
	public static final int GAME_HEIGHT=624;
	public static final short PJ_BIT = 1;
	public static final short ROCK_BIT = 2;
	public static final short STAR_BIT = 4;
	public static final float TIME_CREATION_ROCK = 2f;

	public SpriteBatch batch;
	@Override
	public void create() {
		batch = new SpriteBatch();
		setScreen(new GameScreen(this));
	}
}
