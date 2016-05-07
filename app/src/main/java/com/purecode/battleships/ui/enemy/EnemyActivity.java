package com.purecode.battleships.ui.enemy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.purecode.battleships.R;
import com.purecode.battleships.ui.CustomGridAdapter;

/**
 * Created by pnagarjuna on 07/05/16.
 */
public class EnemyActivity extends AppCompatActivity {
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enemy_layout);
        gridView = (GridView) findViewById(R.id.gridview);
        gridView.setNumColumns(10);
        gridView.setAdapter(new CustomGridAdapter(false));
    }
}
