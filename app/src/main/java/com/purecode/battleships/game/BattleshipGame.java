package com.purecode.battleships.game;

import com.purecode.battleships.players.MyPlayer;
import com.purecode.battleships.players.OpponentPlayer;
import com.purecode.battleships.ships.AircraftCarrier;

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
        opponentPlayer.positionShipRandomly(new AircraftCarrier());
        gameState = GameState.STARTED;
    }

    @Override
    public void end() {
        gameState = GameState.STOPPED;
    }

    @Override
    public GameState getGameState() {
        if (myPlayer.getOccupiedBoxesCount() == myPlayer.getSuccessfulFireCount()) {
            gameState = GameState.GAME_OVER;
        }
        if (opponentPlayer.getOccupiedBoxesCount() == opponentPlayer.getSuccessfulFireCount()) {
            gameState = GameState.GAME_OVER;
        }
        return gameState;
    }
}
