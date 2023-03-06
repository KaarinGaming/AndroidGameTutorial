package com.tutorial.androidgametutorial.inputs;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.MotionEvent;

import com.tutorial.androidgametutorial.GamePanel;

public class TouchEvents {
    private GamePanel gamePanel;
    private float xCenter = 540, yCenter = 1300, radius = 100;
    private Paint circlePaint, yellowPaint;
    private float xTouch, yTouch;
    private boolean touchDown;

    public TouchEvents(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        circlePaint = new Paint();
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeWidth(5);
        circlePaint.setColor(Color.RED);
        yellowPaint = new Paint();
        yellowPaint.setColor(Color.YELLOW);
    }

    public void draw(Canvas c) {
        c.drawCircle(xCenter, yCenter, radius, circlePaint);

        if (touchDown) {
            c.drawLine(xCenter, yCenter, xTouch, yTouch, yellowPaint);
            c.drawLine(xCenter, yCenter, xTouch, yCenter, yellowPaint);
            c.drawLine(xTouch, yTouch, xTouch, yCenter, yellowPaint);
        }


    }

    public boolean touchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN -> {

                float x = event.getX();
                float y = event.getY();

                float a = Math.abs(x - xCenter);
                float b = Math.abs(y - yCenter);
                float c = (float) Math.hypot(a, b);

                if (c <= radius) {
                    touchDown = true;
                    xTouch = x;
                    yTouch = y;
                }
            }

            case MotionEvent.ACTION_MOVE -> {
                if (touchDown) {
                    xTouch = event.getX();
                    yTouch = event.getY();

                    float xDiff = xTouch - xCenter;
                    float yDiff = yTouch - yCenter;

                    gamePanel.setPlayerMoveTrue(new PointF(xDiff, yDiff));

                }

            }
            case MotionEvent.ACTION_UP -> {
                touchDown = false;
                gamePanel.setPlayerMoveFalse();

            }
        }

        return true;
    }
}
