package com.example.moviesapp.view;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.moviesapp.R;
import com.example.moviesapp.model.Movie;

import java.util.Objects;

public class MovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Movie Information");
        setContentView(R.layout.activity_movie_info);

        ImageView imgImageView = findViewById(R.id.imgMovieInfo);
        TextView txtTitle = findViewById(R.id.txtInfoTitle);
        TextView txtVoteAverage = findViewById(R.id.txtInfoVoteAverage);
        TextView txtGenres = findViewById(R.id.txtInfoGenres);
        TextView txtVoteCount = findViewById(R.id.txtInfoVoteCount);
        TextView txtReleaseDate = findViewById(R.id.txtInfoReleaseDate);
        TextView txtRuntime = findViewById(R.id.txtInfoRuntime);
        TextView txtOverview = findViewById(R.id.txtInfoOverview);
        TextView txtOriginalLanguage = findViewById(R.id.txtInfoOriginalLanguage);

        Bundle bundle = getIntent().getExtras();

        String imgMovieInfo = bundle.getString("poster_url");
        String txtInfoTitle = bundle.getString("title");
        String txtInfoVoteAverage = bundle.getString("vote_average");
        String txtInfoGenres = bundle.getString("genres");
        String txtInfoVoteCount = bundle.getString("vote_count");
        String txtInfoReleaseDate = bundle.getString("release_date");
        String txtInfoRuntime = bundle.getString("runtime");
        String txtInfoOverview = bundle.getString("overview");
        String txtInfoOriginalLanguage = bundle.getString("original_language");

        Glide.with(MovieActivity.this).load(imgMovieInfo).into(imgImageView);
        txtTitle.setText(txtInfoTitle);
        txtVoteAverage.setText(txtInfoVoteAverage);
        txtGenres.setText(txtInfoGenres);
        txtVoteCount.setText(txtInfoVoteCount);
        txtReleaseDate.setText(txtInfoReleaseDate);
        txtRuntime.setText(txtInfoRuntime);
        txtOverview.setText(txtInfoOverview);
        txtOriginalLanguage.setText(txtInfoOriginalLanguage);

    }
}
