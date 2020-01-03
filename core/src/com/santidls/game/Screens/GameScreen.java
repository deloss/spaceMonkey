package com.santidls.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.santidls.game.EntityCreator;
import com.santidls.game.Estrella;
import com.santidls.game.Hud;
import com.santidls.game.Personaje;
import com.santidls.game.Pincho;
import com.santidls.game.ProcesadorInput;
import com.santidls.game.Utils;
import com.santidls.game.Vakeros;
import com.santidls.game.WorldContactListener;

import javax.swing.text.html.parser.Entity;

/**
 * Created by Santiago on 14/01/2018.
 */

public class GameScreen extends BaseScreen {
    public World world;
    private OrthographicCamera gameCam;
    private Viewport viewport;
    private Box2DDebugRenderer renderer;
    private Personaje pj;
    private SpriteBatch sb;
    private Array<Pincho> pinchos=new Array<Pincho>();
    private Estrella estrella;
    private Hud hud;
    private Vakeros game;
    private float contadorRock;
    private EntityCreator entityCreator;
    Array<Pincho> pinchosAEliminar;
    public GameScreen(Vakeros game){
        this.game = game;
        entityCreator = EntityCreator.getInstance();
        renderer=new Box2DDebugRenderer();
        world=new World(new Vector2(0,0),true);
        gameCam=new OrthographicCamera(Vakeros.GAME_WIDTH/Vakeros.PIXELES_POR_METRO,Vakeros.GAME_HEIGHT/Vakeros.PIXELES_POR_METRO);
        viewport=new FitViewport(gameCam.viewportWidth,gameCam.viewportHeight,gameCam);
        gameCam.position.set(viewport.getWorldWidth()/2,viewport.getWorldHeight()/2,0);
        sb = game.batch;
        hud = new Hud(sb);
        pj = entityCreator.createPj(new Vector2(6,3),this);
        contadorRock = 0;
        pinchosAEliminar = new Array<>();
        pinchos.add(entityCreator.createRock(new Vector2(0.2f,3), (float)Math.PI,this));
        pinchos.add(entityCreator.createRock(new Vector2(11.8f,3),0,this));
        pinchos.add(entityCreator.createRock(new Vector2(6,5.8f),(float)Math.PI/2,this));
        pinchos.add(entityCreator.createRock(new Vector2(6,0.2f),(float)-Math.PI/2,this));
        pinchos.add(entityCreator.createRock(new Vector2(0.2f,0.2f),(float)(-5*Math.PI/6),this));
        estrella = entityCreator.createStar(new Vector2(1f,0.2f),this);
        Gdx.input.setInputProcessor(new ProcesadorInput(pj.getBody()));
        world.setContactListener(new WorldContactListener(this));
    }
    public OrthographicCamera getCam(){
        return gameCam;
    }
    public World getWorld(){
        return world;
    }
    public void update(float delta){
        contadorRock += delta;
        gameCam.update();
        pj.update(delta);
        for(Pincho pincho: pinchos) {
            if(!pincho.isDestroyed())
                pincho.update(delta);
            else {
                pinchosAEliminar.add(pincho);
            }
        }

        if(pinchosAEliminar.notEmpty()) {
            for(Pincho pincho: pinchosAEliminar) {
                pinchos.removeValue(pincho, true);
                System.out.println("Elimnando rock destruido");
            }
            pinchosAEliminar.clear();
        }

        if(estrella.isDestroyed()) {
            Vector3 newRockPosition = Utils.generateRandomLocationForRock();
            pinchos.add(entityCreator.createRock(new Vector2(newRockPosition.x,newRockPosition.y), newRockPosition.z,this));
            estrella = new Estrella(new Texture("banana.png"), Utils.getRandomLocationForStar(pj.getX(), pj.getY()), this);
        }
        estrella.update(delta);
        if(contadorRock > Vakeros.TIME_CREATION_ROCK) {
            Vector3 newRockPosition = Utils.generateRandomLocationForRock();
            pinchos.add(entityCreator.createRock(new Vector2(newRockPosition.x,newRockPosition.y), newRockPosition.z,this));
            contadorRock = 0;
        }
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        world.step(delta,6,2);
        renderer.render(world,gameCam.combined);
        sb.setProjectionMatrix(gameCam.combined);
        sb.begin();
        pj.draw(sb);
        /*for(Pincho pincho : pinchos)
            pincho.draw(sb);*/
        estrella.draw(sb);
        sb.end();

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

    }

    public void upScore() {
        hud.upScore();
    }
}
