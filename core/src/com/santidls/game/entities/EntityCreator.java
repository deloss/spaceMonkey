package com.santidls.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.santidls.game.Screens.GameScreen;
import com.santidls.game.utils.Utils;

public class EntityCreator {

    //cambiar por assetmanager
    public static Texture pinchoTexture1 = new Texture("roca1.png");
    public static Texture pinchoTexture2 = new Texture("roca2.png");
    public static Texture pinchoTexture3 = new Texture("roca3.png");
    //

    private static EntityCreator instance;

    private EntityCreator() {}

    public static EntityCreator getInstance() {
        if(instance == null)
            instance = new EntityCreator();
        return instance;
    }

    public Pincho createRock(Vector2 position, float angulo, GameScreen game) {
        int rocaTextureIndex = Utils.getRandomInt(3);
        if(rocaTextureIndex == 0)
            return new Pincho(pinchoTexture1, new Vector2(position.x, position.y), angulo, game);
        else if(rocaTextureIndex == 1)
            return new Pincho(pinchoTexture2, new Vector2(position.x, position.y), angulo, game);
        else
            return new Pincho(pinchoTexture3, new Vector2(position.x, position.y), angulo, game);
    }

    public Personaje createPj(Vector2 position, GameScreen game) {
        return new Personaje(new Vector2(position.x, position.y), game);
    }

    public Estrella createStar(Vector2 position, GameScreen game) {
        return new Estrella(new Texture("banana.png"), position, game);
    }
}
