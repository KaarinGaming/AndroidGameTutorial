package com.tutorial.androidgametutorial.entities;

import android.graphics.PointF;
import android.graphics.RectF;

public abstract class Entity {

    protected RectF hitbox;

    public Entity(PointF pos, float width, float height){
        this.hitbox = new RectF(pos.x,pos.y,width,height);
    }

    public RectF getHitbox() {
        return hitbox;
    }
}
