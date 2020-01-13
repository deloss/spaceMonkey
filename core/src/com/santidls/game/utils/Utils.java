package com.santidls.game.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.santidls.game.entities.Pincho;

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
            xPosition = -Pincho.ROCK_SIZE - ROCK_OFFSET;
            yPosition = rand.nextInt((Consts.GAME_HEIGHT / Consts.PIXELES_POR_METRO) - ROCK_OFFSET) + ROCK_OFFSET;
        }
        else if (side == RIGHT_SIDE) {
            xPosition = (Consts.GAME_WIDTH / Consts.PIXELES_POR_METRO) + Pincho.ROCK_SIZE + ROCK_OFFSET;
            yPosition = rand.nextInt((Consts.GAME_HEIGHT / Consts.PIXELES_POR_METRO) - ROCK_OFFSET) + ROCK_OFFSET;
            double random;
            if( yPosition < Consts.GAME_HEIGHT / (Consts.PIXELES_POR_METRO * 4) )
                random = ((Math.random() / 4) * Math.PI);
            else if(yPosition > Consts.GAME_HEIGHT * 3 / (Consts.PIXELES_POR_METRO * 4))
                random = -1 * ((Math.random() / 4) * Math.PI);
            else
                random = (Math.random() / 2) * Math.PI - Math.PI / 4;
            angle = (float) random; // Antes era 0

        }
        else if (side == TOP_SIDE) {
            xPosition = rand.nextInt((Consts.GAME_HEIGHT / Consts.PIXELES_POR_METRO) - ROCK_OFFSET) + ROCK_OFFSET;
            yPosition = (Consts.GAME_HEIGHT / Consts.PIXELES_POR_METRO) + Pincho.ROCK_SIZE + ROCK_OFFSET;
            double random;
            if( xPosition < Consts.GAME_WIDTH / (Consts.PIXELES_POR_METRO * 4) )
                random = -(((Math.random() / 4) * Math.PI) + (Math.PI / 2));
            else if(xPosition > Consts.GAME_WIDTH * 3 / (Consts.PIXELES_POR_METRO * 4))
                random = ((Math.random() / 4) * Math.PI) + (Math.PI / 2);
            else
                random = -((Math.random() / 2) * Math.PI) + (Math.PI / 4);
            angle = (float) random;
        }
        else {
            xPosition = rand.nextInt((Consts.GAME_HEIGHT / Consts.PIXELES_POR_METRO) - ROCK_OFFSET) + ROCK_OFFSET;
            yPosition = - Pincho.ROCK_SIZE - ROCK_OFFSET;
            double random;
            if( xPosition < Consts.GAME_WIDTH / (Consts.PIXELES_POR_METRO * 4) )
                random = (((Math.random() / 4) * Math.PI) + (Math.PI / 2));
            else if(xPosition > Consts.GAME_WIDTH * 3 / (Consts.PIXELES_POR_METRO * 4))
                random = -((Math.random() / 4) * Math.PI) + (Math.PI / 2);
            else
                random = ((Math.random() / 2) * Math.PI) + (Math.PI / 4);
            angle = (float) random;
        }
        return new Vector3(xPosition, yPosition, angle);
    }

    public static int getRandomInt(int finalInt) {
        return rand.nextInt(3);
    }
}
