package com.tutorial.androidgametutorial.gamestates;

import static com.tutorial.androidgametutorial.helpers.GameConstants.Sprite.X_DRAW_OFFSET;
import static com.tutorial.androidgametutorial.helpers.GameConstants.Sprite.Y_DRAW_OFFSET;
import static com.tutorial.androidgametutorial.main.MainActivity.GAME_HEIGHT;
import static com.tutorial.androidgametutorial.main.MainActivity.GAME_WIDTH;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.view.MotionEvent;

import com.tutorial.androidgametutorial.entities.BuildingManager;
import com.tutorial.androidgametutorial.entities.Character;
import com.tutorial.androidgametutorial.entities.Player;
import com.tutorial.androidgametutorial.entities.Weapons;
import com.tutorial.androidgametutorial.entities.enemies.Skeleton;
import com.tutorial.androidgametutorial.environments.MapManager;
import com.tutorial.androidgametutorial.helpers.GameConstants;
import com.tutorial.androidgametutorial.helpers.interfaces.GameStateInterface;
import com.tutorial.androidgametutorial.main.Game;
import com.tutorial.androidgametutorial.ui.ButtonImages;
import com.tutorial.androidgametutorial.ui.CustomButton;
import com.tutorial.androidgametutorial.ui.PlayingUI;

import java.util.ArrayList;

public class Playing extends BaseState implements GameStateInterface {
    private float cameraX, cameraY;
    private boolean movePlayer;
    private PointF lastTouchDiff;
    private MapManager mapManager;
    //    private BuildingManager buildingManager;
    private Player player;
    private ArrayList<Skeleton> skeletons;

    private PlayingUI playingUI;

    private final Paint redPaint;

    private RectF attackBox = null;

    private boolean attacking, attackChecked;

    public Playing(Game game) {
        super(game);

        mapManager = new MapManager();
        calcStartCameraValues();
//        buildingManager = new BuildingManager();
        player = new Player();
        skeletons = new ArrayList<>();

        playingUI = new PlayingUI(this);

        redPaint = new Paint();
        redPaint.setStrokeWidth(1);
        redPaint.setStyle(Paint.Style.STROKE);
        redPaint.setColor(Color.RED);

        for (int i = 0; i < 5; i++)
            spawnSkeleton();

        updateWepHitbox();

    }

    private void calcStartCameraValues() {
        cameraX = GAME_WIDTH / 2 - mapManager.getMaxWidthCurrentMap() / 2;
        cameraY = GAME_HEIGHT / 2 - mapManager.getMaxHeightCurrentMap() / 2;
    }

    public void spawnSkeleton() {
        skeletons.add(new Skeleton(new PointF(player.getHitbox().left - cameraX, player.getHitbox().top - cameraY)));

    }

    @Override
    public void update(double delta) {
        updatePlayerMove(delta);
        player.update(delta, movePlayer);
        updateWepHitbox();

        if (attacking) if (!attackChecked) checkAttack();


        for (Skeleton skeleton : skeletons)
            if (skeleton.isActive()) skeleton.update(delta);

        mapManager.setCameraValues(cameraX, cameraY);
//        buildingManager.setCameraValues(cameraX, cameraY);
    }

    private void checkAttack() {

        RectF attackBoxWithoutCamera = new RectF(attackBox);
        attackBoxWithoutCamera.left -= cameraX;
        attackBoxWithoutCamera.top -= cameraY;
        attackBoxWithoutCamera.right -= cameraX;
        attackBoxWithoutCamera.bottom -= cameraY;

        for (Skeleton s : skeletons)
            if (attackBoxWithoutCamera.intersects(s.getHitbox().left, s.getHitbox().top, s.getHitbox().right, s.getHitbox().bottom))
                s.setActive(false);

        attackChecked = true;

    }

