package com.purecode.battleships.players;

import android.util.Log;
import android.util.Pair;

import com.purecode.battleships.ships.AircraftCarrier;
import com.purecode.battleships.ships.GameShip;

import java.util.Random;

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
                    for (int i = pos.second; i < b; i++) {
                        shipsGrid[a][i] = gameShip.getShipId();
                        occupiedBoxesCount += gameShip.getSize();
                    }
                } else {
                    int a = pos.first + gameShip.getSize();
                    int b = pos.second;
                    for (int i = pos.first; i < a; i++) {
                        shipsGrid[i][b] = gameShip.getShipId();
                        occupiedBoxesCount += gameShip.getSize();
                    }
                }
            }
        } else {
            throw new UnsupportedOperationException("Other ships are not supported right now");
        }
    }

    public void positionShipRandomly(GameShip gameShip) {
        if (gameShip instanceof AircraftCarrier) {
            positionShip(gameShip, Pair.create(0, 0), true);
//            Random random = new Random();
//            boolean orientation = random.nextBoolean();
//            if (orientation) {
//                int guessedRow = random.nextInt(shipsGrid.length);
//                int guessedCol = random.nextInt(shipsGrid[0].length - gameShip.getSize());
//                positionShip(gameShip, Pair.create(guessedRow, guessedCol), orientation);
//                occupiedBoxesCount += gameShip.getSize();
//            } else {
//                int guessedCol = random.nextInt(shipsGrid[0].length);
//                int guessedRow = random.nextInt(shipsGrid.length - gameShip.getSize());
//                positionShip(gameShip, Pair.create(guessedRow, guessedCol), orientation);
//                occupiedBoxesCount += gameShip.getSize();
//            }
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
            for (int i = pos.second; i < b; i++) {
                if (shipsGrid[a][i] > 0) return false;
            }
            return true;
        } else {
            int a = pos.first + gameShip.getSize();
            int b = pos.second;
            if (a >= shipsGrid.length) return false;
            if (b >= shipsGrid[0].length) return false;
            for (int i = pos.first; i < a; i++) {
                if (shipsGrid[i][b] > 0) return false;
            }
            return true;
        }
    }

    public void fire(Pair<Integer, Integer> pos, BattleshipPlayer battleshipPlayer) {
        if (pos.first < movesGrid.length && pos.second < movesGrid[0].length) {
            if (battleshipPlayer != null) {
                movesGrid[pos.first][pos.second] = -1;
                moviesCount++;
                battleshipPlayer.applyFire(pos);
            }
        }
    }

    public void randomFire(BattleshipPlayer battleshipPlayer) {
        Random random = new Random();
        Pair<Integer, Integer> pos = Pair.create(random.nextInt(movesGrid.length), random.nextInt(movesGrid[0].length));
        if (pos.first < movesGrid.length && pos.second < movesGrid[0].length) {
            Log.e(BattleshipPlayer.class.getSimpleName(), "random fire " + pos);
            if (battleshipPlayer != null) {
                movesGrid[pos.first][pos.second] = -1;
                moviesCount++;
                battleshipPlayer.applyFire(pos);
            }
        }
    }



    public void applyFire(Pair<Integer, Integer> pos) {
        if (pos.first < shipsGrid.length && pos.second < shipsGrid[0].length) {
            if (shipsGrid[pos.first][pos.second] > 0) {
                successfulFireCount++;
            }
            Log.e(BattleshipPlayer.class.getSimpleName(), "apply fire " + pos);
            shipsGrid[pos.first][pos.second] = -1;
        }
    }

    public boolean isFired(Pair<Integer, Integer> pos) {
        if (pos.first < shipsGrid.length && pos.second < shipsGrid[0].length) {
            return shipsGrid[pos.first][pos.second] == -1;
        } else return false;
    }

    public int getOccupiedBoxesCount() {
        return occupiedBoxesCount;
    }

    public void setOccupiedBoxesCount(int occupiedBoxesCount) {
        this.occupiedBoxesCount = occupiedBoxesCount;
    }

    public int getSuccessfulFireCount() {
        return successfulFireCount;
    }

    public void setSuccessfulFireCount(int successfulFireCount) {
        this.successfulFireCount = successfulFireCount;
    }

    public int getMoviesCount() {
        return moviesCount;
    }

    public void setMoviesCount(int moviesCount) {
        this.moviesCount = moviesCount;
    }

    public int[][] getShipsGrid() {
        return shipsGrid;
    }

    public int[][] getMovesGrid() {
        return movesGrid;
    }
}
