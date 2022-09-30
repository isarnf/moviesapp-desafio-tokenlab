package com.example.moviesapp.view;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.moviesapp.R;
import com.example.moviesapp.model.Movie;
import com.example.moviesapp.controller.MovieAdapter;
import com.example.moviesapp.model.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private final List<Movie> movieList = new ArrayList<>();
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Top Ranked Movies");

        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.mainProgressBar);
        recyclerView = findViewById(R.id.lytRecyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchMovies();
    }

    public void fetchMovies() {

        String url = "https://desafio-mobile.nyc3.digitaloceanspaces.com/movies-v2";
        RequestQueue requestQueue = VolleySingleton.getInstance(MainActivity.this).getRequestQueue();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                hideProgressBar();
                for(int i=0; i < response.length();i++){
                    try {

                        JSONObject jsonObject = response.getJSONObject(i);

                        String id = jsonObject.getString("id");
                        String posterUrl = jsonObject.getString("poster_url");
                        String title = jsonObject.getString("title");
                        String voteAverage = jsonObject.getString("vote_average");
                        JSONArray genres = jsonObject.getJSONArray("genres");

                        String stringGenres = getGenresFromJsonArray(genres);

                        Movie movie = new Movie(id, posterUrl, title, voteAverage, stringGenres);
                        movieList.add(movie);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    MovieAdapter adapter = new MovieAdapter(MainActivity.this, movieList);
                    recyclerView.setAdapter(adapter);
                }
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "The operation couldn't be completed. Please check if you have internet connection or try again later.", Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(jsonArrayRequest);
    }

    private String getGenresFromJsonArray(JSONArray genres)  {
        List<String> allGenres = new ArrayList<>();
        for(int i = 0; i<genres.length(); i++){
            String genre = null;
            try {
                genre = genres.getString(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            allGenres.add(genre);
        }
        return String.join(", ", allGenres);
    }

    private void hideProgressBar(){
        progressBar.setVisibility(ProgressBar.GONE);
    }

}