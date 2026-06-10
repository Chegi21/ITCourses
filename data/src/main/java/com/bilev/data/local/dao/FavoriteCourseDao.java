package com.bilev.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.bilev.data.local.entity.FavoriteCourseEntity;

import java.util.List;

@Dao
public interface FavoriteCourseDao {

    @Query("SELECT * FROM favorite_courses")
    LiveData<List<FavoriteCourseEntity>> getAllFavorites();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(FavoriteCourseEntity entity);

    @Query("DELETE FROM favorite_courses WHERE id = :courseId")
    void deleteById(int courseId);

    @Query("SELECT COUNT(*) > 0 FROM favorite_courses WHERE id = :courseId")
    LiveData<Boolean> isFavorite(int courseId);
}
