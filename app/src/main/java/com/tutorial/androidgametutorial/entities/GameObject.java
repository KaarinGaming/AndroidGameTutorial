package com.tutorial.androidgametutorial.entities;

import android.graphics.PointF;

public class GameObject extends Entity {
    private GameObjects objectType;

    public GameObject(PointF pos, GameObjects objectType) {
        super(pos, objectType.width, objectType.height);
        this.objectType = objectType;
    }

    public GameObjects getObjectType() {
        return objectType;
    }
}
