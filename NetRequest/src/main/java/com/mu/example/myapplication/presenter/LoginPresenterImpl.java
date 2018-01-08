package com.mu.example.myapplication.presenter;

import android.content.Context;

import com.mu.example.myapplication.bean.LoginBean;
import com.mu.example.myapplication.model.ILoginModel;
import com.mu.example.myapplication.model.LoginModelImpl;
import com.mu.example.myapplication.model.OnLoginListener;
import com.mu.example.myapplication.ui.ILoginView;

/**
 * Created by mu on 2018/1/5.
 */

public class LoginPresenterImpl implements ILoginPresenter, OnLoginListener {
    private Context context;
    private ILoginView iLoginView;
    private ILoginModel iLoginModel;

    public LoginPresenterImpl(Context context, ILoginView iLoginView) {
        this.context = context;
        this.iLoginView = iLoginView;
        iLoginModel = new LoginModelImpl();
    }

    @Override
    public void pLogin(String storeName, String userName, String password, String IP) {
        iLoginView.showProgress();
        //model获取数据,由OnLoginListener接口回调处理结果
        iLoginModel.mLogin(storeName, userName, password, IP, this);
    }

    @Override
    public void onLoginSuccess(LoginBean bean) {
        iLoginView.hideProgress();
        iLoginView.onSuccess(bean);
    }

    @Override
    public void onLoginFailed(String msg, Exception e) {
        iLoginView.hideProgress();
        iLoginView.showDialogFailed(msg, e);
    }
}
