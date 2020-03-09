package com.mumbojumbo.popularmovies.room.entities;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Movie implements Parcelable {
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

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(this.movieTitle);
        dest.writeString(this.overview);
        dest.writeString(this.poster);
        dest.writeDouble(this.popularity);
        dest.writeString(this.releaseDate);
        dest.writeDouble(this.voteAverage);
        dest.writeInt(isFavorite?1:0);
        dest.writeInt(this.id);
    }
    public static final Parcelable.Creator<Movie> CREATOR =
            new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[0];
        }
    };

    public Movie(Parcel in) {
        this.movieTitle = in.readString();
        this.overview = in.readString();
        this.poster = in.readString();
        this.popularity=in.readDouble();
        this.releaseDate=in.readString();
        this.voteAverage = in.readDouble();
        this.isFavorite = in.readInt()==1;
        this.id = in.readInt();
    }
    @Override
    public int describeContents() {
        return 0;
    }

}
