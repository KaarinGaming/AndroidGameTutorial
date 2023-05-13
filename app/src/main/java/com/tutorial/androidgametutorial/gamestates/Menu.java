package com.tutorial.androidgametutorial.gamestates;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.tutorial.androidgametutorial.helpers.interfaces.GameStateInterface;
import com.tutorial.androidgametutorial.main.Game;

public class Menu extends BaseState implements GameStateInterface {

    private Paint paint;

    public Menu(Game game) {
        super(game);
        paint = new Paint();
        paint.setTextSize(60);
        paint.setColor(Color.WHITE);
    }

    @Override
    public void update(double delta) {

    }

    @Override
    public void render(Canvas c) {
        c.drawText("MENU!", 800, 200, paint);
    }

    @Override
    public void touchEvents(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN)
            game.setCurrentGameState(Game.GameState.PLAYING);
    }
}
