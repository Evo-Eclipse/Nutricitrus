package com.example.nutricitrus.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.nutricitrus.data.PFCEntry;
import com.example.nutricitrus.databinding.FragmentHomeBinding;
import com.github.mikephil.charting.charts.LineChart;

import java.util.List;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        homeViewModel.getAllPFCEntries().observe(getViewLifecycleOwner(), new Observer<List<PFCEntry>>() {
            @Override
            public void onChanged(List<PFCEntry> pfcEntries) {
                updateChart(binding.lineChart, pfcEntries);
            }
        });

        binding.buttonRecord.setOnClickListener(view -> recordPFC());

        return root;
    }

    private void recordPFC() {
        String proteinStr = binding.inputProtein.getText().toString();
        String fatStr = binding.inputFat.getText().toString();
        String carbsStr = binding.inputCarbs.getText().toString();
        String fluidsStr = binding.inputFluids.getText().toString();

        if (TextUtils.isEmpty(proteinStr) || TextUtils.isEmpty(fatStr) || TextUtils.isEmpty(carbsStr) || TextUtils.isEmpty(fluidsStr)) {
            Toast.makeText(getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double protein = Double.parseDouble(proteinStr);
        double fat = Double.parseDouble(fatStr);
        double carbs = Double.parseDouble(carbsStr);
        double fluids = Double.parseDouble(fluidsStr);

        homeViewModel.recordPFC(protein, fat, carbs, fluids);
    }

    private void updateChart(LineChart chart, List<PFCEntry> pfcEntries) {
        homeViewModel.updateChart(chart);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}


