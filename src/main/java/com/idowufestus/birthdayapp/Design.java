package com.idowufestus.birthdayapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * Created by IDOWU FESTUS on 8/15/2017.
 */
public class Design extends AppCompatActivity {

    TextView appelation, name;

    Bitmap bitmap;
    Birthday birthday = new Birthday();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.birthday_layout);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);

        instantiateFields();

        appelation.setText(birthday.getAppelation());
        name.setText(birthday.getName());
    }

    private void instantiateFields(){
        appelation = (TextView) findViewById(R.id.txtAppelation);
        name = (TextView) findViewById(R.id.txtSignature);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_image, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemExit:
                finish();
                break;

            case R.id.itemSave:
                Random random = new Random();
                int n = random.nextInt();
                String fileName = "birthday_"+n+".jpg";
//                bitmap = takeScreenShot();
//                saveBitmap(bitmap);
                View rootView = getWindow().getDecorView().findViewById(R.id.relativeLayout);
                getScreenShot(rootView);
                saveImage(getScreenShot(rootView), fileName);
                Toast.makeText(getApplicationContext(), "IMAGE SAVED SUCCESSFULLY", Toast.LENGTH_LONG).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private Bitmap takeScreenShot() {
        View rootView = findViewById(R.id.relativeLayout).getRootView();
        rootView.setDrawingCacheEnabled(true);
        return rootView.getDrawingCache();
    }

    private void saveBitmap(Bitmap bitmap){
        Random random = new Random(1000);
        int n = random.nextInt();
        File imagePath = new File(Environment.getExternalStorageDirectory() + "/screenshot"+n+".jpg");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e("GREC", e.getMessage(), e);
        } catch (IOException e) {
            Log.e("GREC", e.getMessage(), e);
        }

    }

    private static Bitmap getScreenShot(View view){
        View screenView = view.getRootView();
        screenView.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(screenView.getDrawingCache());
        screenView.setDrawingCacheEnabled(false);
        return bitmap;
    }

    private static void saveImage(Bitmap bm, String fileName){
        final String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/card_designs";
        File dir = new File(dirPath);
        if (!dir.exists()){
            dir.mkdirs();
        }
            File file = new File(dirPath, fileName);
            try {
                FileOutputStream fOut = new FileOutputStream(file);
                bm.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
                fOut.flush();
                fOut.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

}
