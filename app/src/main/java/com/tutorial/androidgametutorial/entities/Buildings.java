package com.tutorial.androidgametutorial.entities;

import static com.tutorial.androidgametutorial.helpers.GameConstants.Sprite.SCALE_MULTIPLIER;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;

import com.tutorial.androidgametutorial.R;
import com.tutorial.androidgametutorial.helpers.interfaces.BitmapMethods;
import com.tutorial.androidgametutorial.main.MainActivity;

public enum Buildings implements BitmapMethods {


    HOUSE_ONE(0, 0, 64, 48, 17,32,14,15);


    Bitmap houseImg;
    RectF hitboxDoorway;

    Buildings(int x, int y, int width, int height, int doorwayX, int doorwayY, int doorwayWidth, int doorwayHeight) {
        options.inScaled = false;

        Bitmap atlas = BitmapFactory.decodeResource(MainActivity.getGameContext().getResources(), R.drawable.buildings_atlas, options);
        houseImg = getScaledBitmap(Bitmap.createBitmap(atlas, x, y, width, height));

        hitboxDoorway = new RectF(
                doorwayX * SCALE_MULTIPLIER,
                doorwayY * SCALE_MULTIPLIER,
                (doorwayX + doorwayWidth) * SCALE_MULTIPLIER,
                (doorwayY + doorwayHeight) * SCALE_MULTIPLIER);
    }

    public RectF getHitboxDoorway() {
        return hitboxDoorway;
    }

    public Bitmap getHouseImg() {
        return houseImg;
    }
}
