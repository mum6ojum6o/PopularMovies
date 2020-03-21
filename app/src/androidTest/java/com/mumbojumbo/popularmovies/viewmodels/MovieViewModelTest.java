package com.mumbojumbo.popularmovies.viewmodels;

import android.app.Instrumentation;

import androidx.lifecycle.ViewModelProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.mumbojumbo.popularmovies.MainActivity;
import com.mumbojumbo.popularmovies.room.entities.Movie;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class MovieViewModelTest {

    @Rule
    public ActivityTestRule activityTestRule = new ActivityTestRule(MainActivity.class);

    public MovieViewModel movieViewModel;
    @Before
    public void init(){
        movieViewModel = new ViewModelProvider((MainActivity)activityTestRule.getActivity())
                .get(MovieViewModel.class);
    }

    @Test
    public void getmCachedMovies() {
        assertNotNull(movieViewModel.mCachedMovies);
        assertNotEquals(0,movieViewModel.getmCachedMovies().getValue().size());
    }

    @Test
    public void pullFromNetwork() {

    }

    @Test
    public void updateMovie() {
    }
}