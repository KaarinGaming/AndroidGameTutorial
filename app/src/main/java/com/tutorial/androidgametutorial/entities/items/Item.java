package com.tutorial.androidgametutorial.entities.items;

import android.graphics.PointF;

import com.tutorial.androidgametutorial.entities.Entity;

public class Item extends Entity {

    private final Items itemType;

    public Item(Items itemType, PointF pos) {
        super(pos, itemType.getWidth(), itemType.getHeight());
        this.itemType = itemType;
    }

    public Items getItemType() {
        return itemType;
    }
}