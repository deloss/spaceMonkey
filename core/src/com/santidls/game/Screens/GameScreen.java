package com.santidls.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.santidls.game.SpaceMonkey;
import com.santidls.game.entities.RockDestroyerBorders;
import com.santidls.game.entities.ScreenBorders;
import com.santidls.game.utils.Consts;
import com.santidls.game.entities.EntityCreator;
import com.santidls.game.entities.Estrella;
import com.santidls.game.entities.Personaje;
import com.santidls.game.entities.Pincho;
import com.santidls.game.utils.ProcesadorInput;
import com.santidls.game.utils.Utils;
import com.santidls.game.WorldContactListener;

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
    private SpaceMonkey game;
    private float contadorRock;
    private EntityCreator entityCreator;
    private Array<Pincho> pinchosAEliminar;
    private Texture backgroundTexture;
    public GameScreen(SpaceMonkey game){
        this.game = game;
        entityCreator = EntityCreator.getInstance(game);
        if(Consts.SET_DEBUG_RENDERER)
            renderer=new Box2DDebugRenderer();
        world=new World(new Vector2(0,0),true);
        gameCam=new OrthographicCamera(Consts.GAME_WIDTH/Consts.PIXELES_POR_METRO,Consts.GAME_HEIGHT/Consts.PIXELES_POR_METRO);
        viewport=new FitViewport(gameCam.viewportWidth,gameCam.viewportHeight,gameCam);
        gameCam.position.set(viewport.getWorldWidth()/2,viewport.getWorldHeight()/2,0);
        sb = game.batch;
        hud = new Hud(sb);
        pj = entityCreator.createPj(new Vector2(6,3),this);
        contadorRock = 0;
        new ScreenBorders(this);
        new RockDestroyerBorders(this);
        backgroundTexture = game.getManager().get("fondogalaxia.png");
        pinchosAEliminar = new Array<>();
        pinchos.add(entityCreator.createRock(this));
        pinchos.add(entityCreator.createRock(this));
        pinchos.add(entityCreator.createRock(this));
        pinchos.add(entityCreator.createRock(this));
        pinchos.add(entityCreator.createRock(this));
        estrella = entityCreator.createStar(pj.getBody().getPosition(),this);

        //Gdx.input.setInputProcessor(new ProcesadorInput(pj.getBody()));

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
            estrella = entityCreator.createStar(pj.getBody().getPosition(), this);
        }
        estrella.update(delta);
        if(contadorRock > Consts.TIME_CREATION_ROCK) {
            pinchos.add(entityCreator.createRock(this));
            contadorRock = 0;
        }
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        world.step(delta,6,2);
        sb.setProjectionMatrix(gameCam.combined);
        sb.begin();
        sb.draw(backgroundTexture, 0, 0, Consts.GAME_WIDTH / Consts.PIXELES_POR_METRO, Consts.GAME_HEIGHT / Consts.PIXELES_POR_METRO);
        pj.draw(sb);
        for(Pincho pincho : pinchos)
            pincho.draw(sb);
        estrella.draw(sb);
        sb.end();
        if(Consts.SET_DEBUG_RENDERER)
            renderer.render(world,gameCam.combined);

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

    }

    public void upScore() {
        hud.upScore();
    }

    public void gameOver() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                game.gameOver(hud.getScore());
            }
        });
        thread.start();
    }

}
