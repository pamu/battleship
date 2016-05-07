package com.purecode.battleships.players;

import android.util.Pair;

import com.purecode.battleships.ships.AircraftCarrier;
import com.purecode.battleships.ships.GameShip;

/**
 * Created by pnagarjuna on 07/05/16.
 */
public class MyPlayer extends BattleshipPlayer {

    public MyPlayer() {
        movesGrid = new int[10][10];
        shipsGrid = new int[10][10];
    }

}
