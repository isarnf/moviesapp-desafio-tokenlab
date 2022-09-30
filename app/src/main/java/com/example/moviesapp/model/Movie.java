package com.example.moviesapp.model;

import java.util.Objects;

public class Movie {
    private final String id;
    private String title;
    private String posterUrl;
    private String voteAverage;
    private String voteCount;
    private String originalLanguage;
    private String runtime;
    private String releaseDate;
    private String genres;
    private String overview;

    public Movie(String id, String title, String posterUrl, String voteAverage, String voteCount, String originalLanguage, String runtime, String releaseDate, String genres, String overview) {
        this.id = id;
        this.title = title;
        this.posterUrl = posterUrl;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
        this.originalLanguage = originalLanguage;
        this.runtime = runtime;
        this.releaseDate = releaseDate;
        this.genres = genres;
        this.overview = overview;
    }

    public Movie(String id, String posterUrl, String title, String voteAverage, String genres) {
        this.id=id;
        this.posterUrl = posterUrl;
        this.title = title;
        this.voteAverage = voteAverage;
        this.genres = genres;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(String voteCount) {
        this.voteCount = voteCount;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {this.originalLanguage = originalLanguage;}

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id.equals(movie.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", posterUrl='" + posterUrl + '\'' +
                ", voteAverage='" + voteAverage + '\'' +
                ", voteCount='" + voteCount + '\'' +
                ", originalLanguage='" + originalLanguage + '\'' +
                ", runtime='" + runtime + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", genres='" + genres + '\'' +
                ", overview='" + overview + '\'' +
                '}';
    }
}
