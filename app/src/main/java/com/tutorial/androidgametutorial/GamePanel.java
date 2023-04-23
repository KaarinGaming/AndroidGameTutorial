package com.tutorial.androidgametutorial;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.tutorial.androidgametutorial.entities.Character;
import com.tutorial.androidgametutorial.entities.Player;
import com.tutorial.androidgametutorial.entities.enemies.Skeleton;
import com.tutorial.androidgametutorial.environments.MapManager;
import com.tutorial.androidgametutorial.helpers.GameConstants;
import com.tutorial.androidgametutorial.inputs.TouchEvents;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private Paint redPaint = new Paint();
    private SurfaceHolder holder;
    private float cameraX, cameraY;
    private boolean movePlayer;
    private PointF lastTouchDiff;
    private GameLoop gameLoop;
    private TouchEvents touchEvents;
    private MapManager mapManager;

    private Player player;

    private ArrayList<Skeleton> skeletons;


    public GamePanel(Context context) {
        super(context);
        holder = getHolder();
        holder.addCallback(this);
        redPaint.setColor(Color.RED);
        touchEvents = new TouchEvents(this);
        gameLoop = new GameLoop(this);
        mapManager = new MapManager();
        player = new Player();
        skeletons = new ArrayList<>();

        for (int i = 0; i < 50; i++)
            skeletons.add(new Skeleton(new PointF(100, 100)));

    }

    public void render() {
        Canvas c = holder.lockCanvas();
        c.drawColor(Color.BLACK);
        mapManager.draw(c);
        touchEvents.draw(c);

        drawPlayer(c);
        for (Skeleton skeleton : skeletons)
            drawCharacter(c, skeleton);

        holder.unlockCanvasAndPost(c);
    }

    private void drawPlayer(Canvas c) {
        c.drawBitmap(
                player.getGameCharType().getSprite(player.getAniIndex(), player.getFaceDir()),
                player.getHitbox().left,
                player.getHitbox().top,
                null);
    }

    public void drawCharacter(Canvas canvas, Character c) {
        canvas.drawBitmap(
                c.getGameCharType().getSprite(c.getAniIndex(), c.getFaceDir()),
                c.getHitbox().left + cameraX,
                c.getHitbox().top + cameraY,
                null);
    }

    public void update(double delta) {
        updatePlayerMove(delta);
        player.update(delta, movePlayer);
        for (Skeleton skeleton : skeletons)
            skeleton.update(delta);
        mapManager.setCameraValues(cameraX, cameraY);

    }

    private void updatePlayerMove(double delta) {
        if (!movePlayer)
            return;

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

        if (lastTouchDiff.x < 0)
            xSpeed *= -1;
        if (lastTouchDiff.y < 0)
            ySpeed *= -1;

        int pWidth = GameConstants.Sprite.SIZE;
        int pHeight = GameConstants.Sprite.SIZE;

        if (xSpeed <= 0)
            pWidth = 0;
        if (ySpeed <= 0)
            pHeight = 0;


        float deltaX = xSpeed * baseSpeed * -1;
        float deltaY = ySpeed * baseSpeed * -1;

        if (mapManager.canMoveHere(player.getHitbox().left + cameraX * -1 + deltaX * -1 + pWidth, player.getHitbox().top + cameraY * -1 + deltaY * -1 + pHeight)) {
            cameraX += deltaX;
            cameraY += deltaY;
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return touchEvents.touchEvent(event);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        gameLoop.startGameLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

    public void setPlayerMoveTrue(PointF lastTouchDiff) {
        movePlayer = true;
        this.lastTouchDiff = lastTouchDiff;
    }

    public void setPlayerMoveFalse() {
        movePlayer = false;
        player.resetAnimation();
    }


}