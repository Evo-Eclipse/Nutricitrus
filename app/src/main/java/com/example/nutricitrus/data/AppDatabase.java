package com.example.nutricitrus.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {PFCEntry.class, Exercise.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PFCEntryDao pfcEntryDao();
    public abstract ExerciseDao exerciseDao();

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    // Стереть старую базу данных
                    // context.getApplicationContext().deleteDatabase("nutricitrus_database");

                    // Создать новую базу данных
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "nutricitrus_database")
                            .createFromAsset("nutricitrus.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
