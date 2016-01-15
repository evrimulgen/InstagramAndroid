package com.emreeran.instagramclient.objects;

import org.json.JSONObject;

/**
 * Created by Emre Eran on 08/01/16.
 */
public class Photo extends Media {

    private static final String TAG = Photo.class.getSimpleName();

    public static Photo mapFromJsonObject(JSONObject jsonObject) {
        Photo photo = new Photo();
        return mapFromJsonObject(jsonObject, photo);
    }

}
