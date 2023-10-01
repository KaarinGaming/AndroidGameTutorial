package com.tutorial.androidgametutorial.helpers;

import android.graphics.PointF;
import android.graphics.RectF;

import com.tutorial.androidgametutorial.entities.Building;
import com.tutorial.androidgametutorial.entities.enemies.Skeleton;
import com.tutorial.androidgametutorial.environments.Doorway;
import com.tutorial.androidgametutorial.environments.GameMap;

import java.util.ArrayList;

public class HelpMethods {

    public static void AddDoorwayToGameMap(GameMap gameMapLocatedIn, GameMap gameMapTarget, int buildingIndex) {
        float houseX = gameMapLocatedIn.getBuildingArrayList().get(buildingIndex).getPos().x;
        float houseY = gameMapLocatedIn.getBuildingArrayList().get(buildingIndex).getPos().y;

        RectF hitbox = gameMapLocatedIn.getBuildingArrayList().get(buildingIndex).getBuildingType().getHitboxDoorway();
        Doorway doorway = new Doorway(new RectF(hitbox.left + houseX, hitbox.top + houseY, hitbox.right + houseX, hitbox.bottom + houseY), gameMapTarget);

        gameMapLocatedIn.addDoorway(doorway);

    }

    public static RectF CreateHitboxForDoorway(GameMap gameMapLocatedIn, int buildingIndex) {
        Building building = gameMapLocatedIn.getBuildingArrayList().get(buildingIndex);

        float x = building.getPos().x;
        float y = building.getPos().y;
        RectF hitbox = gameMapLocatedIn.getBuildingArrayList().get(buildingIndex).getBuildingType().getHitboxDoorway();

        return new RectF(hitbox.left + x, hitbox.top + y, hitbox.right + x, hitbox.bottom + y);

    }

    public static RectF CreateHitboxForDoorway(int xTile, int yTile) {
        float x = xTile * GameConstants.Sprite.SIZE;
        float y = yTile * GameConstants.Sprite.SIZE;

        return new RectF(x, y, x + GameConstants.Sprite.SIZE, y + GameConstants.Sprite.SIZE);

    }

    public static void ConnectTwoDoorways(GameMap gameMapOne, RectF hitboxOne, GameMap gameMapTwo, RectF hitboxTwo) {

        Doorway doorwayOne = new Doorway(hitboxOne, gameMapOne);
        Doorway doorwayTwo = new Doorway(hitboxTwo, gameMapTwo);

        doorwayOne.connectDoorway(doorwayTwo);
        doorwayTwo.connectDoorway(doorwayOne);
    }


    public static ArrayList<Skeleton> GetSkeletonsRandomized(int amount, int[][] gameMapArray) {

        int width = (gameMapArray[0].length - 1) * GameConstants.Sprite.SIZE;
        int height = (gameMapArray.length - 1) * GameConstants.Sprite.SIZE;

        ArrayList<Skeleton> skeletonArrayList = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            float x = (float) (Math.random() * width);
            float y = (float) (Math.random() * height);
            skeletonArrayList.add(new Skeleton(new PointF(x, y)));
        }


        return skeletonArrayList;

    }
}
