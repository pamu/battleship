package com.purecode.battleships;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.widget.GridView;
import android.widget.ListAdapter;

import com.purecode.battleships.ui.CustomGridAdapter;
import com.purecode.battleships.ui.SquareBox;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = (GridView) findViewById(R.id.gridview);
        gridView.setNumColumns(10);
        List<SquareBox> squareBoxes = new ArrayList<>();
        for(int i = 0; i < 100 ; i ++) {
            squareBoxes.add(new SquareBox(Pair.create(i, i), i));
        }
        gridView.setAdapter(new CustomGridAdapter(squareBoxes));
    }
}
