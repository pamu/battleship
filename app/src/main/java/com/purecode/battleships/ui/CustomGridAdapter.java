package com.purecode.battleships.ui;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.purecode.battleships.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pnagarjuna on 07/05/16.
 */
public class CustomGridAdapter extends BaseAdapter {

    private List<SquareBox> squareBoxes = new ArrayList<>();

    public CustomGridAdapter(List<SquareBox> squareBoxes) {
        this.squareBoxes.addAll(squareBoxes);
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView  == null) {
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
