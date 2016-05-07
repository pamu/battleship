package com.purecode.battleships.ui;

import android.util.Pair;

/**
 * Created by pnagarjuna on 07/05/16.
 */
public class SquareBox {
    private Pair<Integer, Integer> position;
    private int boxNum;

    public SquareBox(Pair<Integer, Integer> position, int boxNum) {
        this.position = position;
        this.boxNum = boxNum;
    }

    public Pair<Integer, Integer> getPosition() {
        return position;
    }

    public void setPosition(Pair<Integer, Integer> position) {
        this.position = position;
    }

    public int getBoxNum() {
        return boxNum;
    }

    public void setBoxNum(int boxNum) {
        this.boxNum = boxNum;
    }
}
