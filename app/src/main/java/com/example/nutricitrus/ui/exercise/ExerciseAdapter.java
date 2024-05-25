package com.example.nutricitrus.ui.exercise;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nutricitrus.R;
import com.example.nutricitrus.data.Exercise;
import com.example.nutricitrus.databinding.ItemExerciseBinding;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {
    private List<Exercise> exercises;

    public ExerciseAdapter(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_exercise, parent, false);
        return new ExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        Exercise exercise = exercises.get(position);
        holder.bind(exercise);
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
        notifyDataSetChanged();
    }

    static class ExerciseViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTextView;
        private final TextView descriptionTextView;
        private final ImageView imageView;

        public ExerciseViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.exercise_name);
            descriptionTextView = itemView.findViewById(R.id.exercise_description);
            imageView = itemView.findViewById(R.id.exercise_image);
        }

        public void bind(Exercise exercise) {
            nameTextView.setText(exercise.getName());
            descriptionTextView.setText(exercise.getDescription());
            // Load the image from assets using the imagePath
            try {
                InputStream ims = itemView.getContext().getAssets().open(exercise.getImagePath());
                Drawable d = Drawable.createFromStream(ims, null);
                imageView.setImageDrawable(d);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}