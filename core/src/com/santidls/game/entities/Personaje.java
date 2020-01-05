package com.santidls.game.entities;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.santidls.game.utils.Consts;
import com.santidls.game.Screens.GameScreen;

import static com.santidls.game.utils.Consts.LINEAR_VELOCITY_ROTATION_RELATION;
import static com.santidls.game.utils.Consts.MAX_VELOCITY;
import static com.santidls.game.utils.Consts.PIXELES_POR_METRO;

/**
 * Created by Santiago on 15/01/2018.
 */

public class Personaje extends Sprite {
    private Vector2 posicion;
    private Body body;
    private World world;
    private int contador = 0;

    public Personaje(Texture texture, Vector2 posicion, GameScreen game){
        super(texture);
        world=game.getWorld();
        this.posicion=posicion;
        setSize(1,1);
        setPosition(posicion.x + getWidth()/2, posicion.y + getHeight()/2);
        setOrigin((posicion.x / PIXELES_POR_METRO) + getWidth()/2, (posicion.y / PIXELES_POR_METRO) + getHeight() / 2);
        crearPj();
    }

    public void crearPj(){
        BodyDef bdef=new BodyDef();
        bdef.position.set(posicion.x,posicion.y);
        bdef.type= BodyDef.BodyType.DynamicBody;
        body=world.createBody(bdef);
        FixtureDef fixDef=new FixtureDef();
        PolygonShape shape=new PolygonShape();
        shape.setAsBox((getWidth()/2),(getHeight()/2));
        fixDef.shape=shape;
        fixDef.density=0;
        Filter filter = new Filter();
        filter.categoryBits = Consts.PJ_BIT;
        Fixture fixture = body.createFixture(fixDef);
        fixture.setUserData(this);
        fixture.setFilterData(filter);
    }
    public Body getBody(){
        return body;
    }
    public void update(float dt){

        boolean gyroscopeAvail = Gdx.input.isPeripheralAvailable(Input.Peripheral.Gyroscope);
        if(gyroscopeAvail){
            float gyroX = Gdx.input.getGyroscopeX();
            float gyroY = Gdx.input.getGyroscopeY();
            float velX = normalizeSpeed(gyroX);
            float velY = normalizeSpeed(gyroY);

            /*System.out.println("Velocity x = " + velX);
            System.out.println("Velocity y = " + velY);
            System.out.println(String.format("Position/WorldCenter : (%1f, %2f) / (%3f, %4f)", body.getPosition().x, body.getPosition().y, body.getWorldCenter().x, body.getWorldCenter().y));
            System.out.println(String.format("bodyPosition/spritePosition : (%1f, %2f) / (%3f, %4f)", body.getPosition().x, body.getPosition().y, getX(), getY()));*/

            setPosition(body.getPosition().x - getWidth()/2, body.getPosition().y - getHeight()/2);
            //body.setTransform(body.getPosition(), velX * 2 / getWidth());
            body.applyLinearImpulse(new Vector2(velX, velY), body.getWorldCenter(), true);
            System.out.println(velX * 10 * 2 / getWidth());
            body.setAngularVelocity(velX * LINEAR_VELOCITY_ROTATION_RELATION * 2 / getWidth());
            setRotation((float)(body.getAngle() * 180 / Math.PI));


        } else {
            contador++;
            if(contador > 10) {
                System.out.println(dt);
                contador = 0;
                setOrigin((body.getPosition().x / PIXELES_POR_METRO) + getWidth()/2, (body.getPosition().y / PIXELES_POR_METRO) + getHeight() / 2);
                rotate(10);
            }
            //System.out.println("No hay giroscopio");
            //setPosition((body.getPosition().x + getWidth() / 2), (body.getPosition().y + getHeight() / 2));
        }








    }

    private float normalizeSpeed(float velocity) {
        if (velocity > MAX_VELOCITY)
            return MAX_VELOCITY;
        else if(velocity < - MAX_VELOCITY)
            return - MAX_VELOCITY;
        else
            return velocity;
    }

}
