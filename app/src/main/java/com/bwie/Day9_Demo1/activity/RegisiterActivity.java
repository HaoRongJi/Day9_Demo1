package com.bwie.Day9_Demo1.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bwie.Day9_Demo1.R;
import com.bwie.Day9_Demo1.bean.UserBean;
import com.bwie.Day9_Demo1.presenter.RegisiterPresenter;
import com.bwie.Day9_Demo1.view.IRegView;

public class RegisiterActivity extends AppCompatActivity implements IRegView {

    private RegisiterPresenter presenter;
    private EditText mobileEt;
    private EditText pwdEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regisiter);

        init();
    }

    private void init() {
        initView();
        initData();
    }

    private void initData() {

        presenter=new RegisiterPresenter(this);
    }

    private void initView() {
        mobileEt = findViewById(R.id.mobile_et1);
        pwdEt = findViewById(R.id.pwd_et1);
    }


    @Override
    public void mobileEmpty() {
        Toast.makeText(this,"手机号不能为空",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void mobileForm() {
        Toast.makeText(this,"手机号格式不正确",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void pwdEmpty() {
        Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void pwdForm() {
        Toast.makeText(this, "密码格式不正确", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void success(UserBean userBean) {
        Toast.makeText(this,userBean.msg,Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void failure(String errorMsg) {
        Toast.makeText(this,errorMsg,Toast.LENGTH_SHORT).show();
    }

    public void regisiter(View view) {
        presenter.regisiter(mobileEt.getText().toString(),pwdEt.getText().toString());
    }
}
