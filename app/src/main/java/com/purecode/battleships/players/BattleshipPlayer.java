package com.purecode.battleships.players;

import android.util.Pair;

import com.purecode.battleships.ships.AircraftCarrier;
import com.purecode.battleships.ships.GameShip;

/**
 * Created by pnagarjuna on 07/05/16.
 */
public abstract class BattleshipPlayer implements Player {
    int[][] shipsGrid;
    int[][] movesGrid;
    int successfulFireCount;
    int occupiedBoxesCount;
    int moviesCount;

    public void positionShip(GameShip gameShip, Pair<Integer, Integer> pos, boolean orientation) throws UnsupportedOperationException {
        if (gameShip instanceof AircraftCarrier) {
            if (isValid(gameShip, pos, orientation)) {
                if (orientation) {
                    int a = pos.first;
                    int b = pos.second + gameShip.getSize();
                    for( int i = pos.second; i < b; i ++) {
                        shipsGrid[a][i] = gameShip.getShipId();
                        occupiedBoxesCount ++;
                    }
                } else {
                    int a = pos.first + gameShip.getSize();
                    int b = pos.second;
                    for(int i = pos.first; i < a; i ++) {
                        shipsGrid[i][b]  = gameShip.getShipId();
                        occupiedBoxesCount ++;
                    }
                }
            }
        } else {
            throw new UnsupportedOperationException("Other ships are not supported right now");
        }
    }

    public boolean isValid(GameShip gameShip, Pair<Integer, Integer> pos, boolean orientation) {
        if (orientation) {
            int a = pos.first;
            int b = pos.second + gameShip.getSize();
            if (a >= shipsGrid.length) return false;
            if (b >= shipsGrid[0].length) return false;
            for( int i = pos.second; i < b; i ++) {
                if (shipsGrid[a][i] > 0) return false;
            }
            return true;
        } else {
            int a = pos.first + gameShip.getSize();
            int b = pos.second;
            if (a >= shipsGrid.length) return false;
            if (b >= shipsGrid[0].length) return false;
            for(int i = pos.first; i < a; i ++) {
                if (shipsGrid[i][b] > 0) return false;
            }
            return true;
        }
    }

    public void fire(Pair<Integer, Integer> pos, BattleshipPlayer battleshipPlayer) {
        if (pos.first < movesGrid.length && pos.second < movesGrid[0].length) {
            if (battleshipPlayer != null) {
                if (battleshipPlayer instanceof OpponentPlayer) {
                    movesGrid[pos.first][pos.second] = -1;
                    moviesCount ++;
                    battleshipPlayer.applyFire(pos);
                }
            }
        }
    }

    public void applyFire(Pair<Integer, Integer> pos) {
        if(pos.first < shipsGrid.length && pos.second < shipsGrid[0].length) {
            if (shipsGrid[pos.first][pos.second] > 0) {
                successfulFireCount ++;
            }
            shipsGrid[pos.first][pos.second] = -1;
        }
    }

    public boolean isFired(Pair<Integer, Integer> pos) {
        if(pos.first < shipsGrid.length && pos.second < shipsGrid[0].length) {
            return shipsGrid[pos.first][pos.second] == -1;
        } else return false;
    }

}
