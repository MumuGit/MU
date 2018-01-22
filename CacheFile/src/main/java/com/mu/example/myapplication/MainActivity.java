package com.mu.example.myapplication;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test3();
    }

    private void test2() {
        System.out.println(Environment.getDataDirectory());
        System.out.println(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS));
        System.out.println(Environment.getExternalStorageDirectory());
        System.out.println(Environment.getDownloadCacheDirectory());
        System.out.println(Environment.getRootDirectory());
        System.out.println(Environment.getExternalStorageState());


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void test3() {
        System.out.println(getCacheDir());
        System.out.println(getDataDir());
        System.out.println(getFilesDir());
        System.out.println(getExternalCacheDir());
        System.out.println(getCodeCacheDir());
        System.out.println(getNoBackupFilesDir());
        System.out.println(getExternalMediaDirs()[0]);
        System.out.println(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS));


    }

    private void test() {
        final String url = "https://d33wubrfki0l68.cloudfront.net/42023922872cca83b20851f15088d1fd4236d084/e41a8/images/logo.png";
        imageView = (ImageView) findViewById(R.id.test);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    InputStream is = new URL(url).openStream();
                    AddCacheUtil.addCacheOnInternal(is);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        String path = App.getApplication().getCacheDir() + "/test";
        imageView.setImageBitmap(BitmapFactory.decodeFile(path));
    }
}
