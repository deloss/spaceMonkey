package com.santidls.game;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.santidls.game.Screens.GameScreen;

public class WorldContactListener implements ContactListener {
    private GameScreen screen;

    public WorldContactListener(GameScreen screen) {
        this.screen = screen;
    }
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

        switch (cDef){
            case Vakeros.PJ_BIT | Vakeros.STAR_BIT:
                if(fixA.getFilterData().categoryBits == Vakeros.STAR_BIT) {
                    System.out.println("Colision entre estrella y pj");
                    ((Estrella)fixA.getUserData()).destroyStar();
                    screen.upScore();
                }
                else {
                    System.out.println("Colision entre estrella y pj");
                    ((Estrella)fixB.getUserData()).destroyStar();
                    screen.upScore();
                }
                break;

            case Vakeros.ROCK_BIT:
                ((Pinchos)fixA.getUserData()).destroy();
                ((Pinchos)fixB.getUserData()).destroy();
                break;
        }
    }

    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
