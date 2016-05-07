package com.purecode.battleships.ui.placeships;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.purecode.battleships.R;
import com.purecode.battleships.game.BattleshipGame;
import com.purecode.battleships.ui.CustomGridAdapter;


public class MainActivity extends AppCompatActivity {

    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = (GridView) findViewById(R.id.gridview);
        gridView.setNumColumns(10);
        gridView.setAdapter(new CustomGridAdapter());
        Button next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = BattleshipGame.getInstance().getMyPlayer().getOccupiedBoxesCount();
                if (count > 0) {
                } else {
                    Toast.makeText(getApplicationContext(), "Cannot go ahead without placing ships", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
