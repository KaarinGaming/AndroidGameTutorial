package com.tutorial.androidgametutorial.environments;


import com.tutorial.androidgametutorial.entities.Building;


import java.util.ArrayList;

public class GameMap {

    private int[][] spriteIds;
    private Tiles tilesType;
    private ArrayList<Building> buildingArrayList;
    private ArrayList<Doorway> doorwayArrayList;

    public GameMap(int[][] spriteIds, Tiles tilesType, ArrayList<Building> buildingArrayList) {
        this.spriteIds = spriteIds;
        this.tilesType = tilesType;
        this.buildingArrayList = buildingArrayList;
        this.doorwayArrayList = new ArrayList<>();
    }

    public void addDoorway(Doorway doorway) {
        this.doorwayArrayList.add(doorway);
    }

    public ArrayList<Doorway> getDoorwayArrayList() {
        return doorwayArrayList;
    }

    public ArrayList<Building> getBuildingArrayList() {
        return buildingArrayList;
    }

    public Tiles getFloorType() {
        return tilesType;
    }

    public int getSpriteID(int xIndex, int yIndex) {
        return spriteIds[yIndex][xIndex];
    }

    public int getArrayWidth() {
        return spriteIds[0].length;
    }

    public int getArrayHeight() {
        return spriteIds.length;
    }


}
