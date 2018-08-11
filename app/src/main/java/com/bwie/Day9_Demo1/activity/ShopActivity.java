package com.bwie.Day9_Demo1.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bwie.Day9_Demo1.R;
import com.bwie.Day9_Demo1.adapter.ProductAdapter;
import com.bwie.Day9_Demo1.bean.ProductBean;
import com.bwie.Day9_Demo1.common.Api;
import com.bwie.Day9_Demo1.utils.OKHttpUtils;
import com.bwie.Day9_Demo1.view.RequestCallback;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

public class ShopActivity extends AppCompatActivity {

    private EditText productEt;
    private Button searchBtn;
    private RecyclerView reclclerView;
    private Handler handler=new Handler();
    private ProductBean productBean;
    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        init();
    }

    private void init() {
        initView();
    }

    private void initView() {
        productEt = findViewById(R.id.product_et);
        searchBtn = findViewById(R.id.search_btn);
        reclclerView = findViewById(R.id.xrecyclerview);
    }


    public void searchProduct(View view) {
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("keywords",productEt.getText().toString());

        OKHttpUtils.getInstance().getData(Api.PRODUCT_URL, hashMap, new RequestCallback() {
            @Override
            public void onResponse(Call call, Response response) {
                String result=null;
                if (response.code()==200){

                    try {
                        result=response.body().string();
                        parsePrpductBean(result);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call call, IOException e) {

            }
        });
    }

    private void parsePrpductBean(String result) {
        productBean = new Gson().fromJson(result, ProductBean.class);
        handler.post(new Runnable() {
            @Override
            public void run() {
                fillDatas();
            }
        });
    }

    private void fillDatas() {
        productAdapter = new ProductAdapter(this, productBean.data);
        reclclerView.setLayoutManager(new LinearLayoutManager(this));

        reclclerView.setAdapter(productAdapter);
    }
}
