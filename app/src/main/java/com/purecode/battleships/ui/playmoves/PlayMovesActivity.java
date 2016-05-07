package com.purecode.battleships.ui.playmoves;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.purecode.battleships.R;

/**
 * Created by pnagarjuna on 07/05/16.
 */
public class PlayMovesActivity extends AppCompatActivity {
    GridView movesGrid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_moves_acitvity_layout);
        movesGrid = (GridView) findViewById(R.id.movies_gridview);
        movesGrid.setNumColumns(10);
        movesGrid.setAdapter(new MovesGridAdapter());
    }
}
