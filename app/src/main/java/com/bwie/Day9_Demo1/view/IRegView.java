package com.bwie.Day9_Demo1.view;

import com.bwie.Day9_Demo1.bean.UserBean;

public interface IRegView {

    void mobileEmpty();
    void mobileForm();
    void pwdEmpty();
    void pwdForm();
    void success(UserBean userBean);
    void failure(String errorMsg);

}