    private void updateWepHitbox() {
        PointF pos = getWepPos();
        float w = getWepWidth();
        float h = getWepHeight();

        attackBox = new RectF(pos.x, pos.y, pos.x + w, pos.y + h);
    }

    private float getWepWidth() {
        //Must keep in mind, there is a rotation active
        return switch (player.getFaceDir()) {

            case GameConstants.Face_Dir.LEFT, GameConstants.Face_Dir.RIGHT ->
                    Weapons.BIG_SWORD.getHeight();

            case GameConstants.Face_Dir.UP, GameConstants.Face_Dir.DOWN ->
                    Weapons.BIG_SWORD.getWidth();

            default -> throw new IllegalStateException("Unexpected value: " + player.getFaceDir());
        };
    }

    private float getWepHeight() {
        //Must keep in mind, there is a rotation active
        return switch (player.getFaceDir()) {

            case GameConstants.Face_Dir.UP, GameConstants.Face_Dir.DOWN ->
                    Weapons.BIG_SWORD.getHeight();

            case GameConstants.Face_Dir.LEFT, GameConstants.Face_Dir.RIGHT ->
                    Weapons.BIG_SWORD.getWidth();

            default -> throw new IllegalStateException("Unexpected value: " + player.getFaceDir());
        };
    }

    private PointF getWepPos() {
        //Must keep in mind, there is a rotation active
        return switch (player.getFaceDir()) {

            case GameConstants.Face_Dir.UP ->
                    new PointF(player.getHitbox().left - 0.5f * GameConstants.Sprite.SCALE_MULTIPLIER, player.getHitbox().top - Weapons.BIG_SWORD.getHeight() - Y_DRAW_OFFSET);

            case GameConstants.Face_Dir.DOWN ->
                    new PointF(player.getHitbox().left + 0.75f * GameConstants.Sprite.SCALE_MULTIPLIER, player.getHitbox().bottom);

            case GameConstants.Face_Dir.LEFT ->
                    new PointF(player.getHitbox().left - Weapons.BIG_SWORD.getHeight() - X_DRAW_OFFSET, player.getHitbox().bottom - Weapons.BIG_SWORD.getWidth() - 0.75f * GameConstants.Sprite.SCALE_MULTIPLIER);

            case GameConstants.Face_Dir.RIGHT ->
                    new PointF(player.getHitbox().right + X_DRAW_OFFSET, player.getHitbox().bottom - Weapons.BIG_SWORD.getWidth() - 0.75f * GameConstants.Sprite.SCALE_MULTIPLIER);

            default -> throw new IllegalStateException("Unexpected value: " + player.getFaceDir());
        };

    }

    @Override
    public void render(Canvas c) {
        mapManager.draw(c);
//        buildingManager.draw(c);
        drawPlayer(c);

        for (Skeleton skeleton : skeletons)
            if (skeleton.isActive()) drawCharacter(c, skeleton);

        playingUI.draw(c);
    }


    private void drawPlayer(Canvas c) {

        c.drawBitmap(Weapons.SHADOW.getWeaponImg(), player.getHitbox().left, player.getHitbox().bottom - 5 * GameConstants.Sprite.SCALE_MULTIPLIER, null);

        c.drawBitmap(player.getGameCharType().getSprite(getAniIndex(), player.getFaceDir()), player.getHitbox().left - X_DRAW_OFFSET, player.getHitbox().top - GameConstants.Sprite.Y_DRAW_OFFSET, null);

        c.drawRect(player.getHitbox(), redPaint);

        if (attacking) drawWeapon(c);
    }

    private int getAniIndex() {
        if (attacking) return 4;
        return player.getAniIndex();
    }

    private void drawWeapon(Canvas c) {
        c.rotate(getWepRot(), attackBox.left, attackBox.top);

        c.drawBitmap(Weapons.BIG_SWORD.getWeaponImg(), attackBox.left + wepRotAdjustLeft(), attackBox.top + wepRotAdjustTop(), null);

        c.rotate(getWepRot() * -1, attackBox.left, attackBox.top);

        c.drawRect(attackBox, redPaint);
    }

