package com.mumbojumbo.popularmovies.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result<T> {
    @SerializedName("page")
    int mPage;

    int total_results;
    int total_pages;

    List<T> results;

    public Result(int mPage, int total_results, int total_pages, List<T> results) {
        this.mPage = mPage;
        this.total_results = total_results;
        this.total_pages = total_pages;
        this.results = results;
    }

    public int getmPage() {
        return mPage;
    }

    public void setmPage(int mPage) {
        this.mPage = mPage;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
