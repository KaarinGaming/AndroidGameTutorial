package com.tutorial.androidgametutorial.entities;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.tutorial.androidgametutorial.R;
import com.tutorial.androidgametutorial.helpers.interfaces.BitmapMethods;
import com.tutorial.androidgametutorial.main.MainActivity;

public enum GameObjects implements BitmapMethods {

    PILLAR_YELLOW(0, 6, 16, 42),
    STATUE_ANGRY_YELLOW(16, 1, 32, 47),
    MONK_STATUE_BALL_YELLOW(49, 2, 30, 30),
    MONK_STATUE_YELLOW(81, 2, 30, 30),
    SOLDIER_SPEAR_YELLOW(112, 1, 16, 31),
    PLANTER_STICKS_YELLOW(128, 11, 16, 20),
    CUBE_YELLOW(32, 48, 16, 16),
    FROG_YELLOW(48, 38, 32, 26),
    SOLDIER_SWORD_YELLOW(81, 32, 31, 32),
    PILLAR_SHORT_YELLOW(112, 32, 16, 32),
    PILLAR_SNOW_YELLOW(128, 32, 16, 32),
    PILLAR_GREEN(0, 70, 16, 42),
    STATUE_ANGRY_GREEN(16, 65, 32, 47),
    MONK_STATUE_BALL_GREEN(49, 66, 30, 30),
    MONK_STATUE_GREEN(81, 66, 30, 30),
    SOLDIER_SPEAR_GREEN(112, 65, 16, 31),
    PLANTER_STICKS_GREEN(128, 75, 16, 20),
    CUBE_GREEN(32, 112, 16, 16),
    FROG_GREEN(48, 102, 32, 26),
    SOLDIER_SWORD_GREEN(81, 96, 31, 32),
    PILLAR_SHORT_GREEN(112, 96, 16, 32),
    PILLAR_SNOW_GREEN(128, 96, 16, 32),
    POT_ONE_FULL(144, 0, 16, 19),
    POT_ONE_EMPTY(160, 0, 16, 19),
    POT_TWO_FULL(144, 19, 16, 21),
    POT_ONE_FILLED(160, 20, 16, 20),
    BASKET_FULL_RED_FRUIT(144, 40, 16, 16),
    BASKET_FULL_CHICKEN(160, 40, 16, 16),
    BASKET_EMPTY(144, 56, 16, 16),
    BASKET_FULL_BREAD(160, 56, 16, 16),
    OVEN_SNOW_YELLOW(144, 73, 28, 39),
    OVEN_YELLOW(0, 129, 28, 28),
    OVEN_GREEN(28, 128, 30, 29),
    STOMP(58, 128, 16, 22),
    SMALL_POT_FULL(0, 112, 16, 13),
    SMALL_POT_EMPTY(16, 12, 16, 13);

    Bitmap objectImg;
    int width, height;

    GameObjects(int x, int y, int width, int height) {
        options.inScaled = false;
        this.width = width;
        this.height = height;
        Bitmap atlas = BitmapFactory.decodeResource(MainActivity.getGameContext().getResources(), R.drawable.world_objects, options);
        objectImg = getScaledBitmap(Bitmap.createBitmap(atlas, x, y, width, height));

    }

    public Bitmap getObjectImg() {
        return objectImg;
    }
}
