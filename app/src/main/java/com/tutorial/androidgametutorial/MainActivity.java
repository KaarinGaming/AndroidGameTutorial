package com.tutorial.androidgametutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static Context gameContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameContext = this;
        setContentView(new GamePanel(this));
    }

    public static Context getGameContext() {
        return gameContext;
    }
}