package com.bilev.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.bilev.data.local.dao.FavoriteCourseDao;
import com.bilev.data.local.entity.FavoriteCourseEntity;

@Database(entities = {FavoriteCourseEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FavoriteCourseDao favoriteCourseDao();
}
