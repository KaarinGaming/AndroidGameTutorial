package com.tutorial.androidgametutorial.entities.enemies;

import static com.tutorial.androidgametutorial.main.MainActivity.GAME_HEIGHT;
import static com.tutorial.androidgametutorial.main.MainActivity.GAME_WIDTH;

import android.graphics.PointF;

import com.tutorial.androidgametutorial.entities.Character;
import com.tutorial.androidgametutorial.entities.GameCharacters;
import com.tutorial.androidgametutorial.environments.GameMap;
import com.tutorial.androidgametutorial.helpers.GameConstants;

import java.util.Random;

public class Skeleton extends Character {
    private long lastDirChange = System.currentTimeMillis();
    private Random rand = new Random();
    private boolean moving = true, preparingAttack;
    private long timerBeforeAttack, timerAttackDuration;
    private long timeToAttack = 500, timeForAttackDuration = 250;


    public Skeleton(PointF pos) {
        super(pos, GameCharacters.SKELETON);
    }

    public void update(double delta, GameMap gameMap) {
        if (moving) {
            updateMove(delta, gameMap);
            updateAnimation();
        }
        if (preparingAttack) {
            checkTimeToAttackTimer();
        }
        if (attacking) {
            updateAttackTimer();
        }
    }

    public void prepareAttack() {
        timerBeforeAttack = System.currentTimeMillis();
        preparingAttack = true;
        moving = false;
    }

    private void updateAttackTimer() {
        if (timerAttackDuration + timeForAttackDuration < System.currentTimeMillis()) {
            setAttacking(false);
            resetAnimation();
            moving = true;
        }
    }

    private void checkTimeToAttackTimer() {
        if (timerBeforeAttack + timeToAttack < System.currentTimeMillis()) {
            setAttacking(true);
            preparingAttack = false;
            timerAttackDuration = System.currentTimeMillis();
        }
    }

    private void updateMove(double delta, GameMap gameMap) {
        if (System.currentTimeMillis() - lastDirChange >= 3000) {
            faceDir = rand.nextInt(4);
            lastDirChange = System.currentTimeMillis();
        }

        switch (faceDir) {
            case GameConstants.Face_Dir.DOWN:
                hitbox.top += delta * 300;
                hitbox.bottom += delta * 300;
                if (hitbox.bottom >= gameMap.getMapHeight()) faceDir = GameConstants.Face_Dir.UP;
                break;

            case GameConstants.Face_Dir.UP:
                hitbox.top -= delta * 300;
                hitbox.bottom -= delta * 300;
                if (hitbox.top <= 0) faceDir = GameConstants.Face_Dir.DOWN;
                break;

            case GameConstants.Face_Dir.RIGHT:
                hitbox.left += delta * 300;
                hitbox.right += delta * 300;
                if (hitbox.right >= gameMap.getMapWidth()) faceDir = GameConstants.Face_Dir.LEFT;
                break;

            case GameConstants.Face_Dir.LEFT:
                hitbox.left -= delta * 300;
                hitbox.right -= delta * 300;
                if (hitbox.left <= 0) faceDir = GameConstants.Face_Dir.RIGHT;
                break;
        }
    }

    public boolean isPreparingAttack() {
        return preparingAttack;
    }
}
