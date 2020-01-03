package com.santidls.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import javax.swing.GroupLayout;

public class Hud {

    public Stage stage;
    private Viewport viewport;
    private int score;
    private Label scoreLabel;
    private Label scoreTextLabel;

    public Hud(SpriteBatch sb) {
        score = 0;
        viewport = new FitViewport(Vakeros.GAME_WIDTH/4, Vakeros.GAME_HEIGHT/4, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        //make the table fill the entire stage
        table.setFillParent(true);

        //define our labels using the String, and a Label style consisting of a font and color
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
