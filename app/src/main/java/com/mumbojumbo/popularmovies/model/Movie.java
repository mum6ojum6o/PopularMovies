package com.mumbojumbo.popularmovies.model;

public class Movie {
    private String mOriginalTitle;
    private String mTitle;
    private String mOverview;
    private double mPopularity;
    private String mPoster;
    private String mReleaseDate;

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



}
