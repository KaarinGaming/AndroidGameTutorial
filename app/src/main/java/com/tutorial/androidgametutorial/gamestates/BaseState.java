package com.tutorial.androidgametutorial.gamestates;

import com.tutorial.androidgametutorial.main.Game;

public abstract class BaseState {
    protected Game game;

    public BaseState(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
}
