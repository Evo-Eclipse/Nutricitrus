package com.example.nutricitrus.ui.home;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.nutricitrus.data.PFCEntry;
import com.example.nutricitrus.data.PFCRepository;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends AndroidViewModel {
    private final PFCRepository repository;
    private final LiveData<List<PFCEntry>> allPFCEntries;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        repository = new PFCRepository(application);
        allPFCEntries = repository.getAllPFCEntries();
    }

    public void recordPFC(double protein, double fat, double carbs, double fluids) {
        if (protein > 0 && fat > 0 && carbs > 0 && fluids > 0) {
            PFCEntry entry = new PFCEntry(protein, fat, carbs, fluids, System.currentTimeMillis());
            Log.d("HomeViewModel", "Recording PFC Entry: " + entry.getProtein() + ", " + entry.getTimestamp());
            repository.insert(entry);
        } else {
            Log.e("HomeViewModel", "Attempted to record invalid PFC data: " + protein + ", " + fat + ", " + carbs + ", " + fluids);
        }
    }

    public void updateChart(LineChart chart) {
        List<Entry> values = new ArrayList<>();
        List<PFCEntry> pfcEntries = allPFCEntries.getValue();

        if (pfcEntries != null) {
            for (PFCEntry pfc : pfcEntries) {
                if (isValidEntry(pfc)) {
                    Log.d("HomeViewModel", "PFC Entry: " + pfc.getProtein() + ' '
                            + pfc.getFat() + ' ' + pfc.getCarbs() + ' ' + pfc.getFluids() + ' '
                            + pfc.getTimestamp());
                    values.add(new Entry(pfc.getTimestamp(), (float) pfc.getProtein()));
                } else {
                    Log.e("HomeViewModel", "Invalid PFC Entry: " + pfc.getProtein() + ' '
                            + pfc.getFat() + ' ' + pfc.getCarbs() + ' ' + pfc.getFluids() + ' '
                            + pfc.getTimestamp());
                }
            }

            if (!values.isEmpty()) {
                LineDataSet dataSet = new LineDataSet(values, "PFC Chart");
                LineData lineData = new LineData(dataSet);
                chart.setData(lineData);
                chart.invalidate(); // refresh
            } else {
                Log.e("HomeViewModel", "No valid data entries to display in chart.");
                chart.clear(); // Clear the chart if there are no valid values
            }
        } else {
            Log.e("HomeViewModel", "PFC entries list is null.");
            chart.clear(); // Clear the chart if the pfcEntries is null
        }
    }

    private boolean isValidEntry(PFCEntry entry) {
        long currentTime = System.currentTimeMillis();
        Log.d("HomeViewModel", "Current time: " + currentTime);
        long oneYearAgo = currentTime - (365L * 24 * 60 * 60 * 1000); // One year in milliseconds

        return entry.getTimestamp() > oneYearAgo && entry.getTimestamp() < currentTime;
    }

    public LiveData<List<PFCEntry>> getAllPFCEntries() {
        return allPFCEntries;
    }
}
