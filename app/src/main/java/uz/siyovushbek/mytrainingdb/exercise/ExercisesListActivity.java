package uz.siyovushbek.mytrainingdb.exercise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import uz.siyovushbek.mytrainingdb.DatabaseUtil;
import uz.siyovushbek.mytrainingdb.MainActivity;
import uz.siyovushbek.mytrainingdb.R;

public class ExercisesListActivity extends AppCompatActivity {

    private RecyclerView exercisesRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);
        initViews();
        ExerciseRecViewAdapter adapter = new ExerciseRecViewAdapter(this);
        adapter.setExercises(DatabaseUtil.getInstance().getAllExercises());

        exercisesRecyclerView.setAdapter(adapter);
        exercisesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void onBackPressed() {
        Intent i = new Intent(this, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

    private void initViews() {
        exercisesRecyclerView = findViewById(R.id.exercises_rec_view);

    }
}