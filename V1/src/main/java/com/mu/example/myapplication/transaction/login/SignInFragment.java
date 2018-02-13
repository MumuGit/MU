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
                        int a=0;
                        a=1;
                    }

                    @Override
                    public void onComplete() {
                        int a=0;
                        a=1;
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

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
//                HttpUtil.getApi().login_sms(new OkHttpManager.ResultCallback<String>() {
//                    @Override
//                    public void onError(Call call, Exception e) {
//                        ToastUtil.toast("网络不通");
//
//                    }
//
//                    @Override
//
//                    public void onSuccess(String response) {
//
//
//                        String content = JasonUtil.getContent(response);
//                        int status = JasonUtil.getStatus(response);
//                        if (status == 200) {
//                            //3.保存返回结果到本地表user,
//                            // time_expire_login（即登录状态失效时间；UNIX时间戳）为90天后
//                            ACT1Content act1Content = JasonUtil.getGson().fromJson(content, ACT1Content.class);
//                            DaoUtil.updateUserWithACT1(act1Content);
//
//                            SHHelper.editor.putLong(SHHelper.time_expire_login, System.currentTimeMillis()
//                                    + SHHelper.time_expire_login_90);
//                            SHHelper.editor.commit();
//                            //5.如user.password不为空，转到个人中心页，否则转到密码设置页
//                            String password = DaoUtil.getUser().getPassword();
//                            if (TextUtils.isEmpty(password)) {
//                                startActivity(new Intent(getActivity(), AccountPasswordSetActivity.class));
//                            } else {
//                                ActivityUtils.startNaviActivity(getActivity(), NaviActivity.MINE_FG);
//                            }
//
//                        } else {
//                            passwordErrorCount++;
//                            if (passwordErrorCount >= MAX_COUNT_ALLOW_PASSWORD_MISTAKE) {
//                                mVerifycodeLayout.setVisibility(View.VISIBLE);
//                                EditTextFocusRequestUtil.requestFocus(mVerifycodeInput);
//                            } else {
//                                mVerifycodeLayout.setVisibility(View.GONE);
//                            }
//                            ErrorModel error = JasonUtil.getGson().fromJson(content, ErrorModel.class);
//                            String message = error.getError().getMessage();
//                            ToastUtil.toast(message);
//
//                        }
//                    }
//                }, getActivity(), paramMapSubmit);
            }
        });
    }
}
