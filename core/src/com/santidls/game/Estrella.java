package com.santidls.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.santidls.game.Screens.GameScreen;

import static java.lang.Math.sin;

/**
 * Created by Santiago on 15/01/2018.
 */

public class Estrella extends Sprite {
    private Body body;
    private Fixture fixture;
    private Vector2 posicion;
    private World world;
    private OrthographicCamera cam;
    private Texture texture;
    private boolean setToDestroy = false;
    private boolean destroyed = false;
    public Estrella(Texture texture, Vector2 position, GameScreen game){
        super(texture);
        world=game.getWorld();
        cam=game.getCam();
        this.texture=texture;
        posicion=position;
        setPosition(posicion.x,posicion.y);
        setSize(1,1);
        crearEstrella();
    }

    public void crearEstrella(){
        BodyDef bdef=new BodyDef();
        bdef.type= BodyDef.BodyType.StaticBody;
        bdef.position.set(posicion);
        body=world.createBody(bdef);
        FixtureDef fdef=new FixtureDef();
        PolygonShape shape=new PolygonShape();
        Vector2[] vertices=new Vector2[4];
        vertices[0]=new Vector2(1,1);
        vertices[1]=new Vector2(0,1);
        vertices[2]=new Vector2(0,0);
        vertices[3]=new Vector2(1,0);
        shape.set(vertices);
        fdef.shape=shape;
        fdef.density=1;
        fdef.isSensor = true;
        fixture=body.createFixture(fdef);
        fixture.setUserData(this);
        Filter filter = new Filter();
        filter.categoryBits = Vakeros.STAR_BIT;
        fixture.setFilterData(filter);
    }

    public void update(float dt) {
        if(setToDestroy && !destroyed) {
            world.destroyBody(body);
            destroyed = true;
        }
    }

    @Override
    public void draw(Batch batch) {
        if(!destroyed)
            super.draw(batch);
    }

    public void destroyStar() {
        setToDestroy = true;
    }

    public boolean isDestroyed() {
        return destroyed;
    }
}
