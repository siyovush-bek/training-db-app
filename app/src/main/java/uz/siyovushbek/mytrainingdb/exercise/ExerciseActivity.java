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

import uz.siyovushbek.mytrainingdb.DatabaseUtil;
import uz.siyovushbek.mytrainingdb.R;

public class ExerciseActivity extends AppCompatActivity {

    private TextView exerciseName, exerciseDescription;
    private ImageView exercisePhoto;
    private Button changePhotoButton;

    private Exercise exercise;

    private final static int PICK_IMAGE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        Intent intent = getIntent();
        int position = intent.getIntExtra("EXERCISE_ID", 0);

        exerciseDescription = findViewById(R.id.individual_exercise_desc);
        exerciseName = findViewById(R.id.individual_exercise_name);
        exercisePhoto = findViewById(R.id.individual_exercise_image);
        changePhotoButton = findViewById(R.id.individual_exercise_image_change_button);

        exercise = DatabaseUtil.getInstance().getExerciseById(position);

        exerciseName.setText(exercise.getName());
        exerciseDescription.setText(exercise.getDescription());
        String imagePath = exercise.getFileName();
        if("".equals(imagePath)) {
            exercisePhoto.setImageResource(R.drawable.default_exercise_icon);
        } else {
            Glide.with(this).load(imagePath).into(exercisePhoto);
        }

        changePhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadImage();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, ExercisesListActivity.class);
//        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            Uri uri = data.getData();
//            String filePath = saveImageToInternalStorage(uri);
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                String image = saveToInternalStorage(bitmap);
                Glide.with(this).load(image).into(exercisePhoto);
                exercise.setFileName(image);
            } catch (IOException e) {
                Toast.makeText(this, "Error while loading image", Toast.LENGTH_LONG);
            }

        }
    }

    private String saveToInternalStorage(Bitmap bitmapImage){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        String imageName = generateImageName();
        File mypath=new File(directory,imageName);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath() + "/" + imageName;
    }

    private String generateImageName() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return "JPEG_" + timeStamp + "_.jpg" ;
    }

    private void loadImage() {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, PICK_IMAGE);
    }

}