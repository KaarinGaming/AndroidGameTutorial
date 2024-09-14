package com.tutorial.androidgametutorial.entities.items;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.tutorial.androidgametutorial.R;
import com.tutorial.androidgametutorial.helpers.interfaces.BitmapMethods;
import com.tutorial.androidgametutorial.main.MainActivity;

public enum Items implements BitmapMethods {

    EMPTY_POT(R.drawable.empty_pot),
    MEDIPACK(R.drawable.medipack),
    FISH(R.drawable.fish);

    final Bitmap image;

    Items(int resId) {
        options.inScaled = false;
        image = getScaledBitmap(BitmapFactory.decodeResource(MainActivity.getGameContext().getResources(), resId, options));
    }


    public Bitmap getImage() {
        return image;
    }

    public int getWidth() {
        return image.getWidth();
    }

    public int getHeight() {
        return image.getHeight();
    }
}
