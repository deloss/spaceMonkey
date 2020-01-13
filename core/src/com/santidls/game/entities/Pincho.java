package com.santidls.game.entities;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.santidls.game.utils.Consts;
import com.santidls.game.Screens.GameScreen;

import static java.lang.Math.sin;

/**
 * Created by Santiago on 15/01/2018.
 */

public class Pincho extends Sprite {
    public static final float ROCK_SIZE = 0.8f;
    private Body body;
    private Fixture fixture;
    private Vector2 posicion;
    private World world;
    private float angulo;
    private boolean destroyed = false;
    private boolean setToDestroy = false;
    private float velocity;
    private float stateTimer;
    private Animation rockDestroying;
    private Texture destroyingTexture;
    public Pincho(Texture normalTexture, Texture destroyingTexture, Vector2 position, float angulo, GameScreen game){
        super(normalTexture);
        this.destroyingTexture = destroyingTexture;
        world=game.getWorld();
        posicion=position;
        setPosition(posicion.x,posicion.y);
        this.angulo=angulo;
        velocity = 2;
        stateTimer = 0;
        setSize(ROCK_SIZE,ROCK_SIZE);
        crearPinchos();
    }

    public void crearPinchos(){

        Array<TextureRegion> frames = new Array<>();
        for(int i = 0; i < 6; i++) {
            frames.add(new TextureRegion(destroyingTexture, i * 573, 0, 573, 532));
        }
        rockDestroying = new Animation(0.2f, frames);

        BodyDef bdef=new BodyDef();
        bdef.type= BodyDef.BodyType.DynamicBody;
        bdef.position.set(posicion);
        body=world.createBody(bdef);
        FixtureDef fdef=new FixtureDef();
        CircleShape shape=new CircleShape();
        shape.setPosition(new Vector2(0, 0));
        shape.setRadius(ROCK_SIZE / 2);
        fdef.shape=shape;
        fdef.density=100;
        fixture=body.createFixture(fdef);
        fixture.setUserData(this);
        Filter filter = new Filter();
        filter.categoryBits = Consts.ROCK_BIT;
        fixture.setFilterData(filter);
        body.setTransform(body.getPosition(),angulo);
        body.setLinearVelocity((float)(-velocity*Math.cos((double)angulo)),(float)(-velocity*sin((double)angulo)));
    }

    public void update(float dt){
        if(setToDestroy && !destroyed) {
            if(stateTimer == 0)
                world.destroyBody(body);
            setRegion((TextureRegion)rockDestroying.getKeyFrame(stateTimer, false));
            if(rockDestroying.isAnimationFinished(stateTimer)) {
                destroyed = true;
            }
            stateTimer += dt;
        }
        if(!destroyed) {
            setPosition(body.getPosition().x - getWidth()/2, body.getPosition().y - getHeight()/2);
        }
    }

    public void destroy() {
        setToDestroy = true;
        stateTimer = 0;
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
