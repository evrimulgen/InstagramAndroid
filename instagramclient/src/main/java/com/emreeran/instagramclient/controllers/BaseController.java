package com.emreeran.instagramclient.controllers;

import android.content.Context;
import android.os.Handler;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Emre Eran on 08/01/16.
 */
public abstract class BaseController {
    protected Handler mHandler;

    public BaseController(Context context) {
        mHandler = new Handler(context.getMainLooper());
    }

    protected void get(String url, Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(callback);
    }
}
