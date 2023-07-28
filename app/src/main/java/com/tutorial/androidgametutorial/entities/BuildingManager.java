package com.tutorial.androidgametutorial.entities;

import android.graphics.Canvas;
import android.graphics.PointF;

import java.util.ArrayList;

public class BuildingManager {

    private ArrayList<Building> buildingArrayList;
    private float cameraX, cameraY;

    public BuildingManager() {
        buildingArrayList = new ArrayList<>();
        buildingArrayList.add(new Building(new PointF(200, 200), Buildings.HOUSE_ONE));
    }

    public void setCameraValues(float cameraX, float cameraY) {
        this.cameraX = cameraX;
        this.cameraY = cameraY;
    }

    public void draw(Canvas c) {
        for (Building b : buildingArrayList)
            c.drawBitmap(b.getBuildingType().getHouseImg(), b.getPos().x + cameraX, b.getPos().y + cameraY, null);
    }
}
