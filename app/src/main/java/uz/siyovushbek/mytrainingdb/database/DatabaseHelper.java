package uz.siyovushbek.mytrainingdb.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import uz.siyovushbek.mytrainingdb.exercise.Exercise;

public class DatabaseHelper extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "exercise_records.db";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableExercisesStatement = "CREATE TABLE exercises (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "description TEXT NOT NULL," +
                "image_path TEXT" +
                ")";
        sqLiteDatabase.execSQL(createTableExercisesStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOne(Exercise exercise) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", exercise.getName());
        cv.put("description", exercise.getDescription());
        cv.put("image_path", exercise.getFileName());

        long l = db.insert("exercises", null, cv);
        if (l == -1) {
            return false;
        }
        return true;
    }

    public List<Exercise> getAllExercises() {
        List<Exercise> exercisesList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM exercises;";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String description = cursor.getString(2);
                String fileName = cursor.getString(3);
                exercisesList.add(new Exercise(id, name, description, fileName));
            } while (cursor.moveToNext());
        }
        return exercisesList;
    }

    public Exercise getExerciseById(int id) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM exercises WHERE id = " + id;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()) {
            String name = cursor.getString(1);
            String description = cursor.getString(2);
            String fileName = cursor.getString(3);
            return new Exercise(id, name, description, fileName);
        }
        return null;
    }

    public void changeExercisePhoto(Exercise updatedExercise) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "UPDATE exercises SET image_path = \"" + updatedExercise.getFileName() + "\"" +
                " WHERE id = " + updatedExercise.getId();
        db.execSQL(query);
    }


}
