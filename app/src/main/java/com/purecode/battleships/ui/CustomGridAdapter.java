package com.purecode.battleships.ui;

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
import com.purecode.battleships.ships.AircraftCarrier;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pnagarjuna on 07/05/16.
 */
public class CustomGridAdapter extends BaseAdapter {

    private List<SquareBox> squareBoxes = new ArrayList<>();

    public CustomGridAdapter() {
        drawGameState();
    }

    public void drawGameState() {
        clear();
        int[][] grid = BattleshipGame.getInstance().getMyPlayer().getShipsGrid();
        for(int i = 0; i < grid.length; i ++) {
            for(int j = 0; j < grid[0].length; j ++) {
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

        square.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(v.getContext());
                dialog.setContentView(R.layout.dialog_layout);
                dialog.setTitle("Choose Orientation");
                final Switch switchBox = (Switch) dialog.findViewById(R.id.orientation);
                switchBox.setChecked(true);
                Button ok = (Button) dialog.findViewById(R.id.ok);
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Pair pos = squareBoxes.get(position).getPosition();
                        BattleshipGame.getInstance().getMyPlayer().positionShip(new AircraftCarrier(), pos, switchBox.isChecked());
                        drawGameState();
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        return convertView;
    }
}
