package com.mu.example.myapplication.core.permission;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;

import com.mu.example.myapplication.App;
import com.mu.example.myapplication.util.ToastUtil;

import java.util.ArrayList;

/**
 * Created by mu on 2018/2/5.
 */

public class PermissionUtil {
    private Activity mActivity;
    private String[] mPermission;
    private int mRequestCode;
    private IPermissionSuccess mPermissionSuccess;
    private IPermissionFail mPermissionFail;
    private String[] mUnauthorizedPermission;

    public PermissionUtil(Activity activity) {
        mActivity = activity;
    }

    public static PermissionUtil with(Activity activity) {
        return new PermissionUtil(activity);
    }

    public static PermissionUtil permission(String[] permission) {
        PermissionUtil permissionUtil = new PermissionUtil(App.mCurrentActivity);
        permissionUtil.mPermission = permission;
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

    public void request() {
        if (mPermission.length != 0) {
            requestPermssion();
        } else {
            ToastUtil.ToastShort("没有要请求的权限");
        }
    }

    private void requestPermssion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !hasPermission()) {
            mActivity.requestPermissions(mPermission, mRequestCode);
        } else {
            mPermissionSuccess.onSuccess();
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == mRequestCode) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    mPermissionFail.onFail(permissions[i]);
                    return;
                }
            }
            mPermissionSuccess.onSuccess();
        }

    }

    private boolean hasPermission() {
        ArrayList<String> permissionList = new ArrayList<String>();

        for (String permission : mPermission) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (mActivity.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
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
