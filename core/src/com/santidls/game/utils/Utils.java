package com.santidls.game.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

public class Utils {
    private static final int LEFT_SIDE = 0;
    private static final int RIGHT_SIDE = 1;
    private static final int TOP_SIDE = 2;
    private static final int BOTTOM_SIDE = 3;
    private static Random rand = new Random();

    public static Vector2 getRandomLocationForStar(float pjPositionX, float pjPositionY) {
        float xPosition;
        float yPosition;
        do {
            xPosition = rand.nextInt(Consts.GAME_WIDTH / Consts.PIXELES_POR_METRO - 1);
            yPosition = rand.nextInt(Consts.GAME_HEIGHT / Consts.PIXELES_POR_METRO - 1);
        }while(euclidianDistance(xPosition, yPosition, pjPositionX, pjPositionY) < 5);
        return new Vector2(xPosition, yPosition);
    }

    private static double euclidianDistance(float x1, float y1, float x2, float y2) {
        return Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
    }

    public static Vector3 generateRandomLocationForRock() {
        float xPosition;
        float yPosition;
        float angle;
        int side = rand.nextInt(4);
        if (side == LEFT_SIDE) {
            angle = (float) Math.PI;
            xPosition = 0;
            yPosition = rand.nextInt(Consts.GAME_HEIGHT / Consts.PIXELES_POR_METRO);
        }
        else if (side == RIGHT_SIDE) {
            angle = (float) 0;
            xPosition = Consts.GAME_WIDTH / Consts.PIXELES_POR_METRO;
            yPosition = rand.nextInt(Consts.GAME_HEIGHT / Consts.PIXELES_POR_METRO);
        }
        else if (side == TOP_SIDE) {
            angle = (float) -Math.PI/2;
            xPosition = rand.nextInt(Consts.GAME_WIDTH / Consts.PIXELES_POR_METRO);
            yPosition = 0;
        }
        else {
            angle = (float) Math.PI/2;
            xPosition = rand.nextInt(Consts.GAME_WIDTH / Consts.PIXELES_POR_METRO);
            yPosition = Consts.GAME_HEIGHT / Consts.PIXELES_POR_METRO;
        }
        return new Vector3(xPosition, yPosition, angle);
    }

    public static int getRandomInt(int finalInt) {
        return rand.nextInt(3);
    }
}
