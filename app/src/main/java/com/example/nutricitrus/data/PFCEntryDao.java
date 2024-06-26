package com.example.nutricitrus.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PFCEntryDao {
    @Insert
    void insert(PFCEntry entry);

    @Query("SELECT * FROM pfc_entries ORDER BY timestamp DESC")
    LiveData<List<PFCEntry>> getAllPFCEntries();
}
