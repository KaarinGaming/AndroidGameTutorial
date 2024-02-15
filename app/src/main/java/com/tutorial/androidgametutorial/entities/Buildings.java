package com.tutorial.androidgametutorial.entities;

import static com.tutorial.androidgametutorial.helpers.GameConstants.Sprite.SCALE_MULTIPLIER;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.graphics.RectF;

import com.tutorial.androidgametutorial.R;
import com.tutorial.androidgametutorial.helpers.GameConstants;
import com.tutorial.androidgametutorial.helpers.interfaces.BitmapMethods;
import com.tutorial.androidgametutorial.main.MainActivity;

public enum Buildings implements BitmapMethods {


    HOUSE_ONE(0, 0, 64, 48, 23, 38, 20, 52);


    Bitmap houseImg;
    PointF doorwayPoint;
    int hitboxRoof, hitboxFloor, hitboxHeight, hitboxWidth;


    Buildings(int x, int y, int width, int height, int doorwayX, int doorwayY, int hitboxRoof, int hitboxFloor) {
        options.inScaled = false;

        this.hitboxRoof = hitboxRoof;
        this.hitboxFloor = hitboxFloor;
        this.hitboxHeight = (hitboxFloor - hitboxRoof) * SCALE_MULTIPLIER;
        this.hitboxWidth = width * SCALE_MULTIPLIER;

        Bitmap atlas = BitmapFactory.decodeResource(MainActivity.getGameContext().getResources(), R.drawable.buildings_atlas, options);
        houseImg = getScaledBitmap(Bitmap.createBitmap(atlas, x, y, width, height));

        doorwayPoint = new PointF(doorwayX * SCALE_MULTIPLIER, doorwayY * SCALE_MULTIPLIER);


    }

    public PointF getDoorwayPoint() {
        return doorwayPoint;
    }

    public int getHitboxRoof() {
        return hitboxRoof;
    }


    public Bitmap getHouseImg() {
        return houseImg;
    }
}
