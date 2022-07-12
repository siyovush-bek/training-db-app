package uz.siyovushbek.mytrainingdb;

import java.util.ArrayList;
import java.util.List;

import uz.siyovushbek.mytrainingdb.exercise.Exercise;

public class DatabaseUtil {
    private ArrayList<Exercise> exercisesList;
    private static DatabaseUtil instance;
    private static boolean isTestDataPopulated = false;

    private DatabaseUtil() {
        exercisesList = new ArrayList<>();
    }

    public static DatabaseUtil getInstance() {
        if(instance == null) {
            instance = new DatabaseUtil();
        }
        return instance;
    }

    public List<Exercise> getAllExercises() {
        return exercisesList;
    }


    public boolean addExercise(Exercise exercise) {
        exercisesList.add(exercise);
        return true;
    }

    public final Exercise getExerciseById(int i) {
        return exercisesList.get(i);
    }

    public static void populateWithTestData() {
        if(isTestDataPopulated) {
            return;
        }
        getInstance().addExercise(new Exercise("Push up", "Great exercise for chest muscles", ""));
        getInstance().addExercise(new Exercise("Pull up", "Great exercise for biceps", ""));
        getInstance().addExercise(new Exercise("Squad", "Great exercise for leg muscles", ""));
        getInstance().addExercise(new Exercise("Push up Level 2", "Great exercise for chest muscles", ""));
        getInstance().addExercise(new Exercise("Pull up Level 9", "Great exercise for biceps", ""));
        getInstance().addExercise(new Exercise("Squad Level 8", "Great exercise for leg muscles", ""));
    }

}
