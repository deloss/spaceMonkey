package com.santidls.game;

import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Utils {

    public static Vector2 getRandomLocationForStar(float pjPositionX, float pjPositionY) {
        Random rand = new Random();
        float xPosition;
        float yPosition;
        do {
            xPosition = rand.nextInt(Vakeros.GAME_WIDTH / Vakeros.PIXELES_POR_METRO);
            yPosition = rand.nextInt(Vakeros.GAME_HEIGHT / Vakeros.PIXELES_POR_METRO);
        }while(euclidianDistance(xPosition, yPosition, pjPositionX, pjPositionY) < 5);
        return new Vector2(xPosition, yPosition);
    }

    private static double euclidianDistance(float x1, float y1, float x2, float y2) {
        return Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
    }
}
