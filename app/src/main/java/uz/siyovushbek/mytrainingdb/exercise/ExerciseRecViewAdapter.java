package uz.siyovushbek.mytrainingdb.exercise;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import uz.siyovushbek.mytrainingdb.R;

public class ExerciseRecViewAdapter
        extends RecyclerView.Adapter<ExerciseRecViewAdapter.ViewHolder> {

    private final Context context;
    private List<Exercise> exercises;
    public ExerciseRecViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.exercise_item_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        int p = holder.getAdapterPosition();
        holder.exerciseName.setText(exercises.get(position).getName());
        holder.exerciseDescription.setText(exercises.get(position).getDescription());
        String imagePath = exercises.get(position).getFileName();
        if("".equals(imagePath)) {
            holder.exercisePhoto.setImageResource(R.drawable.default_exercise_icon);
        } else {
            Glide.with(context).load(imagePath).into(holder.exercisePhoto);
        }
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ExerciseActivity.class);
                int id = exercises.get(p).getId();
                intent.putExtra("EXERCISE_ID", id);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView exerciseName, exerciseDescription;
        private ImageView exercisePhoto;
        private RelativeLayout parent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            exerciseName = itemView.findViewById(R.id.exercise_item_name);
            exerciseDescription = itemView.findViewById(R.id.exercise_item_desc);
            exercisePhoto = itemView.findViewById(R.id.exercise_item_photo);
            parent = itemView.findViewById(R.id.exercise_item_parent);
        }
    }
}
