package com.santidls.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.santidls.game.Screens.GameScreen;

import java.io.IOException;

/**
 * Created by Santiago on 15/01/2018.
 */

public class Personaje extends Sprite {
    private Vector2 posicion;
    public Body body;
    private World world;
    private OrthographicCamera cam;
    private Texture texture;

    public Personaje(Texture texture, Vector2 posicion, GameScreen game){
        super(texture);
        world=game.getWorld();
        cam=game.getCam();
        this.posicion=posicion;
        this.texture=texture;
        //setPosition(posicion.x*Vakeros.PIXELES_POR_METRO,posicion.y*Vakeros.PIXELES_POR_METRO);
        setSize(getWidth()/(Vakeros.PIXELES_POR_METRO*2),getHeight()/(Vakeros.PIXELES_POR_METRO*2));
        crearPj();
        //System.out.println(getWidth());
    }
    Vector2 tmp= new Vector2();
    public void crearPj(){
        BodyDef bdef=new BodyDef();
        //bdef.position.set((getX()+getWidth()/2)/Vakeros.PIXELES_POR_METRO,(getY()+getHeight()/2)/Vakeros.PIXELES_POR_METRO);
        bdef.position.set(posicion.x,posicion.y);
        bdef.type= BodyDef.BodyType.DynamicBody;
        body=world.createBody(bdef);
        FixtureDef fixDef=new FixtureDef();
        PolygonShape shape=new PolygonShape();
        shape.setAsBox((getWidth()/2),(getHeight()/2));
        fixDef.shape=shape;
        fixDef.density=0;
        Filter filter = new Filter();
        filter.categoryBits = Vakeros.PJ_BIT;
        Fixture fixture = body.createFixture(fixDef);
        fixture.setUserData(this);
        fixture.setFilterData(filter);
        tmp.set(body.getPosition());
        //System.out.println(tmp.x + " " + tmp.y + " " + getX() + " " + getY());
    }
    public Body getBody(){
        return body;
    }
    public void update(float dt){
        if(Gdx.input.isTouched()) {
            //body.applyLinearImpulse(new Vector2(5, 10), body.getWorldCenter(), true);
        }

        setPosition((body.getPosition().x-getWidth()/2),(body.getPosition().y-getHeight()/2));



    }

}
