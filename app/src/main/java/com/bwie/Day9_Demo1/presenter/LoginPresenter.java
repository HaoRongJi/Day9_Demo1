package com.bwie.Day9_Demo1.presenter;

import com.bwie.Day9_Demo1.bean.LoginBean;
import com.bwie.Day9_Demo1.model.LoginModel;
import com.bwie.Day9_Demo1.view.ILoginView;

import java.util.HashMap;

public class LoginPresenter {

    private ILoginView iLoginView;
    private LoginModel loginModel;

    public LoginPresenter(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
        this.loginModel = new LoginModel();
    }

    public  void login(HashMap<String, String> hashMap) {
        loginModel.login(hashMap, new LoginModel.LoginCallBack() {
            @Override
            public void failure(String errorMsg) {
                iLoginView.failure(errorMsg);
            }

            @Override
            public void success(LoginBean loginBean) {
                iLoginView.success(loginBean);
            }
        });
    }
}
