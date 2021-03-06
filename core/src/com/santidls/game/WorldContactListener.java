package com.santidls.game;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.santidls.game.Screens.GameScreen;
import com.santidls.game.entities.Estrella;
import com.santidls.game.entities.Personaje;
import com.santidls.game.entities.Pincho;
import com.santidls.game.utils.Consts;

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
            case Consts.PJ_BIT | Consts.STAR_BIT:
                if(fixA.getFilterData().categoryBits == Consts.STAR_BIT) {
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

            case Consts.ROCK_BIT:
                ((Pincho)fixA.getUserData()).destroy();
                ((Pincho)fixB.getUserData()).destroy();
                break;

            case Consts.PJ_BIT | Consts.ROCK_BIT:
                if(fixA.getFilterData().categoryBits == Consts.PJ_BIT)
                    ((Personaje)fixA.getUserData()).pjDead();
                else
                    ((Personaje)fixB.getUserData()).pjDead();
                screen.gameOver();
                break;
            case Consts.ROCK_BIT | Consts.ROCK_DESTOYER_BORDER_BIT:
                if(fixA.getFilterData().categoryBits == Consts.ROCK_BIT)
                    ((Pincho)fixA.getUserData()).destroy();
                else
                    ((Pincho)fixB.getUserData()).destroy();

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
