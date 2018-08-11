package com.bwie.Day9_Demo1.view;

import com.bwie.Day9_Demo1.bean.LoginBean;
import com.bwie.Day9_Demo1.bean.UserBean;

public interface ILoginView {

    void mobileEmpty();
    void mobileForm();
    void pwdEmpty();
    void pwdForm();
    void success(LoginBean userBean);
    void failure(String errorMsg);

}
