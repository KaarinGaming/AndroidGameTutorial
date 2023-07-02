package com.tutorial.androidgametutorial.entities;

import android.graphics.PointF;
import android.graphics.RectF;

public abstract class Entity {

    protected RectF hitbox;
    protected boolean active = true;

    public Entity(PointF pos, float width, float height) {
        this.hitbox = new RectF(pos.x, pos.y, pos.x + width, pos.y + height);
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public RectF getHitbox() {
        return hitbox;
    }
}
