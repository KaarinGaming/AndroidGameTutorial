package com.tutorial.androidgametutorial.environments;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.RectF;

import com.tutorial.androidgametutorial.entities.Building;
import com.tutorial.androidgametutorial.entities.Buildings;
import com.tutorial.androidgametutorial.helpers.GameConstants;
import com.tutorial.androidgametutorial.helpers.HelpMethods;

import java.util.ArrayList;

public class MapManager {

    private GameMap currentMap, outsideMap, insideMap;
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


    public void drawBuildings(Canvas c) {
        if (currentMap.getBuildingArrayList() != null)
            for (Building b : currentMap.getBuildingArrayList())
                c.drawBitmap(b.getBuildingType().getHouseImg(), b.getPos().x + cameraX, b.getPos().y + cameraY, null);
    }

    public void drawTiles(Canvas c) {
        for (int j = 0; j < currentMap.getArrayHeight(); j++)
            for (int i = 0; i < currentMap.getArrayWidth(); i++)
                c.drawBitmap(currentMap.getFloorType().getSprite(currentMap.getSpriteID(i, j)), i * GameConstants.Sprite.SIZE + cameraX, j * GameConstants.Sprite.SIZE + cameraY, null);
    }

    public void draw(Canvas c) {
        drawTiles(c);
        drawBuildings(c);
    }

    public Doorway isPlayerOnDoorway(RectF playerHitbox) {
        for (Doorway doorway : currentMap.getDoorwayArrayList())
            if (doorway.isPlayerInsideDoorway(playerHitbox, cameraX, cameraY))
                return doorway;

        return null;
    }

    public void changeMap(GameMap gameMap) {
        this.currentMap = gameMap;
    }

    private void initTestMap() {

        int[][] outsideArray = {
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

        int[][] insideArray = {
                {0,   1,  1,  1,  2},
                {22, 23, 23, 23, 24},
                {22, 23, 23, 23, 24},
                {22, 23, 23, 23, 24},
                {44, 45, 45, 45, 46}
        };

        ArrayList<Building> buildingArrayList = new ArrayList<>();
        buildingArrayList.add(new Building(new PointF(200, 200), Buildings.HOUSE_ONE));

        insideMap = new GameMap(insideArray, Tiles.INSIDE, null);
        outsideMap = new GameMap(outsideArray, Tiles.OUTSIDE, buildingArrayList);


        HelpMethods.AddDoorwayToGameMap(outsideMap, insideMap, 0);


        currentMap = outsideMap;
    }
}
