package com.mu.example.myapplication.core.permission;

import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.View;

import com.mu.example.myapplication.App;
import com.mu.example.myapplication.R;
import com.mu.example.myapplication.core.ui.poplist.PopListUtil;
import com.mu.example.myapplication.core.ui.poplist.permission.PermissionAdapter;
import com.mu.example.myapplication.util.HandlerUtil;
import com.mu.example.myapplication.util.ScreenUtil;
import com.mu.example.myapplication.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    public static PermissionUtil getPermissionUtil(String key) {
        return permissionMap.get(key);
    }

    private static Map<String, PermissionUtil> permissionMap = new HashMap<>();
    private List<PermissionData> mPermissionData = new ArrayList<>();

    private PermissionUtil() {

    }

    public static PermissionUtil permission(String[] permission) {
        PermissionUtil permissionUtil = new PermissionUtil();
        permissionUtil.mPermission = permission;
        permissionMap.put(App.mCurrentActivity.getClass().getSimpleName(), permissionUtil);
        PermissionData permissionData = new PermissionData();
        permissionUtil.mPermissionData.add(permissionData);
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

    public PermissionUtil explain(String explain) {
        mPermissionData.get(0).setExplain(explain);
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
            mPermissionData.get(0).setRequestCode(mRequestCode);
            mPermissionData.get(0).setUnauthorizedPermission(mUnauthorizedPermission);
            HandlerUtil.getInstance().postTask(new Runnable() {
                @Override
                public void run() {
                    View target = App.mCurrentActivity.getWindow().getDecorView();
                    PopListUtil.with(App.mCurrentActivity, R.layout.view_bc_permission, R.style.permission)
                            .width(ScreenUtil.dip2px(300))
                            .height(ScreenUtil.dip2px(150))
                            .location(target,
                                    target.getWidth() / 2,
                                    target.getHeight() / 2,
                                    ScreenUtil.dip2px(150),
                                    ScreenUtil.dip2px(75))
                            .setAdapter(new PermissionAdapter(App.mCurrentActivity))
                            .updateData(mPermissionData).show();
                }
            });
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
