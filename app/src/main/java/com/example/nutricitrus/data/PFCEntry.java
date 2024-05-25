package com.example.nutricitrus.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "pfc_entries")
public class PFCEntry {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private double protein;
    private double fat;
    private double carbs;
    private double fluids;
    private long timestamp;

    public PFCEntry(double protein, double fat, double carbs, double fluids, long timestamp) {
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
        this.fluids = fluids;
        this.timestamp = timestamp;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public double getFluids() {
        return fluids;
    }

    public void setFluids(double fluids) {
        this.fluids = fluids;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
