package com.purecode.battleships.ships;

/**
 * Created by pnagarjuna on 07/05/16.
 */
public abstract class GameShip implements Ship {
    int size;
    int shipId;
    public abstract int getSize();
    public abstract int getShipId();
}
