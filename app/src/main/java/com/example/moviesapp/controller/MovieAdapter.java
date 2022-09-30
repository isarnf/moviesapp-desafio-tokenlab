
package com.example.moviesapp.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.example.moviesapp.R;
import com.example.moviesapp.model.Movie;

import com.example.moviesapp.model.VolleySingleton;
import com.example.moviesapp.view.MovieActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    private final Context context;
    private final List<Movie> movieList;

    public MovieAdapter(Context context, List<Movie> movies){
        this.context = context;
        movieList = movies;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        Movie movie = movieList.get(position);

        Glide.with(context).load(movie.getPosterUrl()).into(holder.imageView);
        holder.title.setText(movie.getTitle());
        holder.voteAverage.setText(movie.getVoteAverage());
        holder.genres.setText(movie.getGenres());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, MovieActivity.class);

                Bundle bundle = new Bundle();

                bundle.putString("id", movie.getId());
                bundle.putString("poster_url", movie.getPosterUrl());
                bundle.putString("title", movie.getTitle());
                bundle.putString("vote_average", movie.getVoteAverage());
                bundle.putString("genres", movie.getGenres());

                fetchMovieById(movie, bundle, intent);
            }
        });
    }

    private void fetchMovieById(Movie movie, Bundle bundle, Intent intent){

        String url = "https://desafio-mobile.nyc3.digitaloceanspaces.com/movies-v2/" + movie.getId();
        RequestQueue requestQueue = VolleySingleton.getInstance(context).getRequestQueue();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {

                    String voteCount = response.getString("vote_count");
                    String releaseDate = response.getString("release_date");
                    String runtime = response.getString("runtime");
                    String overview = response.getString("overview");
                    String originalLanguage = response.getString("original_language");

                    bundle.putString("vote_count", voteCount);
                    bundle.putString("release_date", releaseDate);
                    bundle.putString("runtime", runtime);
                    bundle.putString("overview", overview);
                    bundle.putString("original_language", originalLanguage);

                    intent.putExtras(bundle);

                    context.startActivity(intent);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "The operation couldn't be completed. Please check if you have internet connection or try again later.", Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(jsonObjectRequest);
    }


    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public static class MovieHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView title;
        TextView voteAverage;
        TextView genres;
        ConstraintLayout constraintLayout;


        public MovieHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imgMovieCard);
            title = itemView.findViewById(R.id.txtCardTitle);
            voteAverage = itemView.findViewById(R.id.txtCardVoteAverage);
            genres = itemView.findViewById(R.id.txtCardGenres);
            constraintLayout= itemView.findViewById(R.id.mainLayout);

        }
    }
}

