package com.example.nutricitrus.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ExerciseRepository {
    private final ExerciseDao exerciseDao;
    private final LiveData<List<Exercise>> allExercises;

    public ExerciseRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        exerciseDao = db.exerciseDao();
        allExercises = exerciseDao.getAllExercises();
    }

    public LiveData<List<Exercise>> getAllExercises() {
        return allExercises;
    }

    public void insert(Exercise exercise) {
        AppDatabase.databaseWriteExecutor.execute(() -> exerciseDao.insert(exercise));
    }
}
