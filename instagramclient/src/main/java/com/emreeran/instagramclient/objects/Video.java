package com.emreeran.instagramclient.objects;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Emre Eran on 08/01/16.
 */
public class Video extends Media {
    private static final String TAG = Video.class.getSimpleName();

    private static final String JSON_VIDEOS = "videos";

    private HashMap<Integer, MediaItem> mVideos;

    public static Video mapFromJsonObject(JSONObject jsonObject) {
        Video video = new Video();
        video = mapFromJsonObject(jsonObject, video);

        try {
            JSONObject videos = jsonObject.getJSONObject(JSON_VIDEOS);
            HashMap<Integer, MediaItem> videoMap = new HashMap<>();

            MediaItem lowResoultion = MediaItem.mapFromJsonObject(videos.getJSONObject(MediaItem.JSON_LOW), MediaItem.RESOLUTION_LOW);
            MediaItem standardResolution = MediaItem.mapFromJsonObject(videos.getJSONObject(MediaItem.JSON_STANDARD), MediaItem.RESOLUTION_STANDARD);
            videoMap.put(MediaItem.RESOLUTION_LOW, lowResoultion);
            videoMap.put(MediaItem.RESOLUTION_STANDARD, standardResolution);
            video.setVideos(videoMap);
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage(), e);
        }

        return video;
    }

    public HashMap<Integer, MediaItem> getVideos() {
        return mVideos;
    }

    public void setVideos(HashMap<Integer, MediaItem> videos) {
        mVideos = videos;
    }
}
