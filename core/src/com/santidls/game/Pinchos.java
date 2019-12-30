package com.santidls.game;

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
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.santidls.game.Screens.GameScreen;

import static java.lang.Math.sin;

/**
 * Created by Santiago on 15/01/2018.
 */

public class Pinchos extends Sprite {
    private Body body;
    private Fixture fixture;
    private Vector2 posicion;
    private World world;
    private OrthographicCamera cam;
    private Texture texture;
    private float angulo;
    private boolean destroyed = false;
    private boolean setToDestroy = false;
    public Pinchos(Texture texture, Vector2 position, float angulo, GameScreen game){
        super(texture);
        world=game.getWorld();
        cam=game.getCam();
        this.texture=texture;
        posicion=position;
        setPosition(posicion.x,posicion.y);
        this.angulo=angulo;
        setSize(getWidth()/Vakeros.PIXELES_POR_METRO,getHeight()/Vakeros.PIXELES_POR_METRO);
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
        shape.setRadius(1);
        fdef.shape=shape;
        fdef.density=1;
        fixture=body.createFixture(fdef);
        fixture.setUserData(this);
        Filter filter = new Filter();
        filter.categoryBits = Vakeros.ROCK_BIT;
        fixture.setFilterData(filter);
        body.setTransform(body.getPosition(),angulo);
        body.setLinearVelocity((float)(-1*Math.cos((double)angulo)),(float)(-1*sin((double)angulo)));
    }
    float contador=0;
    public void update(float dt){
        if(setToDestroy && !destroyed) {
            world.destroyBody(body);
            destroyed = true;
        }
        if(!destroyed) {
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
}
