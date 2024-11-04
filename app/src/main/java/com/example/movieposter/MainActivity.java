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

        //prepare posters

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

        Poster amazingSpiderman1 = new Poster();
        amazingSpiderman1.image = R.drawable.amazing_spiderman1;
        amazingSpiderman1.name = "Amazing Spider-Man";
        amazingSpiderman1.createdBy = "Marc Webb";
        amazingSpiderman1.rating = 5f;
        amazingSpiderman1.story = "A new take on the Spider-Man story";
        posterList.add(amazingSpiderman1);

        Poster amazingSpiderman2 = new Poster();
        amazingSpiderman2.image = R.drawable.amazing_spiderman2;
        amazingSpiderman2.name = "Amazing Spider-Man 2";
        amazingSpiderman2.createdBy = "Marc Webb";
        amazingSpiderman2.rating = 5f;
        amazingSpiderman2.story = "Spider-Man's toughest battle yet";
        posterList.add(amazingSpiderman2);

        Poster homecoming = new Poster();
        homecoming.image = R.drawable.spiderman_homecoming;
        homecoming.name = "Spider-Man: Homecoming";
        homecoming.createdBy = "Jon Watts";
        homecoming.rating = 5f;
        homecoming.story = "Peter learns to balance his life with Spider-Man's";
        posterList.add(homecoming);

        Poster farFromHome = new Poster();
        farFromHome.image = R.drawable.spiderman_far_from_home;
        farFromHome.name = "Spider-Man: Far From Home";
        farFromHome.createdBy = "Jon Watts";
        farFromHome.rating = 5f;
        farFromHome.story = "Spider-Man travels to europe";
        posterList.add(farFromHome);

        Poster noWayHome = new Poster();
        noWayHome.image = R.drawable.spiderman_no_way_home;
        noWayHome.name = "Spider-Man: No Way Home";
        noWayHome.createdBy = "Jon Watts";
        noWayHome.rating = 5f;
        noWayHome.story = "Spider-Man fights familiar enemies";
        posterList.add(noWayHome);

        Poster intoSpiderverse = new Poster();
        intoSpiderverse.image = R.drawable.spiderman_into_the_spiderverse;
        intoSpiderverse.name = "Spider-Man: Into the Spider-verse";
        intoSpiderverse.createdBy = "Sony";
        intoSpiderverse.rating = 5f;
        intoSpiderverse.story = "Miles learns what it means to be Spider-Man";
        posterList.add(intoSpiderverse);

        Poster accrossSpiderverse = new Poster();
        accrossSpiderverse.image = R.drawable.spiderman_accross_the_spiderverse;
        accrossSpiderverse.name = "Spider-Man: Across the Spider-verse";
        accrossSpiderverse.createdBy = "Sony";
        accrossSpiderverse.rating = 5f;
        accrossSpiderverse.story = "Miles learns about the spider society";
        posterList.add(accrossSpiderverse);


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

    /**
     * Handles visibility of the 'add to watchlist' button based on poster selection status
     * @param isSelected boolean indicating if the poster is selected(true) or deselected(false).
     */
    @Override
    public void onPosterAction(boolean isSelected) {
        if(isSelected){
            buttonAddToWatchlist.setVisibility(View.VISIBLE);
        } else {
            buttonAddToWatchlist.setVisibility(View.GONE);
        }
    }
}