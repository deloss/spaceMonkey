package com.santidls.game.entities;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.santidls.game.utils.Consts;
import com.santidls.game.Screens.GameScreen;

import static java.lang.Math.sin;

/**
 * Created by Santiago on 15/01/2018.
 */

public class Pincho extends Sprite {
    private Body body;
    private Fixture fixture;
    private Vector2 posicion;
    private World world;
    private float angulo;
    private boolean destroyed = false;
    private boolean setToDestroy = false;
    private float velocity;
    public Pincho(Texture texture, Vector2 position, float angulo, GameScreen game){
        super(texture);
        world=game.getWorld();
        posicion=position;
        setPosition(posicion.x,posicion.y);
        this.angulo=angulo;
        velocity = 2;
        setSize(1,1);
        crearPinchos();
    }

    public void crearPinchos(){
        BodyDef bdef=new BodyDef();
        bdef.type= BodyDef.BodyType.DynamicBody;
        bdef.position.set(posicion);
        body=world.createBody(bdef);
        FixtureDef fdef=new FixtureDef();
        CircleShape shape=new CircleShape();
        shape.setPosition(new Vector2(0, 0));
        shape.setRadius(0.4f);
        fdef.shape=shape;
        fdef.density=1;
        fixture=body.createFixture(fdef);
        fixture.setUserData(this);
        Filter filter = new Filter();
        filter.categoryBits = Consts.ROCK_BIT;
        fixture.setFilterData(filter);
        body.setTransform(body.getPosition(),angulo);
        body.setLinearVelocity((float)(-velocity*Math.cos((double)angulo)),(float)(-velocity*sin((double)angulo)));
    }
    float contador=0;
    public void update(float dt){
        if(setToDestroy && !destroyed) {
            world.destroyBody(body);
            destroyed = true;
        }
        if(!destroyed) {
            setPosition(body.getPosition().x - getWidth()/2, body.getPosition().y - getHeight()/2);
            contador += dt;
            if (contador > 6) {
                body.setLinearVelocity(body.getLinearVelocity().scl(-1));
                contador = 0;
            }
        }
    }

    public void destroy() {
        setToDestroy = true;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
        body.setLinearVelocity((float)(-velocity*Math.cos((double)angulo)),(float)(-velocity*sin((double)angulo)));
    }

    public float getVelocity() {
        return this.velocity;
    }
}
