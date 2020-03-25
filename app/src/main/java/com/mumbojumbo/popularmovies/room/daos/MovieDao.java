package com.mumbojumbo.popularmovies.room.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.mumbojumbo.popularmovies.model.Movie;

import java.util.List;

@Dao
public interface MovieDao {
    @Insert
    public void insertMovie(Movie aMovie);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    public void updateMovie(Movie aMovie);

    @Query("SELECT * FROM Movie where movieId = (:id)")
    public LiveData<Movie> getMovieById(int id);

    @Query("SELECT * FROM Movie ORDER BY popularity")
    public LiveData<List<Movie>> getAllPopularMovies();

    @Query("SELECT * FROM Movie WHERE isFavorite=1")
    public LiveData<List<Movie>> getFavoriteMovies();

}
