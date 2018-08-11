package com.bwie.Day9_Demo1.presenter;

import android.text.TextUtils;

import com.bwie.Day9_Demo1.bean.UserBean;
import com.bwie.Day9_Demo1.model.RegisiterModel;
import com.bwie.Day9_Demo1.utils.RegexValidateUtil;
import com.bwie.Day9_Demo1.view.IRegView;

import java.util.HashMap;

public class RegisiterPresenter {

    private RegisiterModel regisiterModel;
    private IRegView iRegView;

    public RegisiterPresenter(IRegView iRegView) {
        this.regisiterModel = new RegisiterModel();
        this.iRegView = iRegView;
    }

    public void regisiter(String mobile, String pwd) {
        //正则判断
        if (TextUtils.isEmpty(mobile)){

            iRegView.mobileEmpty();
            return;

        }

        if (!RegexValidateUtil.checkCellphone(mobile)){

            iRegView.mobileForm();
            return;

        }
        if (TextUtils.isEmpty(pwd)){

            iRegView.pwdEmpty();
            return;

        }

        if (pwd.length()<6){

            iRegView.pwdForm();
            return;

        }

        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("mobile",mobile);
        hashMap.put("password",pwd);

        regisiterModel.regisiter(hashMap, new RegisiterModel.RegCallBack() {
            @Override
            public void success(UserBean userBean) {
                iRegView.success(userBean);
            }

            @Override
            public void failure(String errorMsg) {
                iRegView.failure(errorMsg);
            }
        });
    }
}
