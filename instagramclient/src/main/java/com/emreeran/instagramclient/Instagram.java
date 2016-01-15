package com.emreeran.instagramclient;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.emreeran.instagramclient.controllers.MediaController;
import com.emreeran.instagramclient.controllers.UserController;
import com.emreeran.instagramclient.objects.MediaList;
import com.emreeran.instagramclient.objects.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Instagram interface
 * TODO: write docs
 * Created by Emre Eran on 07/01/16.
 */
public class Instagram {

    private static final String TAG = Instagram.class.getSimpleName();

    private static final String META_ID = "com.emreeran.instagramclient.CLIENT_ID";
    private static final String META_SECRET = "com.emreeran.instagramclient.CLIENT_SECRET";
    private static final String META_URL = "com.emreeran.instagramclient.REDIRECT_URL";

    private static final String PREFS_NAME = "instagram";
    private static final String PREFS_TOKEN = "access_token";

    private static final String ACCESS_TOKEN_URL = "https://api.instagram.com/oauth/access_token";

    private String mClientId, mClientSecret, mRedirectUrl;

    private Context mContext;

    @SuppressWarnings("unused") // Public api
    public Instagram(Context context) {
        mContext = context;
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            // TODO: can return null, handle
            mClientId = ai.metaData.getString(META_ID);
            mClientSecret = ai.metaData.getString(META_SECRET);
            mRedirectUrl = ai.metaData.getString(META_URL);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }

    @SuppressWarnings("unused") // Public api
    public Instagram(Context context, String clientId, String clientSecret, String redirectUrl) {
        mContext = context;
        mClientId = clientId;
        mClientSecret = clientSecret;
        mRedirectUrl = redirectUrl;
    }

    @SuppressWarnings("unused") // Public api
    public void login() {
        InstagramLoginDialog dialog = new InstagramLoginDialog(mContext, mClientId, mRedirectUrl,
                new InstagramLoginDialog.InstagramClientListener() {
                    @Override
                    public void onSuccess(String code) {
                        fetchAccessTokenWithCode(code);
                    }

                    @Override
                    public void onError(String error) {
                        Log.e(TAG, error);
                    }
                });
        dialog.show();
    }

    @SuppressWarnings("unused") // Public api
    public void getSelf(UserCallback callback) {
        UserController controller = new UserController(mContext);
        String token = getAccessToken();
        if (token != null) {
            controller.getSelf(token, callback);
        } else {
            login();
        }
    }

    @SuppressWarnings("unused") // Public api
    public void getUser(String userId, UserCallback callback) {
        UserController controller = new UserController(mContext);
        String token = getAccessToken();
        if (token != null) {
            controller.getUser(token, userId, callback);
        } else {
            // TODO: handle
        }
    }

    @SuppressWarnings("unused") // Public api
    public void getSelfRecentPosts(MediaListCallback callback) {
        UserController controller = new UserController(mContext);
        String token = getAccessToken();
        if (token != null) {
            controller.getSelfRecentPosts(token, callback);
        } else {
            // TODO: handle
        }
    }

    @SuppressWarnings("unused") // Public api
    public void getUserRecentPosts(String userId, MediaListCallback callback) {
        UserController controller = new UserController(mContext);
        String token = getAccessToken();
        if (token != null) {
            controller.getUserRecentPosts(token, userId, callback);
        } else {
            // TODO: handle
        }
    }

    @SuppressWarnings("unused") // Public api
    public void getLikedPosts(MediaListCallback callback) {
        UserController controller = new UserController(mContext);
        String token = getAccessToken();
        if (token != null) {
            controller.getLikedPosts(token, callback);
        } else {
            // TODO: handle
        }
    }

    @SuppressWarnings("unused") // Public api
    public void searchUser(String phrase, UserListCallback callback) {
        UserController controller = new UserController(mContext);
        String token = getAccessToken();
        if (token != null) {
            controller.searchUser(token, phrase, callback);
        } else {
            // TODO: handle
        }
    }

    public void getMediaById() {

    }

    public void getMediaByShortCode(String shortCode) {
        MediaController controller = new MediaController(mContext);
        String token = getAccessToken();
        if (token != null) {
            
        } else {
            // TODO: handle
        }
    }

    public interface UserCallback {
        void done(User user);
    }

    public interface UserListCallback {
        void done(ArrayList<User> users);
    }

    public interface MediaListCallback {
        void done(MediaList list);
    }

    //------- TODO: move this block somewhere else

    private void fetchAccessTokenWithCode(String code) {
        RequestBody body = new FormBody.Builder()
                .add("client_id", mClientId)
                .add("client_secret", mClientSecret)
                .add("grant_type", "authorization_code")
                .add("redirect_uri", mRedirectUrl)
                .add("code", code).build();

        post(ACCESS_TOKEN_URL, body, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.e(TAG, e.getMessage(), e);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String responseString = response.body().string();
                try {
                    JSONObject responseJson = new JSONObject(responseString);
                    saveAccessToken(responseJson.getString("access_token"));
                } catch (JSONException e) {
                    Log.e(TAG, e.getMessage(), e);
                }
            }
        });
    }

    private void post(String url, RequestBody body, Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).post(body).build();
        client.newCall(request).enqueue(callback);
    }

    private void saveAccessToken(String token) {
        SharedPreferences instagramPreferences = mContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        instagramPreferences.edit().putString(PREFS_TOKEN, token).apply();
    }

    private String getAccessToken() {
        SharedPreferences instagramPreferences = mContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return instagramPreferences.getString(PREFS_TOKEN, null);
    }
}
