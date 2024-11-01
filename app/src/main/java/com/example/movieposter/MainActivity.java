package com.example.movieposter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PosterListener {

    private Button buttonAddToWatchlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView postersRecyclerView = findViewById(R.id.postersRecyclerView);
        buttonAddToWatchlist = findViewById(R.id.buttonAddToWatchlist);

        //prepare data

        List<Poster> posterList = new ArrayList<>();

        Poster spiderman1 = new Poster();
        spiderman1.image = R.drawable.spiderman1;
        spiderman1.name = "Spider-Man";
        spiderman1.createdBy = "Sam Raimi";
        spiderman1.rating = 4f;
        spiderman1.story = "Peter Parker gets bitten by a radioactive spider";
        posterList.add(spiderman1);

        Poster spiderman2 = new Poster();
        spiderman2.image = R.drawable.spiderman2;
        spiderman2.name = "Spider-Man 2";
        spiderman2.createdBy = "Sam Raimi";
        spiderman2.rating = 5f;
        spiderman2.story = "Spider-Man must battle with Doctor Octopus";
        posterList.add(spiderman2);

        Poster spiderman3 = new Poster();
        spiderman3.image = R.drawable.spiderman3;
        spiderman3.name = "Spider-Man 3";
        spiderman3.createdBy = "Sam Raimi";
        spiderman3.rating = 5f;
        spiderman3.story = "Spider-Man must fight off the alien Venom";
        posterList.add(spiderman3);


        final PosterAdapter posterAdapter = new PosterAdapter(posterList, this);
        postersRecyclerView.setAdapter(posterAdapter);

        buttonAddToWatchlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Poster> selectPosters = posterAdapter.getSelectedPosters();

                StringBuilder posterNames = new StringBuilder();
                for (int i=0;i< selectPosters.size();i++) {
                    if (i == 0) {
                        posterNames.append(selectPosters.get(i).name);
                    } else {
                        posterNames.append("\n").append(selectPosters.get(i).name);
                    }
                }
                Toast.makeText(MainActivity.this,posterNames.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onPosterAction(boolean isSelected) {
        if(isSelected){
            buttonAddToWatchlist.setVisibility(View.VISIBLE);
        } else {
            buttonAddToWatchlist.setVisibility(View.GONE);
        }
    }
}