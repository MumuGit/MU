package com.mu.example.myapplication.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by mu on 2018/1/16.
 */

public class FragmentUtil {
    public static void addFragment(FragmentManager manager, Fragment fragment, int containerId) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(containerId, fragment);
        transaction.commit();
    }
}
