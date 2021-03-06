package com.santidls.game.Screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.santidls.game.utils.Consts;

public class Hud {

    public Stage stage;
    private Viewport viewport;
    private int score;
    private Label scoreLabel;
    private Label scoreTextLabel;

    public Hud(SpriteBatch sb) {
        score = 0;
        viewport = new FitViewport(Consts.GAME_WIDTH/4, Consts.GAME_HEIGHT/4, new OrthographicCamera());
        stage = new Stage(viewport, sb);
        Table table = new Table();
        table.top();
        table.setFillParent(true);
        scoreLabel = new Label(String.format("%02d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreTextLabel = new Label("Score:", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        table.add(scoreTextLabel).padTop(5);
        table.add(scoreLabel).padTop(5).padLeft(5);
        stage.addActor(table);
    }

    public void upScore() {
        scoreLabel.setText(String.format("%02d", ++score));
    }

    public int getScore() {
        return score;
    }
}
