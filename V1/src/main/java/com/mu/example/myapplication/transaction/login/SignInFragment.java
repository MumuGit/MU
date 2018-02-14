package com.mu.example.myapplication.transaction.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.mu.example.myapplication.R;
import com.mu.example.myapplication.core.database.GreenDaoHelper;
import com.mu.example.myapplication.greendao.UserDao;
import com.mu.example.myapplication.model.LoginSmsResponse;
import com.mu.example.myapplication.model.SMSResponse;
import com.mu.example.myapplication.util.HttpUtil;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mu on 2018/1/16.
 */

public class SignInFragment extends Fragment {
    private Button sms_code;
    private Button sign_in;
    private EditText phone;
    private EditText captcha;
    private String id;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_sign_in, container, false);
        initView(root);
        return root;
    }


    private void initView(View view) {
        sms_code = view.findViewById(R.id.sms_code);
        sign_in = view.findViewById(R.id.sign_in);
        phone = view.findViewById(R.id.phone);
        captcha = view.findViewById(R.id.captcha);
        sms_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Map<String, String> param = new HashMap();
                param.put("mobile", phone.getText().toString());
                HttpUtil.getApi().sms_create(param).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<SMSResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SMSResponse value) {
                        id = String.valueOf(value.getContent().getSms_id());
                    }

                    @Override
                    public void onError(Throwable e) {
                        int a = 0;
                        a = 1;
                    }

                    @Override
                    public void onComplete() {
                        int a = 0;
                        a = 1;
                    }
                });
            }
        });
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> param = new HashMap();
                param.put("mobile", phone.getText().toString());
                param.put("captcha", captcha.getText().toString());
                param.put("sms_id", id);
                HttpUtil.getApi().login_sms(param).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread()).
                        subscribe(new Observer<LoginSmsResponse>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(LoginSmsResponse value) {
                                UserDao userDao = GreenDaoHelper.getDaoSession().getUserDao();
                                //数据库的增删改查我们都将通过UserDao来进行，插入操作如下：
                                userDao.insert(value.getContent());
                                //new User(null,"david",23,"male")
                                //id传null 即自增。==> 这里是Long类型而不是long
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });

            }
        });
    }
}
