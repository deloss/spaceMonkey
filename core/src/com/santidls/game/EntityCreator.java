package com.santidls.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.santidls.game.Screens.GameScreen;

public class EntityCreator {

    private static EntityCreator instance;

    private EntityCreator() {}

    public static EntityCreator getInstance() {
        if(instance == null)
            instance = new EntityCreator();
        return instance;
    }

    public Pincho createRock(Vector2 position, float angulo, GameScreen game) {
        return new Pincho(new Texture("pincho.png"), new Vector2(position.x, position.y), angulo, game);
    }

    public Personaje createPj(Vector2 position, GameScreen game) {
        return new Personaje(new Texture("space_monkey.png"), new Vector2(position.x, position.y), game);
    }

    public Estrella createStar(Vector2 position, GameScreen game) {
        return new Estrella(new Texture("banana.png"), position, game);
    }
}
