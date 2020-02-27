package com.mumbojumbo.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Movie implements Parcelable {
    @SerializedName("original_title")
    private String mOriginalTitle;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("overview")
    private String mOverview;
    @SerializedName("popularity")
    private double mPopularity;
    @SerializedName("poster_path")
    private String mPoster;
    @SerializedName("release_date")
    private String mReleaseDate;

    @SerializedName("vote_count")
    private int mVoteCount;



    public Movie(Parcel in) {
        this.mOriginalTitle = in.readString();
        this.mOverview = in.readString();
        this.mPoster = in.readString();
        this.mPopularity=in.readDouble();
        this.mReleaseDate=in.readString();
    }

    public Movie(String mOriginalTitle, String mTitle,
                 String mOverview, double mPopularity,
                 String mPoster, String mReleaseDate) {
        this.mOriginalTitle = mOriginalTitle;
        this.mTitle = mTitle;
        this.mOverview = mOverview;
        this.mPopularity = mPopularity;
        this.mPoster = mPoster;
        this.mReleaseDate = mReleaseDate;
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

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmOverview() {
        return mOverview;
    }

    public void setmOverview(String mOverview) {
        this.mOverview = mOverview;
    }

    public double getmPopularity() {
        return mPopularity;
    }

    public void setmPopularity(double mPopularity) {
        this.mPopularity = mPopularity;
    }

    public String getmPoster() {
        return mPoster;
    }

    public void setmPoster(String mPoster) {
        this.mPoster = mPoster;
    }

    public String getmReleaseDate() {
        return mReleaseDate;
    }

    public void setmReleaseDate(String mReleaseDate) {
        this.mReleaseDate = mReleaseDate;
    }
    public int getmVoteCount() {
        return mVoteCount;
    }

    public void setmVoteCount(int mVoteCount) {
        this.mVoteCount = mVoteCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mOriginalTitle);
        dest.writeString(this.mOverview);
        dest.writeString(this.mPoster);
        dest.writeDouble(this.mPopularity);
        dest.writeString(this.mReleaseDate);

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
