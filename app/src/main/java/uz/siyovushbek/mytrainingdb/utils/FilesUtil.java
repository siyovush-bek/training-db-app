package uz.siyovushbek.mytrainingdb.utils;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FilesUtil {

    public final static int PICK_IMAGE = 0;

    public static String saveToInternalStorage(Context context, Bitmap bitmapImage){
        ContextWrapper cw = new ContextWrapper(context.getApplicationContext());
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        String imageName = generateImageName();
        File mypath = new File(directory,imageName);

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

    public static void deletePreviousIcon(Context context, String fileName) {
        if(!"".equals(fileName)) {
            File f = new File(fileName);
            boolean b = f.delete();
            if (b) {
                Toast.makeText(context, "Previous image successfully deleted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Previous image not deleted :((", Toast.LENGTH_SHORT).show();

            }
        }
    }

    private static String generateImageName() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return "JPEG_" + timeStamp + "_.jpg" ;
    }

    public static void loadImage(Activity activity) {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(i, PICK_IMAGE);
    }

}
