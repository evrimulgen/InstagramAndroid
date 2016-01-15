package com.emreeran.instagramclient.controllers;

import android.content.Context;
import android.util.Log;

import com.emreeran.instagramclient.objects.Media;
import com.emreeran.instagramclient.objects.MediaItem;
import com.emreeran.instagramclient.objects.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Emre Eran on 11/01/16.
 */
public class MediaController extends BaseController {
    private static final String TAG = MediaController.class.getSimpleName();

    public MediaController(Context context) {
        super(context);
    }

    public void getMediaById(String token, int mediaId) {
        String url = "https://api.instagram.com/v1/media/" + mediaId + "?access_token=" + token;
        get(url, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.e(TAG, e.getMessage(), e);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    // TODO find if photo or video
                    Log.d(TAG, jsonObject.toString());

                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                        }
                    });
                } catch (JSONException e) {
                    Log.e(TAG, e.getMessage(), e);
                }
            }
        });
    }

    public void getMediaByShortCode(String shortCode) {

    }


    //    PARAMETERS:
//    ACCESS_TOKEN	A valid access token.
//    LAT	Latitude of the center search coordinate. If used, lng is required.
//    LNG	Longitude of the center search coordinate. If used, lat is required.
//    DISTANCE	Default is 1km (distance=1000), max distance is 5km.
    public void searchByArea(long latitude, long longitude) {

    }
}
