package com.bwie.Day9_Demo1.model;

import android.os.Handler;
import android.os.Message;
import android.telecom.Call;

import com.bwie.Day9_Demo1.bean.UserBean;
import com.bwie.Day9_Demo1.common.Api;
import com.bwie.Day9_Demo1.utils.OKHttpUtils;
import com.bwie.Day9_Demo1.view.RequestCallback;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Response;

public class RegisiterModel {




    Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    public void regisiter(HashMap<String,String> hashMap, final RegCallBack regCallBack){

        OKHttpUtils.getInstance().postData(Api.REG_URL, hashMap, new RequestCallback() {
            @Override
            public void onResponse(okhttp3.Call call, Response response) {
                if (response.code()==200){

                    String result=null;

                    try {
                        result=response.body().string();
                        parseJsonResult(result,regCallBack);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                    if (regCallBack!=null){

                        regCallBack.failure("请求失败");

                    }
            }
        });

    }

    private void parseJsonResult(String result, final RegCallBack regCallBack) {
        if (result!=null){

            final UserBean userBean = new Gson().fromJson(result, UserBean.class);

            handler.post(new Runnable() {
                @Override
                public void run() {

                    if(regCallBack!=null){

                        regCallBack.success(userBean);

                    }

                }
            });

        }
    }

    public interface RegCallBack{

        void success(UserBean userBean);
        void failure(String errorMsg);

    }
}
