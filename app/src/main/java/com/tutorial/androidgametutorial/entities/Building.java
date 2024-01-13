package com.tutorial.androidgametutorial.entities;

import android.graphics.PointF;

public class Building extends Entity {

    private Buildings buildingType;

    public Building(PointF pos, Buildings buildingType) {
        super(pos);
        this.buildingType = buildingType;
    }

    public Buildings getBuildingType() {
        return buildingType;
    }

    public PointF getPos() {
        return new PointF(hitbox.left, hitbox.top);
    }

}
