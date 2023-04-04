package com.tutorial.androidgametutorial.environments;

import android.graphics.Canvas;

import com.tutorial.androidgametutorial.helpers.GameConstants;

public class MapManager {

    private GameMap currentMap;
    private float cameraX, cameraY;

    public MapManager() {
        initTestMap();
    }

    public void setCameraValues(float cameraX, float cameraY) {
        this.cameraX = cameraX;
        this.cameraY = cameraY;
    }

    public boolean canMoveHere(float x, float y) {
        if (x < 0 || y < 0)
            return false;

        if (x >= getMaxWidthCurrentMap() || y >= getMaxHeightCurrentMap())
            return false;

        return true;
    }

    public int getMaxWidthCurrentMap() {
        return currentMap.getArrayWidth() * GameConstants.Sprite.SIZE;
    }

    public int getMaxHeightCurrentMap() {
        return currentMap.getArrayHeight() * GameConstants.Sprite.SIZE;
    }


    public void draw(Canvas c) {
        for (int j = 0; j < currentMap.getArrayHeight(); j++)
            for (int i = 0; i < currentMap.getArrayWidth(); i++)
                c.drawBitmap(Floor.OUTSIDE.getSprite(currentMap.getSpriteID(i, j)), i * GameConstants.Sprite.SIZE + cameraX, j * GameConstants.Sprite.SIZE + cameraY, null);
    }

    private void initTestMap() {

        int[][] spriteIds = {
                {454, 276, 275, 275, 190, 275, 190, 275, 275, 279, 190, 275, 190, 275, 275, 279, 190, 275, 190, 275, 275, 279, 275},
                {454, 276, 275, 275, 190, 275, 190, 275, 275, 279, 190, 275, 190, 275, 275, 279, 190, 275, 190, 275, 275, 279, 275},
                {454, 276, 275, 275, 190, 275, 190, 275, 275, 279, 190, 275, 190, 275, 275, 279, 190, 275, 190, 275, 275, 279, 275},
                {454, 276, 275, 275, 190, 275, 190, 275, 275, 279, 190, 275, 190, 275, 275, 279, 190, 275, 190, 275, 275, 279, 275},
                {454, 276, 275, 275, 190, 275, 190, 275, 275, 279, 190, 275, 190, 275, 275, 279, 190, 275, 190, 275, 275, 279, 275},
                {454, 276, 275, 275, 190, 275, 190, 275, 275, 279, 190, 275, 190, 275, 275, 279, 190, 275, 190, 275, 275, 279, 275},
                {454, 276, 275, 275, 190, 275, 190, 275, 275, 279, 190, 275, 190, 275, 275, 279, 190, 275, 190, 275, 275, 279, 275},
                {454, 276, 275, 275, 190, 275, 190, 275, 275, 279, 190, 275, 190, 275, 275, 279, 190, 275, 190, 275, 275, 279, 275},
                {454, 276, 275, 275, 190, 275, 190, 275, 275, 279, 190, 275, 190, 275, 275, 279, 190, 275, 190, 275, 275, 279, 275},
                {454, 276, 275, 275, 190, 275, 190, 275, 275, 279, 190, 275, 190, 275, 275, 279, 190, 275, 190, 275, 275, 279, 275},
                {454, 276, 275, 275, 190, 275, 190, 275, 275, 279, 190, 275, 190, 275, 275, 279, 190, 275, 190, 275, 275, 279, 275},
                {454, 276, 275, 275, 190, 275, 190, 275, 275, 279, 190, 275, 190, 275, 275, 279, 190, 275, 190, 275, 275, 279, 275},
                {454, 276, 275, 275, 190, 275, 190, 275, 275, 279, 190, 275, 190, 275, 275, 279, 190, 275, 190, 275, 275, 279, 275},
                {454, 276, 275, 275, 190, 275, 190, 275, 275, 279, 190, 275, 190, 275, 275, 279, 190, 275, 190, 275, 275, 279, 275},
                {454, 276, 275, 275, 190, 275, 190, 275, 275, 279, 190, 275, 190, 275, 275, 279, 190, 275, 190, 275, 275, 279, 275},
                {454, 276, 275, 275, 190, 275, 190, 275, 275, 279, 190, 275, 190, 275, 275, 279, 190, 275, 190, 275, 275, 279, 275}


        };

        currentMap = new GameMap(spriteIds);
    }
}
