package com.tutorial.androidgametutorial.entities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.tutorial.androidgametutorial.R;
import com.tutorial.androidgametutorial.helpers.interfaces.BitmapMethods;
import com.tutorial.androidgametutorial.main.MainActivity;

public enum Weapons implements BitmapMethods {

    BIG_SWORD(R.drawable.big_sword),
    SHADOW(R.drawable.shadow);

    final Bitmap weaponImg;

    Weapons(int resId) {
        options.inScaled = false;
        weaponImg = getScaledBitmap(BitmapFactory.decodeResource(MainActivity.getGameContext().getResources(), resId, options));
    }

    public Bitmap getWeaponImg() {
        return weaponImg;
    }

    public int getWidth() {
        return weaponImg.getWidth();
    }

    public int getHeight() {
        return weaponImg.getHeight();
    }
}
