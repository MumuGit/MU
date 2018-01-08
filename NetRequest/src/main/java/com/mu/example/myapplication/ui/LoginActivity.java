package com.mu.example.myapplication.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mu.example.myapplication.R;
import com.mu.example.myapplication.bean.LoginBean;
import com.mu.example.myapplication.presenter.ILoginPresenter;
import com.mu.example.myapplication.presenter.LoginPresenterImpl;
import com.mu.example.myapplication.utils.CheckNetwork;
import com.mu.example.myapplication.utils.DebugUtil;

/**
 * Created by mu on 2018/1/5.
 */

public class LoginActivity extends AppCompatActivity implements ILoginView{
    Button btn_login;
    EditText et_store;

    //用户名
    EditText et_user;

    // 密码
    EditText et_ps;
    ILoginPresenter loginPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!CheckNetwork.isNetworkConnected(LoginActivity.this)) {//?没有连接不包括wifi？
                    DebugUtil.ToastShort(LoginActivity.this, "请检查网络连接！");
                    return;
                }
                getInfo();
                loginPresenter.pLogin(storeName, userName, password, IP);

            }
        });
        et_store = findViewById(R.id.et_store);
        et_user = findViewById(R.id.et_user);
        et_ps = findViewById(R.id.et_ps);
        loginPresenter = new LoginPresenterImpl(LoginActivity.this, this);

    }

    String storeName;
    String userName;
    String password;
    String IP;

    private void getInfo() {
        storeName = et_store.getText().toString();
        userName = et_user.getText().toString();
        password = et_ps.getText().toString();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onSuccess(LoginBean bean) {
        DebugUtil.ToastShort(this,"登录成功");
    }

    @Override
    public void showDialogFailed(String msg, Exception e) {
        DebugUtil.ToastShort(this,"登录"+msg);

    }
}
