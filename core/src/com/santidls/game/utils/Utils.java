package com.santidls.game.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

public class Utils {
    private static final int LEFT_SIDE = 0;
    private static final int RIGHT_SIDE = 1;
    private static final int TOP_SIDE = 2;
    private static final int BOTTOM_SIDE = 3;
    private static final int ROCK_OFFSET = 1;
    private static Random rand = new Random();

    public static Vector2 getRandomLocationForStar(float pjPositionX, float pjPositionY) {
        float xPosition;
        float yPosition;
        do {
            xPosition = rand.nextInt(Consts.GAME_WIDTH / Consts.PIXELES_POR_METRO - 3) + 1;
            yPosition = rand.nextInt(Consts.GAME_HEIGHT / Consts.PIXELES_POR_METRO - 3) + 1;
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
            double random = ((Math.random() / 2) * 2 * Math.PI / 4) + (3 * Math.PI / 4);
            angle = (float) random; // antes era Math.PI
            xPosition = 0;
            yPosition = rand.nextInt((Consts.GAME_HEIGHT / Consts.PIXELES_POR_METRO) - ROCK_OFFSET) + ROCK_OFFSET;
        }
        else if (side == RIGHT_SIDE) {
            double random = -1 * (((Math.random() / 2) * 2 * Math.PI / 4) + (3 * Math.PI / 4));
            angle = (float) random; // Antes era 0
            xPosition = Consts.GAME_WIDTH / Consts.PIXELES_POR_METRO;
            yPosition = rand.nextInt((Consts.GAME_HEIGHT / Consts.PIXELES_POR_METRO) - ROCK_OFFSET) + ROCK_OFFSET;
        }
        else if (side == TOP_SIDE) {
            double random = (((Math.random() / 2) * 2 * Math.PI / 4) + (3 * Math.PI / 4)) + Math.PI / 2;
            angle = (float) random; // Antes era -Math.PI/2
            xPosition = rand.nextInt((Consts.GAME_HEIGHT / Consts.PIXELES_POR_METRO) - ROCK_OFFSET) + ROCK_OFFSET;
            yPosition = 0;
        }
        else {
            double random = (((Math.random() / 2) * 2 * Math.PI / 4) + (3 * Math.PI / 4)) - Math.PI / 2;
            angle = (float) random;
            xPosition = rand.nextInt(Consts.GAME_WIDTH / Consts.PIXELES_POR_METRO);
            yPosition = rand.nextInt((Consts.GAME_HEIGHT / Consts.PIXELES_POR_METRO) - ROCK_OFFSET) + ROCK_OFFSET;
        }
        return new Vector3(xPosition, yPosition, angle);
    }

    public static int getRandomInt(int finalInt) {
        return rand.nextInt(3);
    }
}
