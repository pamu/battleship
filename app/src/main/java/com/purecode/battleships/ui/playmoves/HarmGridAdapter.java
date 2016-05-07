package com.purecode.battleships.ui.playmoves;

import android.app.Dialog;
import android.graphics.Color;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Switch;

import com.purecode.battleships.R;
import com.purecode.battleships.game.BattleshipGame;
import com.purecode.battleships.ui.SquareBox;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pnagarjuna on 07/05/16.
 */
public class HarmGridAdapter extends BaseAdapter {
    private List<SquareBox> squareBoxes = new ArrayList<>();

    public HarmGridAdapter() {
        drawGameState();
    }

    public void drawGameState() {
        clear();

        int[][] grid;

        grid = BattleshipGame.getInstance().getMyPlayer().getShipsGrid();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                squareBoxes.add(new SquareBox(Pair.create(i, j), grid[i][j]));
            }
        }
        notifyDataSetChanged();
    }

    public void clear() {
        squareBoxes.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return squareBoxes.size();
    }

    @Override
    public Object getItem(int position) {
        return squareBoxes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.color_square, parent, false);
        }

        View square = convertView.findViewById(R.id.color_square);

        SquareBox squareBox = squareBoxes.get(position);
        int num = squareBox.getBoxNum();

        if (num == 0) {
            square.setBackgroundColor(Color.GRAY);
        } else if (num == -1) {
            square.setBackgroundColor(Color.RED);
        } else if (num == 5) {
            square.setBackgroundColor(Color.GREEN);
        } else {
            square.setBackgroundColor(Color.GRAY);
        }

        return convertView;
    }
}
