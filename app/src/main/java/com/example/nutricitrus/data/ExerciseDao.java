package com.example.nutricitrus.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ExerciseDao {
    @Insert
    void insert(Exercise exercise);

    @Query("SELECT * FROM exercises ORDER BY category")
    LiveData<List<Exercise>> getAllExercises();
}
