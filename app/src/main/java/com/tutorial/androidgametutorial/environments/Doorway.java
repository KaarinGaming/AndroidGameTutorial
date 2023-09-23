package com.tutorial.androidgametutorial.environments;

import android.graphics.PointF;
import android.graphics.RectF;

public class Doorway {

    private RectF hitbox;
    private boolean active = true;
    private final GameMap gameMapLocatedIn;
    private Doorway doorwayConnectedTo;

    public Doorway(RectF doorwayHitbox, GameMap gameMapLocatedIn) {
        this.hitbox = doorwayHitbox;
        this.gameMapLocatedIn = gameMapLocatedIn;
        gameMapLocatedIn.addDoorway(this);
    }

    public void connectDoorway(Doorway destinationDoorway) {
        this.doorwayConnectedTo = destinationDoorway;
    }

    public Doorway getDoorwayConnectedTo() {
        if (doorwayConnectedTo != null)
            return doorwayConnectedTo;
        return null;
    }

    public boolean isPlayerInsideDoorway(RectF playerHitbox, float cameraX, float cameraY) {


        return playerHitbox.intersects(hitbox.left + cameraX, hitbox.top + cameraY, hitbox.right + cameraX, hitbox.bottom + cameraY);
    }

    public boolean isDoorwayActive() {
        return active;
    }

    public void setDoorwayActive(boolean active) {
        this.active = active;
    }

    public PointF getPosOfDoorway() {
        return new PointF(hitbox.left, hitbox.top);
    }

    public GameMap getGameMapLocatedIn() {
        return gameMapLocatedIn;
    }
}
