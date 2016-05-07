package com.purecode.battleships.game;

import com.purecode.battleships.players.MyPlayer;
import com.purecode.battleships.players.OpponentPlayer;

/**
 * Created by pnagarjuna on 07/05/16.
 */
public class BattleshipGame implements Game {
    private MyPlayer myPlayer;
    private OpponentPlayer opponentPlayer;
    private boolean turn;
    private GameState gameState;

    public BattleshipGame() {
        myPlayer = new MyPlayer();
        opponentPlayer = new OpponentPlayer();
        turn = true;
        gameState = GameState.STARTED;
    }

    @Override
    public void start() {

    }

    @Override
    public void end() {

    }

    @Override
    public GameState getGameState() {
        return gameState;
    }
}
