package com.emreeran.instagramclient.objects;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Emre Eran on 08/01/16.
 */
public class MediaList {
    private static final String TAG = MediaList.class.getSimpleName();
    private static final String JSON_DATA = "data";
    private static final String JSON_TYPE = "type";
    private static final String TYPE_IMAGE = "image";
    private static final String TYPE_VIDEO = "video";

    ArrayList<Media> mList;

    public MediaList(JSONObject jsonObject) {
        try {
            JSONArray data = jsonObject.getJSONArray(JSON_DATA);
            for (int i = 0; i < data.length(); i++) {
                JSONObject item = data.getJSONObject(i);
                String type = item.getString(JSON_TYPE);
                Media media = null;
                if (type.equals(TYPE_IMAGE)) {
                    media = Photo.mapFromJsonObject(item);
                } else if (type.equals(TYPE_VIDEO)) {
                    media = Video.mapFromJsonObject(item);
                }
                mList.add(media);
            }
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }

    public ArrayList<Media> getList() {
        return mList;
    }

    public void setList(ArrayList<Media> list) {
        mList = list;
    }
}
