package com.tutorial.androidgametutorial.entities;

import android.graphics.PointF;

public class Building {

    private PointF pos;
    private Buildings buildingType;

    public Building(PointF pos, Buildings buildingType) {
        this.pos = pos;
        this.buildingType = buildingType;
    }

    public Buildings getBuildingType() {
        return buildingType;
    }

    public PointF getPos() {
        return pos;
    }
}
