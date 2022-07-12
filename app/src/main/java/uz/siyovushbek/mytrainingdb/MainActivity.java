package uz.siyovushbek.mytrainingdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import uz.siyovushbek.mytrainingdb.exercise.ExercisesListActivity;
import uz.siyovushbek.mytrainingdb.record.ExerciseRecordsActivity;

public class MainActivity extends AppCompatActivity {

    private Button exercisesButton, exerciseRecordsButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initButtons();

        setOnClickListeners();

        DatabaseUtil.populateWithTestData();

    }

    private void setOnClickListeners() {
        exercisesButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ExercisesListActivity.class);
            startActivity(intent);
        });

        exerciseRecordsButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ExerciseRecordsActivity.class);
            startActivity(intent);
        });

    }

    private void initButtons() {
        exerciseRecordsButton = findViewById(R.id.training_records_button);
        exercisesButton = findViewById(R.id.exercises_button);
    }
}