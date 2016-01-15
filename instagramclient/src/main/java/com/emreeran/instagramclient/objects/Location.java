package com.emreeran.instagramclient.objects;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Emre Eran on 08/01/16.
 */
public class Location extends InstagramObject {
    private static final String TAG = Location.class.getSimpleName();

    private static final String JSON_LATITUDE = "latitude";
    private static final String JSON_LONGITUDE = "longitude";
    private static final String JSON_NAME = "name";

    long mLongitude;
    long mLatitude;
    String mName;

    public static Location mapFromJsonObject(JSONObject jsonObject) {
        Location location = new Location();

        try {
            location.setLatitude(jsonObject.getLong(JSON_LATITUDE));
            location.setLongitude(jsonObject.getLong(JSON_LONGITUDE));
            location.setName(jsonObject.getString(JSON_NAME));
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage(), e);
        }

        return location;
    }

    public long getLongitude() {
        return mLongitude;
    }

    public void setLongitude(long longitude) {
        mLongitude = longitude;
    }

    public long getLatitude() {
        return mLatitude;
    }

    public void setLatitude(long latitude) {
        mLatitude = latitude;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
