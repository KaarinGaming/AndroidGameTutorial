package com.tutorial.androidgametutorial.environments;

import android.graphics.PointF;
import android.graphics.RectF;

public class Doorway {


    private boolean active = true;
    private final GameMap gameMapLocatedIn;
    private Doorway doorwayConnectedTo;
    private PointF doorwayPoint;

    public Doorway(PointF doorwayPoint, GameMap gameMapLocatedIn) {
        this.doorwayPoint = doorwayPoint;
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
        return playerHitbox.contains(doorwayPoint.x + cameraX, doorwayPoint.y + cameraY);
    }

    public boolean isDoorwayActive() {
        return active;
    }

    public void setDoorwayActive(boolean active) {
        this.active = active;
    }

    public PointF getPosOfDoorway() {
        return doorwayPoint;
    }

    public GameMap getGameMapLocatedIn() {
        return gameMapLocatedIn;
    }
}
