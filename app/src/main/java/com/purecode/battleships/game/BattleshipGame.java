package com.purecode.battleships.game;

import com.purecode.battleships.players.MyPlayer;
import com.purecode.battleships.players.OpponentPlayer;
import com.purecode.battleships.players.Player;
import com.purecode.battleships.ships.AircraftCarrier;

/**
 * Created by pnagarjuna on 07/05/16.
 */
public class BattleshipGame implements Game {
    private MyPlayer myPlayer;
    private OpponentPlayer opponentPlayer;
    private boolean myChance;
    private GameState gameState;

    private BattleshipGame() {
        myPlayer = new MyPlayer();
        opponentPlayer = new OpponentPlayer();
        myChance = true;
        gameState = GameState.STARTED;
        opponentPlayer.positionShipRandomly(new AircraftCarrier());
    }

    @Override
    public void start() {
        gameState = GameState.STARTED;
    }

    @Override
    public void end() {
        gameState = GameState.STOPPED;
    }

    @Override
    public GameState getGameState() {
        return gameState;
    }

    public MyPlayer getMyPlayer() {
        return myPlayer;
    }

    public void setMyPlayer(MyPlayer myPlayer) {
        this.myPlayer = myPlayer;
    }

    public OpponentPlayer getOpponentPlayer() {
        return opponentPlayer;
    }

    public void setOpponentPlayer(OpponentPlayer opponentPlayer) {
        this.opponentPlayer = opponentPlayer;
    }

    public boolean isMyChance() {
        return myChance;
    }

    public void setMyChance(boolean myChance) {
        this.myChance = myChance;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    private static BattleshipGame battleshipGame;

    public static BattleshipGame getInstance() {
        if (battleshipGame == null) {
            battleshipGame = new BattleshipGame();
        }
        return battleshipGame;
    }

    public Player getWinner() {
        if (opponentPlayer.getOccupiedBoxesCount() == opponentPlayer.getSuccessfulFireCount()) {
            return myPlayer;
        }

        if (myPlayer.getOccupiedBoxesCount() == myPlayer.getSuccessfulFireCount()) {
            return opponentPlayer;
        }

        return null;
    }
}
