package com.tutorial.androidgametutorial.environments;

import android.graphics.Canvas;

import com.tutorial.androidgametutorial.entities.Building;
import com.tutorial.androidgametutorial.helpers.GameConstants;

import java.util.ArrayList;

public class GameMap {

    private int[][] spriteIds;
    private Floor floorType;
    private ArrayList<Building> buildingArrayList;

    public GameMap(int[][] spriteIds, Floor floorType, ArrayList<Building> buildingArrayList) {
        this.spriteIds = spriteIds;
        this.floorType = floorType;
        this.buildingArrayList = buildingArrayList;
    }

    public ArrayList<Building> getBuildingArrayList() {
        return buildingArrayList;
    }

    public Floor getFloorType() {
        return floorType;
    }

    public int getSpriteID(int xIndex, int yIndex) {
        return spriteIds[yIndex][xIndex];
    }

    public int getArrayWidth() {
        return spriteIds[0].length;
    }

    public int getArrayHeight() {
        return spriteIds.length;
    }


}