    private float wepRotAdjustTop() {
        return switch (player.getFaceDir()) {
            case GameConstants.Face_Dir.LEFT, GameConstants.Face_Dir.UP ->
                    -Weapons.BIG_SWORD.getHeight();
            default -> 0;
        };
    }

    private float wepRotAdjustLeft() {
        return switch (player.getFaceDir()) {
            case GameConstants.Face_Dir.UP, GameConstants.Face_Dir.RIGHT ->
                    -Weapons.BIG_SWORD.getWidth();
            default -> 0;
        };
    }

    private float getWepRot() {
        return switch (player.getFaceDir()) {
            case GameConstants.Face_Dir.LEFT -> 90;
            case GameConstants.Face_Dir.UP -> 180;
            case GameConstants.Face_Dir.RIGHT -> 270;
            default -> 0;
        };

    }

    public void drawCharacter(Canvas canvas, Character c) {
        canvas.drawBitmap(Weapons.SHADOW.getWeaponImg(), c.getHitbox().left + cameraX, c.getHitbox().bottom - 5 * GameConstants.Sprite.SCALE_MULTIPLIER + cameraY, null);


        canvas.drawBitmap(c.getGameCharType().getSprite(c.getAniIndex(), c.getFaceDir()), c.getHitbox().left + cameraX - X_DRAW_OFFSET, c.getHitbox().top + cameraY - GameConstants.Sprite.Y_DRAW_OFFSET, null);

//        canvas.drawRect(c.getHitbox().left + cameraX, c.getHitbox().top + cameraY, c.getHitbox().right + cameraX, c.getHitbox().bottom + cameraY, redPaint);
    }

    private void updatePlayerMove(double delta) {
        if (!movePlayer) return;

        float baseSpeed = (float) (delta * 300);
        float ratio = Math.abs(lastTouchDiff.y) / Math.abs(lastTouchDiff.x);
        double angle = Math.atan(ratio);

        float xSpeed = (float) Math.cos(angle);
        float ySpeed = (float) Math.sin(angle);

        if (xSpeed > ySpeed) {
            if (lastTouchDiff.x > 0) player.setFaceDir(GameConstants.Face_Dir.RIGHT);
            else player.setFaceDir(GameConstants.Face_Dir.LEFT);
        } else {
            if (lastTouchDiff.y > 0) player.setFaceDir(GameConstants.Face_Dir.DOWN);
            else player.setFaceDir(GameConstants.Face_Dir.UP);
        }

        if (lastTouchDiff.x < 0) xSpeed *= -1;
        if (lastTouchDiff.y < 0) ySpeed *= -1;

        int pWidth = (int) player.getHitbox().width();
        int pHeight = (int) player.getHitbox().height();

        if (xSpeed <= 0) pWidth = 0;
        if (ySpeed <= 0) pHeight = 0;


        float deltaX = xSpeed * baseSpeed * -1;
        float deltaY = ySpeed * baseSpeed * -1;

        if (mapManager.canMoveHere(player.getHitbox().left + cameraX * -1 + deltaX * -1 + pWidth, player.getHitbox().top + cameraY * -1 + deltaY * -1 + pHeight)) {
            cameraX += deltaX;
            cameraY += deltaY;
        }
    }

    public void setGameStateToMenu() {
        game.setCurrentGameState(Game.GameState.MENU);
    }

    public void setPlayerMoveTrue(PointF lastTouchDiff) {
        movePlayer = true;
        this.lastTouchDiff = lastTouchDiff;
    }

    public void setPlayerMoveFalse() {
        movePlayer = false;
        player.resetAnimation();
    }

    @Override
    public void touchEvents(MotionEvent event) {
        playingUI.touchEvents(event);
    }


    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
        if (!attacking) attackChecked = false;
    }
}
