package com.purecode.battleships.ships;

/**
 * Created by pnagarjuna on 07/05/16.
 */
public class AircraftCarrier extends GameShip {
    public AircraftCarrier() {
        size = 5;
        shipId = 5;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getShipId() {
        return shipId;
    }
}
