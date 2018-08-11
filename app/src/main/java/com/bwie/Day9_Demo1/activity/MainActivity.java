package com.bwie.Day9_Demo1.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.bwie.Day9_Demo1.R;
import com.bwie.Day9_Demo1.bean.LoginBean;
import com.bwie.Day9_Demo1.bean.UserBean;
import com.bwie.Day9_Demo1.presenter.LoginPresenter;
import com.bwie.Day9_Demo1.view.ILoginView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements ILoginView {

    private EditText mobileEt;
    private EditText pwdEt;
    private LoginPresenter loginPresenter;
    private int REQUEST_CODE=0x1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();


    }

    private void init() {
        initView();
        initData();
    }

    private void initData() {
        loginPresenter=new LoginPresenter(this);
    }

    private void initView() {
        mobileEt = findViewById(R.id.mobile_et);
        pwdEt = findViewById(R.id.pwd_et);
    }

    public void toRegisiter(View view) {
        startActivity(new Intent(this,RegisiterActivity.class));
    }

    public void toLogin(View view) {


            HashMap<String,String> hashMap=new HashMap<>();
            hashMap.put("mobile",mobileEt.getText().toString());
            hashMap.put("password",pwdEt.getText().toString());
            loginPresenter.login(hashMap);


    }

    @Override
    public void mobileEmpty() {

    }

    @Override
    public void mobileForm() {

    }

    @Override
    public void pwdEmpty() {

    }

    @Override
    public void pwdForm() {

    }

    @Override
    public void success(LoginBean userBean) {
        startActivity(new Intent(this,ShopActivity.class));
    }


    @Override
    public void failure(String errorMsg) {

    }

    public void toQR(View view) {
        startActivity(new Intent(this,QRActivity.class));
    }
}
