package com.emreeran.instagramclient.objects;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Emre Eran on 08/01/16.
 */
public class MediaItem {

    private static final String TAG = MediaItem.class.getSimpleName();

    public static final int RESOLUTION_STANDARD = 0;
    public static final int RESOLUTION_LOW = 1;
    public static final int RESOLUTION_THUMBNAIL = 2;

    public static final String JSON_STANDARD = "standard_resolution";
    public static final String JSON_LOW = "low_resoultion";
    public static final String JSON_THUMBNAIL = "thumbnail";

    private static final String JSON_URL = "url";
    private static final String JSON_WIDTH = "width";
    private static final String JSON_HEIGHT = "height";

    private String mUrl;
    private int mWidth;
    private int mHeight;
    private int mType;

    public MediaItem() {
    }

    public MediaItem(String url, int width, int height, int type) {
        mUrl = url;
        mWidth = width;
        mHeight = height;
        mType = type;
    }

    public static MediaItem mapFromJsonObject(JSONObject jsonObject, int type) {
        MediaItem mediaItem = new MediaItem();

        try {
            mediaItem.setUrl(jsonObject.getString(JSON_URL));
            mediaItem.setWidth(jsonObject.getInt(JSON_WIDTH));
            mediaItem.setHeight(jsonObject.getInt(JSON_HEIGHT));
            mediaItem.setType(type);
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage(), e);
        }
        return mediaItem;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public int getWidth() {
        return mWidth;
    }

    public void setWidth(int width) {
        mWidth = width;
    }

    public int getHeight() {
        return mHeight;
    }

    public void setHeight(int height) {
        mHeight = height;
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        mType = type;
    }
}
