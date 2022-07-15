package uz.siyovushbek.mytrainingdb.exercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import uz.siyovushbek.mytrainingdb.R;
import uz.siyovushbek.mytrainingdb.database.DatabaseHelper;

public class AddExerciseActivity extends AppCompatActivity {

    private EditText exerciseName, exerciseDesc;
    private Button addButton;
    private ImageView image;

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);
        dbHelper = new DatabaseHelper(this);
        initViews();
        setOnClickListeners();



    }

    private void setOnClickListeners() {


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dataIsValid()) {
                    String name = exerciseName.getText().toString();
                    String description = exerciseDesc.getText().toString();
                    Exercise exercise = new Exercise(name, description, "");
                    boolean b = dbHelper.addOne(exercise);
                    if(b) {
                        Intent i = new Intent(AddExerciseActivity.this, ExercisesListActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        AddExerciseActivity.this.startActivity(i);
                    }
                }
            }
        });
    }

    private boolean dataIsValid() {
        return true;
    }

    private void initViews() {
        exerciseName = findViewById(R.id.edit_text_exercise_name);
        exerciseDesc = findViewById(R.id.edit_text_exercise_desc);
        addButton = findViewById(R.id.exercise_add_button);
        image = findViewById(R.id.exercise_add_image);
    }


}