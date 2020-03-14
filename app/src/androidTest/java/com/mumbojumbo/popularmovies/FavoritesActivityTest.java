package com.mumbojumbo.popularmovies;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
@RunWith(AndroidJUnit4.class)
public class FavoritesActivityTest {
    @Rule
    ActivityTestRule activityTestRule =
          new ActivityTestRule(FavoritesActivity.class);
    @Test
    public void onCreate() {

    }

    @Test
    public void setUpViewModel() {
    }

    @Test
    public void getMoviesFromPage() {
    }
}