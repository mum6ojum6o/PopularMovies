package com.mumbojumbo.popularmovies.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mumbojumbo.popularmovies.model.Movie;
import com.mumbojumbo.popularmovies.room.daos.MovieDao;



@Database(entities={Movie.class},version=1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private volatile static AppDatabase mDb;
    public abstract MovieDao mMovieDao();

    public static AppDatabase getInstance(Context context){
        if(mDb!=null){
            return mDb;
        }
        synchronized (new Object()) {
            mDb = Room.databaseBuilder(context, AppDatabase.class, "Popular_Movies").build();
        }
        return mDb;
    }
}
