package com.mumbojumbo.popularmovies.model;

import com.google.gson.annotations.SerializedName;

public class Comment {
    @SerializedName("author")
    private String mAuthor;
    @SerializedName("content")
    private String mContent;

    public Comment(String mAuthor, String mContent) {
        this.mAuthor = mAuthor;
        this.mContent = mContent;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public void setmAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }
}
