package com.purecode.battleships.ui.playmoves;

import android.content.Context;
import android.graphics.Color;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.purecode.battleships.R;
import com.purecode.battleships.game.BattleshipGame;
import com.purecode.battleships.game.GameState;
import com.purecode.battleships.players.MyPlayer;
import com.purecode.battleships.players.OpponentPlayer;
import com.purecode.battleships.players.Player;
import com.purecode.battleships.ships.AircraftCarrier;
import com.purecode.battleships.ships.Battleship;
import com.purecode.battleships.ui.SquareBox;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pnagarjuna on 07/05/16.
 */
public class MovesGridAdapter extends BaseAdapter {

    private List<SquareBox> squareBoxes = new ArrayList<>();

    public MovesGridAdapter() {
        drawGameState();
    }

    public void drawGameState() {
        clear();
        int[][] grid = BattleshipGame.getInstance().getMyPlayer().getShipsGrid();
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
    public View getView(final int position, View convertView, final ViewGroup parent) {
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
                if (BattleshipGame.getInstance().isMyChance()) {
                    BattleshipGame.getInstance().getMyPlayer().fire(
                            squareBoxes.get(position).getPosition(),
                            BattleshipGame.getInstance().getOpponentPlayer()
                    );
                    drawGameState();
                    showWinner(v.getContext());
                    BattleshipGame.getInstance().setMyChance(false);
                    BattleshipGame.getInstance().getMyPlayer().randomFire(
                            BattleshipGame.getInstance().getMyPlayer()
                    );
                    BattleshipGame.getInstance().setMyChance(true);
                    drawGameState();
                    showWinner(v.getContext());
                } else {
                    Toast.makeText(v.getContext(), "Not your turn", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return convertView;
    }

    public void showWinner(Context context) {
        if (BattleshipGame.getInstance().getGameState().equals(GameState.GAME_OVER)) {
            Player player = BattleshipGame.getInstance().getWinner();
            if (player == null) {
                String msg = "";
                if (player instanceof MyPlayer) {
                    msg = "You won";
                }
                if (player instanceof OpponentPlayer) {
                    msg = "Computer Won";
                }
                Toast.makeText(context,  msg, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
