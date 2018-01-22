package com.mu.example.myapplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by mu on 2018/1/19.
 */

public class AddCacheUtil {
    private static AddCacheUtil INSTANCE;

    private AddCacheUtil() {
    }

    private static class Holder {
        private static final AddCacheUtil INSTANCE = new AddCacheUtil();
    }

    public static AddCacheUtil getInstance() {
        return AddCacheUtil.Holder.INSTANCE;
    }

    public static void addCacheOnInternal(InputStream is) {
        File tem = new File(App.getApplication().getCacheDir(), "test");
        OutputStream os = null;
        byte[] barray = new byte[1024];
        try {
            os = new FileOutputStream(tem);
            while (is.read(barray) != -1) {
                os.write(barray);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.flush();
                    os.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}
