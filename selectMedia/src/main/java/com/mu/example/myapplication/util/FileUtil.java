package com.mu.example.myapplication.util;

import android.os.Environment;

import com.mu.example.myapplication.App;
import com.mu.example.myapplication.model.Folder;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by mu on 2018/3/5.
 */

public class FileUtil {
    private static final String JPEG_FILE_PREFIX = "IMG_";
    private static final String JPEG_FILE_SUFFIX = ".jpg";
    private static final String MP4_FILE_PREFIX = "VID_";
    private static final String MP4_FILE_SUFFIX = ".mp4";

    public static String getParent(String path) {
        String sp[] = path.split(File.separator);
        return sp[sp.length - 2];
    }

    public static int hasDir(ArrayList<Folder> folders, String dirName) {
        for (int i = 0; i < folders.size(); i++) {
            Folder folder = folders.get(i);
            if (folder.getName().equals(dirName)) {
                return i;
            }
        }
        return -1;
    }

    public static File createTmpFile(int type) throws IOException {
        File dir = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            dir = App.getApplication().getExternalCacheDir();
        } else {
            //todo

        }
        return new File(dir, "mumu.jpg");
//        if (type == MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE) {
//            return File.createTempFile(JPEG_FILE_PREFIX, JPEG_FILE_SUFFIX, dir);
//        }
//        if (type == MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO) {
//            return File.createTempFile(MP4_FILE_PREFIX, MP4_FILE_SUFFIX, dir);
//        }
//        return null;
    }


    public static String getTimeFileName() {
        Date currentTime = new Date();
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyyMM");
        SimpleDateFormat formatter2 = new SimpleDateFormat("MMdd");
        SimpleDateFormat formatter3 = new SimpleDateFormat("HHmmss");
        String dateString1 = formatter1.format(currentTime);
        String dateString2 = formatter2.format(currentTime);
        String dateString3 = formatter3.format(currentTime);
        return dateString1 + "/" + dateString2 + "/" + dateString3;


    }
}
