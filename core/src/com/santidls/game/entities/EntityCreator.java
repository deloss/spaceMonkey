package com.santidls.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.santidls.game.Screens.GameScreen;
import com.santidls.game.SpaceMonkey;
import com.santidls.game.utils.Utils;

public class EntityCreator {

    //cambiar por assetmanager
    private Texture pinchoTexture1;
    private Texture pinchoTexture2;
    private Texture pinchoTexture3;
    private Texture banana;
    //

    private static EntityCreator instance;
    private SpaceMonkey game;

    private EntityCreator(SpaceMonkey game) {
        this.game = game;
        pinchoTexture1 = game.getManager().get("roca1.png");
        pinchoTexture2 = game.getManager().get("roca2.png");
        pinchoTexture3 = game.getManager().get("roca3.png");
        banana = game.getManager().get("banana.png");
    }

    public static EntityCreator getInstance(SpaceMonkey game) {
        if(instance == null)
            instance = new EntityCreator(game);
        return instance;
    }

    public Pincho createRock(GameScreen game) {
        Vector3 position = Utils.generateRandomLocationForRock();
        int rocaTextureIndex = Utils.getRandomInt(3);
        if(rocaTextureIndex == 0)
            return new Pincho(pinchoTexture1, new Vector2(position.x, position.y), position.z, game);
        else if(rocaTextureIndex == 1)
            return new Pincho(pinchoTexture2, new Vector2(position.x, position.y), position.z, game);
        else
            return new Pincho(pinchoTexture3, new Vector2(position.x, position.y), position.z, game);
    }

    public Personaje createPj(Vector2 position, GameScreen game) {
        return new Personaje(new Vector2(position.x, position.y), game);
    }

    public Estrella createStar(Vector2 position, GameScreen game) {
        return new Estrella(banana, Utils.getRandomLocationForStar(position.x, position.y), game);
    }
}
