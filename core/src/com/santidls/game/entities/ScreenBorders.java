package com.santidls.game.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.santidls.game.Screens.GameScreen;
import com.santidls.game.SpaceMonkey;
import com.santidls.game.utils.Consts;

public class ScreenBorders {
    private static final float BORDER_SIZE = 0.1f;


    public ScreenBorders(GameScreen game) {
        World world = game.getWorld();

        //Borde izquierdo
        BodyDef bdef=new BodyDef();
        bdef.position.set(0 - BORDER_SIZE/2, Consts.GAME_HEIGHT / 2);
        bdef.type= BodyDef.BodyType.StaticBody;
        Body body = world.createBody(bdef);
        FixtureDef fixDef=new FixtureDef();
        PolygonShape shape=new PolygonShape();
        shape.setAsBox(BORDER_SIZE / 2, Consts.GAME_HEIGHT / 2);
        fixDef.shape=shape;
        Fixture fixture = body.createFixture(fixDef);
        Filter filter = new Filter();
        filter.maskBits = Consts.PJ_BIT;
        fixture.setFilterData(filter);

        //Borde derecho
        bdef=new BodyDef();
        bdef.position.set(Consts.GAME_WIDTH / Consts.PIXELES_POR_METRO + BORDER_SIZE, Consts.GAME_HEIGHT / (Consts.PIXELES_POR_METRO * 2));
        bdef.type= BodyDef.BodyType.StaticBody;
        body = world.createBody(bdef);
        fixDef=new FixtureDef();
        shape=new PolygonShape();
        shape.setAsBox(BORDER_SIZE / 2, Consts.GAME_HEIGHT / 2);
        fixDef.shape=shape;
        fixture = body.createFixture(fixDef);
        filter = new Filter();
        filter.maskBits = Consts.PJ_BIT;
        fixture.setFilterData(filter);

        //Borde arriba
        bdef=new BodyDef();
        bdef.position.set(Consts.GAME_WIDTH / (Consts.PIXELES_POR_METRO * 2), (Consts.GAME_HEIGHT / Consts.PIXELES_POR_METRO) + BORDER_SIZE);
        bdef.type= BodyDef.BodyType.StaticBody;
        body = world.createBody(bdef);
        fixDef=new FixtureDef();
        shape=new PolygonShape();
        shape.setAsBox(Consts.GAME_WIDTH / 2, 0 + BORDER_SIZE / 2);
        fixDef.shape=shape;
        fixture = body.createFixture(fixDef);
        filter = new Filter();
        filter.maskBits = Consts.PJ_BIT;
        fixture.setFilterData(filter);

        //Borde abajo
        bdef=new BodyDef();
        bdef.position.set(Consts.GAME_WIDTH / (Consts.PIXELES_POR_METRO * 2), 0 - BORDER_SIZE);
        bdef.type= BodyDef.BodyType.StaticBody;
        body = world.createBody(bdef);
        fixDef=new FixtureDef();
        shape=new PolygonShape();
        shape.setAsBox(Consts.GAME_WIDTH / 2, 0 + BORDER_SIZE);
        fixDef.shape=shape;
        fixture = body.createFixture(fixDef);
        filter = new Filter();
        filter.maskBits = Consts.PJ_BIT;
        fixture.setFilterData(filter);

    }
}
