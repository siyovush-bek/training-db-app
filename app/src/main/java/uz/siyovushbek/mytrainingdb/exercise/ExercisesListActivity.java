package uz.siyovushbek.mytrainingdb.exercise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import uz.siyovushbek.mytrainingdb.database.DatabaseHelper;
import uz.siyovushbek.mytrainingdb.MainActivity;
import uz.siyovushbek.mytrainingdb.R;

public class ExercisesListActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private RecyclerView exercisesRecyclerView;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);
        initViews();
        ExerciseRecViewAdapter adapter = new ExerciseRecViewAdapter(this);
        databaseHelper = new DatabaseHelper(this);
        List<Exercise> allExercises = databaseHelper.getAllExercises();
        adapter.setExercises(allExercises);
        exercisesRecyclerView.setAdapter(adapter);
        exercisesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ExercisesListActivity.this, AddExerciseActivity.class);
                ExercisesListActivity.this.startActivity(i);
            }
        });
    }

    public void onBackPressed() {
        Intent i = new Intent(this, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

    private void initViews() {
        exercisesRecyclerView = findViewById(R.id.exercises_rec_view);

        fab = findViewById(R.id.fab);


    }
}