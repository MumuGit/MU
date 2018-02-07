package com.mu.example.myapplication.core.permission;

import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;

import com.mu.example.myapplication.App;
import com.mu.example.myapplication.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mu on 2018/2/5.
 */

public class PermissionUtil {
    private String[] mPermission;
    private int mRequestCode;
    private IPermissionSuccess mPermissionSuccess;
    private IPermissionFail mPermissionFail;
    private String[] mUnauthorizedPermission;
    private static Map<String, PermissionUtil> permissionMap = new HashMap<>();

    public PermissionUtil() {

    }

    public static PermissionUtil permission(String[] permission) {
        PermissionUtil permissionUtil = new PermissionUtil();
        permissionUtil.mPermission = permission;
        permissionMap.put(App.mCurrentActivity.getClass().getSimpleName(), permissionUtil);
        return permissionUtil;
    }

    public PermissionUtil success(IPermissionSuccess permissionSuccess) {
        mPermissionSuccess = permissionSuccess;
        return this;
    }

    public PermissionUtil fail(IPermissionFail permissionFail) {
        mPermissionFail = permissionFail;
        return this;
    }

    public PermissionUtil code(int code) {
        mRequestCode = code;
        return this;
    }

    public void request() {
        if (mPermission.length != 0) {
            requestPermssion();
        } else {
            ToastUtil.ToastShort("没有要请求的权限");
        }

    }

    private void requestPermssion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !hasPermission()) {
            App.mCurrentActivity.requestPermissions(mPermission, mRequestCode);
        } else {
            mPermissionSuccess.onSuccess();
        }
    }

    public static void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        String key = App.mCurrentActivity.getClass().getSimpleName();
        PermissionUtil instance = permissionMap.get(key);
        if (instance != null) {
            if (requestCode == instance.mRequestCode) {
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        instance.mPermissionFail.onFail(permissions[i]);
                        permissionMap.remove(key);
                        return;
                    }
                }
                instance.mPermissionSuccess.onSuccess();
                permissionMap.remove(key);
            }
        }

    }

    private boolean hasPermission() {
        ArrayList<String> permissionList = new ArrayList<String>();
        for (String permission : mPermission) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (App.mCurrentActivity.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                    permissionList.add(permission);
                }
            }
        }
        if (permissionList.size() > 0) {
            mUnauthorizedPermission = permissionList.toArray(new String[permissionList.size()]);
            return false;
        }
        return true;
    }

    public interface IPermissionSuccess {
        void onSuccess();
    }

    public interface IPermissionFail {
        void onFail(String refusePermission);
    }


}
