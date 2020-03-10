package com.mumbojumbo.popularmovies.room.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.mumbojumbo.popularmovies.room.entities.*;

import java.util.List;

@Dao
public interface MovieDao {
    @Update(onConflict = OnConflictStrategy.REPLACE)
    public void save(Movie aMovie);
    @Query("SELECT * FROM Movie where id = (:id)")
    public Movie getMovieById(int id);
    @Query("SELECT * FROM Movie ORDER BY popularity")
    public LiveData<List<Movie>> getAllPopularMovies();

    @Query("SELECT * FROM Movie ORDER BY voteAverage")
    public LiveData<List<Movie>> getAllMovieByVotes();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void saveAll(List<Movie> movies);

    @Query("SELECT * FROM Movie WHERE isFavorite=1")
    public LiveData<List<Movie>> getFavoriteMovies();


}
