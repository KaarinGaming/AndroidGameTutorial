package com.tutorial.androidgametutorial.entities;

import android.graphics.PointF;
import android.graphics.RectF;

public abstract class Entity implements Comparable<Entity> {

    protected RectF hitbox;
    protected boolean active = true;
    protected float lastCameraYValue = 0;

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

    public void setLastCameraYValue(float lastCameraYValue) {
        this.lastCameraYValue = lastCameraYValue;
    }

    @Override
    public int compareTo(Entity other) {
        return Float.compare(hitbox.top - lastCameraYValue, other.hitbox.top - other.lastCameraYValue);
    }
}
