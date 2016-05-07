package com.purecode.battleships.game;

import com.purecode.battleships.players.Player;

/**
 * Created by pnagarjuna on 07/05/16.
 */
public interface Game {
    void start();
    void end();
    GameState getGameState();
}
