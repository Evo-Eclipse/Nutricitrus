package com.example.nutricitrus.ui.exercise;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.nutricitrus.databinding.FragmentExerciseBinding;

import java.util.ArrayList;

public class ExerciseFragment extends Fragment {
    private FragmentExerciseBinding binding;
    private ExerciseViewModel exerciseViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        exerciseViewModel = new ViewModelProvider(this).get(ExerciseViewModel.class);

        binding = FragmentExerciseBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        binding.rvExercises.setLayoutManager(layoutManager);
        ExerciseAdapter adapter = new ExerciseAdapter(new ArrayList<>());
        binding.rvExercises.setAdapter(adapter);
        exerciseViewModel.getAllExercises().observe(getViewLifecycleOwner(), adapter::setExercises);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}