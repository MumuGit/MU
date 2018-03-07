package com.mu.example.myapplication.util;

import com.mu.example.myapplication.model.Folder;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by mu on 2018/3/5.
 */

public class FileUtil {
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
}
