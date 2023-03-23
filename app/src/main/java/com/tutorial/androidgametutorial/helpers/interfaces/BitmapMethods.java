package com.tutorial.androidgametutorial.helpers.interfaces;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.tutorial.androidgametutorial.helpers.GameConstants;

public interface BitmapMethods {

     BitmapFactory.Options options = new BitmapFactory.Options();

     default Bitmap getScaledBitmap(Bitmap bitmap){
          return Bitmap.createScaledBitmap(bitmap,bitmap.getWidth() * GameConstants.Sprite.SCALE_MULTIPLIER,bitmap.getHeight()*GameConstants.Sprite.SCALE_MULTIPLIER,false);
     }
}
