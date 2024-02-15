package com.tutorial.androidgametutorial.entities;

import android.graphics.PointF;

public class GameObject extends Entity {
    private GameObjects objectType;

    public GameObject(PointF pos, GameObjects objectType) {
        super(new PointF(pos.x,pos.y + objectType.hitboxRoof),
                objectType.getHitboxWidth(),
                objectType.getHitboxHeight()
        );
        this.objectType = objectType;
    }

    public GameObjects getObjectType() {
        return objectType;
    }
}
