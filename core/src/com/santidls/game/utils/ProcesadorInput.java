package com.santidls.game.utils;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by Santiago on 18/01/2018.
 */

public class ProcesadorInput implements InputProcessor {
    private Body pj;
    public ProcesadorInput(Body pj){
        this.pj=pj;
    }
    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.UP){
            pj.applyLinearImpulse(new Vector2(0,2),pj.getWorldCenter(),true);
            return true;
        }else if(keycode == Input.Keys.RIGHT){
            pj.applyLinearImpulse(new Vector2(4,0),pj.getWorldCenter(),true);
            return true;
        }else if(keycode == Input.Keys.DOWN){
            pj.applyLinearImpulse(new Vector2(0,-2),pj.getWorldCenter(),true);
            return true;
        }else if(keycode == Input.Keys.LEFT){
            pj.applyLinearImpulse(new Vector2(-4,0),pj.getWorldCenter(),true);
            return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        pj.applyLinearImpulse(new Vector2(0,1),pj.getWorldCenter(),true);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
