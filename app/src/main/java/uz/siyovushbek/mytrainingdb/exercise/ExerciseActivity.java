package uz.siyovushbek.mytrainingdb.exercise;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import uz.siyovushbek.mytrainingdb.database.DatabaseHelper;
import uz.siyovushbek.mytrainingdb.R;
import uz.siyovushbek.mytrainingdb.utils.FilesUtil;

public class ExerciseActivity extends AppCompatActivity {

    private TextView exerciseName, exerciseDescription;
    private ImageView exercisePhoto;
    private Button changePhotoButton;
    private DatabaseHelper dbHelper;
    private Exercise exercise;

    private final static int PICK_IMAGE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        Intent intent = getIntent();
        int id = intent.getIntExtra("EXERCISE_ID", 0);

        initViews();

        dbHelper = new DatabaseHelper(this);
        exercise = dbHelper.getExerciseById(id);

        populateUIWithData(exercise);

        changePhotoButton.setOnClickListener(view -> FilesUtil.loadImage(this));
    }

    private void populateUIWithData(Exercise exercise) {
        exerciseName.setText(exercise.getName());
        exerciseDescription.setText(exercise.getDescription());
        String imagePath = exercise.getFileName();
        if("".equals(imagePath)) {
            exercisePhoto.setImageResource(R.drawable.default_exercise_icon);
        } else {
            Glide.with(this).load(imagePath).into(exercisePhoto);
        }
    }

    private void initViews() {
        exerciseDescription = findViewById(R.id.individual_exercise_desc);
        exerciseName = findViewById(R.id.individual_exercise_name);
        exercisePhoto = findViewById(R.id.individual_exercise_image);
        changePhotoButton = findViewById(R.id.individual_exercise_image_change_button);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, ExercisesListActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FilesUtil.PICK_IMAGE && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                String image = FilesUtil.saveToInternalStorage(this, bitmap);
                Glide.with(this).load(image).into(exercisePhoto);
                FilesUtil.deletePreviousIcon(this, exercise.getFileName());
                exercise.setFileName(image);
                dbHelper.changeExercisePhoto(exercise);
            } catch (IOException e) {
                Toast.makeText(this, "Error while loading image", Toast.LENGTH_LONG);
            }

        }
    }

}