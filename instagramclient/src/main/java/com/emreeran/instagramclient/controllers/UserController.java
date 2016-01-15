package com.emreeran.instagramclient.controllers;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import com.emreeran.instagramclient.Instagram;
import com.emreeran.instagramclient.objects.InstagramObject;
import com.emreeran.instagramclient.objects.MediaList;
import com.emreeran.instagramclient.objects.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Emre Eran on 08/01/16.
 */
public class UserController extends BaseController {
    private static final String TAG = UserController.class.getSimpleName();

    public UserController(Context context) {
        super(context);
    }

    public void getSelf(String token, final Instagram.UserCallback callback) {
        String url = "https://api.instagram.com/v1/users/self/?access_token=" + token;
        get(url, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.e(TAG, e.getMessage(), e);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    final User user = new User(jsonObject);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.done(user);
                        }
                    });
                } catch (JSONException e) {
                    Log.e(TAG, e.getMessage(), e);
                }
            }
        });
    }

    public void getUser(String token, String userId, final Instagram.UserCallback callback) {
        String url = "https://api.instagram.com/v1/users/" + userId + "/?access_token=" + token;
        get(url, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.e(TAG, e.getMessage(), e);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    final User user = new User(jsonObject);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.done(user);
                        }
                    });
                } catch (JSONException e) {
                    Log.e(TAG, e.getMessage(), e);
                }
            }
        });
    }


    //    PARAMETERS
//    ACCESS_TOKEN	A valid access token.
//    COUNT	Count of media to return.
//    MIN_ID	Return media later than this min_id.
//    MAX_ID	Return media earlier than this max_id.
    public void getSelfRecentPosts(String token, final Instagram.MediaListCallback callback) {
        String url = "https://api.instagram.com/v1/users/self/media/recent/?access_token=" + token;
        get(url, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.e(TAG, e.getMessage(), e);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    final MediaList mediaList = new MediaList(jsonObject);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.done(mediaList);
                        }
                    });
                } catch (JSONException e) {
                    Log.e(TAG, e.getMessage(), e);
                }
            }
        });
    }


    //    PARAMETERS
//    ACCESS_TOKEN	A valid access token.
//    COUNT	Count of media to return.
//    MIN_ID	Return media later than this min_id.
//    MAX_ID	Return media earlier than this max_id.
    public void getUserRecentPosts(String token, String userId, final Instagram.MediaListCallback callback) {
        String url = "https://api.instagram.com/v1/users/" + userId + "/media/recent/?access_token=" + token;
        get(url, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.e(TAG, e.getMessage(), e);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    final MediaList mediaList = new MediaList(jsonObject);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.done(mediaList);
                        }
                    });
                } catch (JSONException e) {
                    Log.e(TAG, e.getMessage(), e);
                }
            }
        });
    }

    public void getLikedPosts(String token, final Instagram.MediaListCallback callback) {
        getLikedPosts(token, null, callback);
    }


    //    PARAMETERS
//    ACCESS_TOKEN	A valid access token.
//    COUNT	Count of media to return.
//    MAX_LIKE_ID	Return media liked before this id.
    public void getLikedPosts(String token, @Nullable String maxLikeId, final Instagram.MediaListCallback callback) {
        String url;
        if (maxLikeId == null) {
            url = "https://api.instagram.com/v1/users/self/media/liked?access_token=" + token;
        } else {
            url = "https://api.instagram.com/v1/users/self/media/liked?max_like_id=" + maxLikeId + "&access_token=" + token;
        }

        get(url, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.e(TAG, e.getMessage(), e);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    final MediaList mediaList = new MediaList(jsonObject);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.done(mediaList);
                        }
                    });
                } catch (JSONException e) {
                    Log.e(TAG, e.getMessage(), e);
                }
            }
        });
    }


    public void searchUser(String token, String phrase, final Instagram.UserListCallback callback) {
        searchUser(token, phrase, null, callback);
    }

    //    PARAMETERS
//    ACCESS_TOKEN	A valid access token.
//    Q	A query string.
//    COUNT
    public void searchUser(String token, String phrase, @Nullable Integer count, final Instagram.UserListCallback callback) {
        String url;
        if (count == null) {
            url = "https://api.instagram.com/v1/users/search?q=" + phrase + "&access_token=" + token;
        } else {
            url = "https://api.instagram.com/v1/users/search?q=" + phrase + "&count=" + count + "&access_token=" + token;
        }

        get(url, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.e(TAG, e.getMessage(), e);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    Log.d(TAG, jsonObject.toString());
                    int responseCode = jsonObject.getJSONObject(InstagramObject.JSON_META).getInt(InstagramObject.JSON_CODE);
                    if (responseCode == 200) {
                        final ArrayList<User> userList = new ArrayList<>();
                        JSONArray data = jsonObject.getJSONArray(InstagramObject.JSON_DATA);
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject userObject = data.getJSONObject(i);
                            User user = new User(userObject);
                            userList.add(user);
                        }
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                callback.done(userList);
                            }
                        });
                    }
                } catch (JSONException e) {
                    Log.e(TAG, e.getMessage(), e);
                }
            }
        });
    }
}
