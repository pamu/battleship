package com.purecode.battleships.ui.playmoves;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.GridView;

import com.purecode.battleships.R;
import com.purecode.battleships.ui.enemy.EnemyActivity;

/**
 * Created by pnagarjuna on 07/05/16.
 */
public class PlayMovesActivity extends AppCompatActivity {
    GridView movesGrid;
    GridView grid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_moves_acitvity_layout);

        grid = (GridView) findViewById(R.id.gridview);
        grid.setNumColumns(10);
        HarmGridAdapter harmGridAdapter = new HarmGridAdapter();
        grid.setAdapter(harmGridAdapter);

        movesGrid = (GridView) findViewById(R.id.movies_gridview);
        movesGrid.setNumColumns(10);
        movesGrid.setAdapter(new MovesGridAdapter(harmGridAdapter));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.see:
                startActivity(new Intent(getApplicationContext(), EnemyActivity.class));
                return true;
            default:
                return true;
        }
    }
}
