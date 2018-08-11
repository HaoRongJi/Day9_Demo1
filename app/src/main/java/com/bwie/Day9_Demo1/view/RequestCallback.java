package com.bwie.Day9_Demo1.view;



import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public interface RequestCallback {

    void onResponse(Call call, Response response);
    void onFailure(Call call, IOException e);
}
