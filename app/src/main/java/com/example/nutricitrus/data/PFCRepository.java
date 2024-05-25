package com.example.nutricitrus.data;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PFCRepository {
    private final PFCEntryDao pfcEntryDao;
    private final LiveData<List<PFCEntry>> allPFCEntries;

    public PFCRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        pfcEntryDao = db.pfcEntryDao();
        allPFCEntries = pfcEntryDao.getAllPFCEntries();
    }

    public LiveData<List<PFCEntry>> getAllPFCEntries() {
        return allPFCEntries;
    }

    public void insert(PFCEntry entry) {
        if (entry.getProtein() > 0 && entry.getFat() > 0 && entry.getCarbs() > 0 && entry.getFluids() > 0) {
            AppDatabase.databaseWriteExecutor.execute(() -> pfcEntryDao.insert(entry));
        } else {
            Log.e("PFCRepository", "Attempted to insert invalid PFC entry: " + entry.getProtein() + ", " + entry.getFat() + ", " + entry.getCarbs() + ", " + entry.getFluids());
        }
    }
}