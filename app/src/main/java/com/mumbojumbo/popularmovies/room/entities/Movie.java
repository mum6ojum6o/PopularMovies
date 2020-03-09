package com.mumbojumbo.popularmovies.room.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Movie {
    @PrimaryKey
    private int id;
    private String movieTitle;
    private String overview;
    private double popularity;
    private String poster;
    private String releaseDate;
    private boolean isFavorite;
    private int page;
    private double voteAverage;

    public Movie(int id, String movieTitle, String overview,
                 double popularity, String poster,
                 String releaseDate, boolean isFavorite,
                 int page, double voteAverage) {
        this.id = id;
        this.movieTitle = movieTitle;
        this.overview = overview;
        this.popularity = popularity;
        this.poster = poster;
        this.releaseDate = releaseDate;
        this.isFavorite = isFavorite;
        this.page = page;
        this.voteAverage = voteAverage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }
}
