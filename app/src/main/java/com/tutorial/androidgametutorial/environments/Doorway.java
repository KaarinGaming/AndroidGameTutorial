package com.tutorial.androidgametutorial.environments;

import android.graphics.RectF;

public class Doorway {

    private RectF hitbox;
    private boolean active = true;
    private final GameMap gameMap;

    public Doorway(RectF doorwayHitbox, GameMap gameMap) {
        this.hitbox = doorwayHitbox;
        this.gameMap = gameMap;
    }

    public boolean isPlayerInsideDoorway(RectF playerHitbox, float cameraX, float cameraY) {
        System.out.println(playerHitbox.left);
        System.out.println(hitbox.left + cameraX);

        return playerHitbox.intersects(hitbox.left + cameraX, hitbox.top + cameraY, hitbox.right + cameraX, hitbox.bottom + cameraY);
    }

    public boolean isDoorwayActive() {
        return active;
    }

    public void setDoorwayActive(boolean active) {
        this.active = active;
    }

    public GameMap getGameMap() {
        return gameMap;
    }
}
