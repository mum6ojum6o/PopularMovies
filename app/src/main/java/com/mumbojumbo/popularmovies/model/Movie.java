package com.mumbojumbo.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class Movie implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int tableId;

    @ColumnInfo(name="movieid")
    @SerializedName("id")
    private int movieId;

    @Ignore
    @SerializedName("original_title")
    private String mOriginalTitle;

    @ColumnInfo(name="movieTitle")
    @SerializedName("title")
    private String mTitle;

    @ColumnInfo(name="overview")
    @SerializedName("overview")
    private String mOverview;

    @ColumnInfo(name="popularity")
    @SerializedName("popularity")
    private double mPopularity;

    @ColumnInfo(name="poster")
    @SerializedName("poster_path")
    private String mPoster;

    @ColumnInfo(name="backdrop")
    @SerializedName("backdrop_path")
    private String mBackdrop;

    @ColumnInfo(name="releaseDate")
    @SerializedName("release_date")
    private String mReleaseDate;

    private boolean isFavorite;
    @Ignore
    @SerializedName("vote_average")
    private double mVoteAverage;

    @Ignore
    public Movie(){}

    @Ignore
    public Movie(int movieId) {
        this.movieId = movieId;
    }



    @Ignore
    public Movie(String mOriginalTitle, String mTitle,
                 String mOverview, double mPopularity, String mPoster,
                  String backdrop, String mReleaseDate, int id) {
        this.mOriginalTitle = mOriginalTitle;
        this.mTitle = mTitle;
        this.mOverview = mOverview;
        this.mPopularity = mPopularity;
        this.mPoster = mPoster;
        this.mBackdrop = backdrop;
        this.mReleaseDate = mReleaseDate;
        this.movieId = id;
    }

    public Movie( String mTitle,
                 String mOverview, double mPopularity, String mPoster,
                 String backdrop, String mReleaseDate, int movieId,boolean isFavorite
                 ) {
        //this.mOriginalTitle = mOriginalTitle;
        this.mTitle = mTitle;
        this.mOverview = mOverview;
        this.mPopularity = mPopularity;
        this.mPoster = mPoster;
        this.mBackdrop = backdrop;
        this.mReleaseDate = mReleaseDate;
        this.movieId = movieId;
        this.isFavorite = isFavorite;
        /*this.voteAverage =voteAverage;
        this.page = page;
        */
    }

    @Ignore
    public Movie(  String mTitle,
                 String mOverview, double mPopularity, String mPoster,
                 String backdrop, String mReleaseDate,boolean isFavorite) {
        //this.mOriginalTitle = mOriginalTitle;
        this.mTitle = mTitle;
        this.mOverview = mOverview;
        this.mPopularity = mPopularity;
        this.mPoster = mPoster;
        this.mBackdrop = backdrop;
        this.mReleaseDate = mReleaseDate;
        this.isFavorite = isFavorite;
    }
    @Ignore
    public Movie(int tableId, int movieId, String mOriginalTitle,
                 String mTitle, String mOverview, double mPopularity,
                 String mPoster, String mBackdrop, String mReleaseDate,
                 boolean isFavorite, double mVoteAverage) {
        this.tableId = tableId;
        this.movieId = movieId;
        this.mOriginalTitle = mOriginalTitle;
        this.mTitle = mTitle;
        this.mOverview = mOverview;
        this.mPopularity = mPopularity;
        this.mPoster = mPoster;
        this.mBackdrop = mBackdrop;
        this.mReleaseDate = mReleaseDate;
        this.isFavorite = isFavorite;
        this.mVoteAverage = mVoteAverage;
    }

    @Ignore
    public Movie(int id, String movieTitle, String overview,
                 double popularity, String poster, String backdrop,
                 String releaseDate, boolean isFavorite//,
                /* int page, double voteAverage*/) {
        this.movieId = id;
        this.mTitle = movieTitle;
        this.mOverview = overview;
        this.mPopularity = popularity;
        this.mPoster = poster;
        this.mReleaseDate = releaseDate;
        this.isFavorite = isFavorite;
        this.mBackdrop = backdrop;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }


    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
    public int getMovieId() {
        return movieId;
    }

    public String getmOriginalTitle() {
        return mOriginalTitle;
    }

    public void setmOriginalTitle(String mOriginalTitle) {
        this.mOriginalTitle = mOriginalTitle;
    }

    public String getmTitle() {
        return mTitle;
    }
    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmOverview() {
        return mOverview;
    }
    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String mOverview) {
        this.mOverview = mOverview;
    }

    public double getmPopularity() {
        return mPopularity;
    }
    public double getPopularity() {
        return mPopularity;
    }

    public void setPopularity(double mPopularity) {
        this.mPopularity = mPopularity;
    }

    public String getmPoster() {
        return mPoster;
    }
    public String getPoster() {
        return mPoster;
    }

    public void setPoster(String mPoster) {
        this.mPoster = mPoster;
    }

    public String getmReleaseDate() {
        return mReleaseDate;
    }
    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String mReleaseDate) {
        this.mReleaseDate = mReleaseDate;
    }
    public double getmVoteCount() {
        return mVoteAverage;
    }

    public void setmVoteCount(double mVoteCount) {
        this.mVoteAverage= mVoteCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getmBackdrop() {
        return mBackdrop;
    }
    public String getBackdrop() {
        return mBackdrop;
    }

    public void setBackdrop(String mBackdrop) {
        this.mBackdrop = mBackdrop;
    }

    public double getmVoteAverage() {
        return mVoteAverage;
    }

    public void setmVoteAverage(double mVoteAverage) {
        this.mVoteAverage = mVoteAverage;
    }


    /*public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }*/

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }


    public static Creator<Movie> getCREATOR() {
        return CREATOR;
    }
    @Ignore
    public Movie(Parcel in) {
        this.tableId = in.readInt();
        this.movieId = in.readInt();
        this.mOriginalTitle = in.readString();
        this.mOverview = in.readString();
        this.mPoster = in.readString();
        this.mBackdrop = in.readString();
        this.mPopularity=in.readDouble();
        this.mReleaseDate=in.readString();
        this.isFavorite = in.readInt()==1;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.tableId);
        dest.writeInt(this.movieId);
        dest.writeString(this.mOriginalTitle);
        dest.writeString(this.mOverview);
        dest.writeString(this.mPoster);
        dest.writeString(this.mBackdrop);
        dest.writeDouble(this.mPopularity);
        dest.writeString(this.mReleaseDate);
        dest.writeInt(this.isFavorite?1:0);
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[0];
        }
    };
}
