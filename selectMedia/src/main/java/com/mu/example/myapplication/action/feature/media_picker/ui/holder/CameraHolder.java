package com.mu.example.myapplication.action.feature.media_picker.ui.holder;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.mu.example.myapplication.App;
import com.mu.example.myapplication.R;
import com.mu.example.myapplication.util.FileUtil;

import java.io.File;
import java.io.IOException;

/**
 * Created by mu on 2018/1/10.
 */

public class CameraHolder extends PickHolder {
    ImageView camera;
    Context context;

    public CameraHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
        camera = itemView.findViewById(R.id.camera);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestCamera();
            }
        });
    }

    interface requestMediaCaptrue {

    }

    private void requestCamera() {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        File tempFile = null;
        try {
            tempFile = FileUtil.createTmpFile(MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Uri output;
        ContentValues contentValues = new ContentValues();
//        contentValues.put(MediaStore.Images.Media.DATA, tempFile.getAbsolutePath());
//        contentValues.put(MediaStore.Files.FileColumns.MIME_TYPE, "image/jpeg");
//        contentValues.put(MediaStore.MediaColumns.SIZE, tempFile.length());
        contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, "mumu" + ".jpg");
//        contentValues.put(MediaStore.Files.FileColumns.MEDIA_TYPE, MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE);
        output = App.getApplication().getContentResolver().
                insert(Uri.parse("content://media/external/images/media"), contentValues);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//如果是7.0android系统
//
//        } else {
//            output = Uri.fromFile(tempFile);
//
//        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, output);
        context.startActivity(intent);
    }


}
