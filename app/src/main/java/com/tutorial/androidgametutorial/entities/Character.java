package com.tutorial.androidgametutorial.entities;

import static com.tutorial.androidgametutorial.helpers.GameConstants.Sprite.HITBOX_SIZE;
import static com.tutorial.androidgametutorial.helpers.GameConstants.Sprite.X_DRAW_OFFSET;
import static com.tutorial.androidgametutorial.helpers.GameConstants.Sprite.Y_DRAW_OFFSET;

import android.graphics.PointF;
import android.graphics.RectF;

import com.tutorial.androidgametutorial.helpers.GameConstants;

public abstract class Character extends Entity {
    protected int aniTick, aniIndex;
    protected int faceDir = GameConstants.Face_Dir.DOWN;
    protected final GameCharacters gameCharType;
    protected boolean attacking, attackChecked;
    private RectF attackBox = null;

    public Character(PointF pos, GameCharacters gameCharType) {
        super(pos, HITBOX_SIZE, HITBOX_SIZE);
        this.gameCharType = gameCharType;

        updateWepHitbox();
    }

    protected void updateAnimation() {
        aniTick++;
        if (aniTick >= GameConstants.Animation.SPEED) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GameConstants.Animation.AMOUNT)
                aniIndex = 0;
        }
    }

    public void resetAnimation() {
        aniTick = 0;
        aniIndex = 0;
    }

    public int getAniIndex() {
        if (attacking) return 4;
        return aniIndex;
    }

    public int getFaceDir() {
        return faceDir;
    }

    public void setFaceDir(int faceDir) {
        this.faceDir = faceDir;
    }

    public GameCharacters getGameCharType() {
        return gameCharType;
    }

    public void updateWepHitbox() {
        PointF pos = getWepPos();
        float w = getWepWidth();
        float h = getWepHeight();

        attackBox = new RectF(pos.x, pos.y, pos.x + w, pos.y + h);
    }

    public float getWepWidth() {
        //Must keep in mind, there is a rotation active
        return switch (getFaceDir()) {

            case GameConstants.Face_Dir.LEFT, GameConstants.Face_Dir.RIGHT ->
                    Weapons.BIG_SWORD.getHeight();

            case GameConstants.Face_Dir.UP, GameConstants.Face_Dir.DOWN ->
                    Weapons.BIG_SWORD.getWidth();

            default -> throw new IllegalStateException("Unexpected value: " + getFaceDir());
        };
    }

    public float getWepHeight() {
        //Must keep in mind, there is a rotation active
        return switch (getFaceDir()) {

            case GameConstants.Face_Dir.UP, GameConstants.Face_Dir.DOWN ->
                    Weapons.BIG_SWORD.getHeight();

            case GameConstants.Face_Dir.LEFT, GameConstants.Face_Dir.RIGHT ->
                    Weapons.BIG_SWORD.getWidth();

            default -> throw new IllegalStateException("Unexpected value: " + getFaceDir());
        };
    }

    public PointF getWepPos() {
        //Must keep in mind, there is a rotation active
        return switch (getFaceDir()) {

            case GameConstants.Face_Dir.UP ->
                    new PointF(getHitbox().left - 0.5f * GameConstants.Sprite.SCALE_MULTIPLIER, getHitbox().top - Weapons.BIG_SWORD.getHeight() - Y_DRAW_OFFSET);

            case GameConstants.Face_Dir.DOWN ->
                    new PointF(getHitbox().left + 0.75f * GameConstants.Sprite.SCALE_MULTIPLIER, getHitbox().bottom);

            case GameConstants.Face_Dir.LEFT ->
                    new PointF(getHitbox().left - Weapons.BIG_SWORD.getHeight() - X_DRAW_OFFSET, getHitbox().bottom - Weapons.BIG_SWORD.getWidth() - 0.75f * GameConstants.Sprite.SCALE_MULTIPLIER);

            case GameConstants.Face_Dir.RIGHT ->
                    new PointF(getHitbox().right + X_DRAW_OFFSET, getHitbox().bottom - Weapons.BIG_SWORD.getWidth() - 0.75f * GameConstants.Sprite.SCALE_MULTIPLIER);

            default -> throw new IllegalStateException("Unexpected value: " + getFaceDir());
        };

    }

    public float wepRotAdjustTop() {
        return switch (getFaceDir()) {
            case GameConstants.Face_Dir.LEFT, GameConstants.Face_Dir.UP ->
                    -Weapons.BIG_SWORD.getHeight();
            default -> 0;
        };
    }

    public float wepRotAdjustLeft() {
        return switch (getFaceDir()) {
            case GameConstants.Face_Dir.UP, GameConstants.Face_Dir.RIGHT ->
                    -Weapons.BIG_SWORD.getWidth();
            default -> 0;
        };
    }

    public float getWepRot() {
        return switch (getFaceDir()) {
            case GameConstants.Face_Dir.LEFT -> 90;
            case GameConstants.Face_Dir.UP -> 180;
            case GameConstants.Face_Dir.RIGHT -> 270;
            default -> 0;
        };

    }

    public RectF getAttackBox() {
        return attackBox;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
        if (!attacking) attackChecked = false;
    }

    public boolean isAttacking() {
        return attacking;
    }

    public boolean isAttackChecked() {
        return attackChecked;
    }

    public void setAttackChecked(boolean attackChecked) {
        this.attackChecked = attackChecked;
    }

}
