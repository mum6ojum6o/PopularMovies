package com.mumbojumbo.popularmovies.model;

import java.util.List;

public class Result {
    int mPage;
    int total_results;
    int total_pages;
    List<Movie> results;

    public Result(int mPage, int total_results, int total_pages, List<Movie> results) {
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

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }
}
