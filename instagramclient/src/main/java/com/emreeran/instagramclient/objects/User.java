package com.emreeran.instagramclient.objects;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Emre Eran on 08/01/16.
 */
public class User extends InstagramObject {

    private static final String TAG = User.class.getSimpleName();

    private static final String JSON_USERNAME = "username";
    private static final String JSON_PROFILE_PICTURE = "profile_picture";
    private static final String JSON_BIO = "bio";
    private static final String JSON_WEBSITE = "website";
    private static final String JSON_COUNTS = "counts";
    private static final String JSON_COUNTS_MEDIA = "media";
    private static final String JSON_COUNTS_FOLLOWER = "followed_by";
    private static final String JSON_COUNTS_FOLLOWED = "follows";

    private String mUsername;
    private String mFullName;
    private String mProfilePictureUrl;
    private String mBio;
    private String mWebsiteUrl;
    private int mPostCount;
    private int mFollowerCount;
    private int mFollowedCount;

    public User() {
    }

    public User(JSONObject jsonObject) {
        try {
            if (jsonObject.has(JSON_DATA)) {
                JSONObject data = jsonObject.getJSONObject(JSON_DATA);
                setFromJsonObject(data);
            } else {
                setFromJsonObject(jsonObject);
            }
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }

    public User(String id, String username, String fullName, String profilePictureUrl, String bio, String websiteUrl, int postCount,
                int followerCount, int followedCount) {
        mId = id;
        mUsername = username;
        mFullName = fullName;
        mProfilePictureUrl = profilePictureUrl;
        mBio = bio;
        mWebsiteUrl = websiteUrl;
        mPostCount = postCount;
        mFollowerCount = followerCount;
        mFollowedCount = followedCount;
    }

    private void setFromJsonObject(JSONObject data) {
        try {
            setId(data.getString(JSON_ID));
            setUsername(data.getString(JSON_USERNAME));
            if (data.has(JSON_PROFILE_PICTURE)) {
                setProfilePictureUrl(data.getString(JSON_PROFILE_PICTURE));
            }

            if (data.has(JSON_BIO)) {
                setBio(data.getString(JSON_BIO));
            }

            if (data.has(JSON_WEBSITE)) {
                setWebsiteUrl(data.getString(JSON_WEBSITE));
            }

            if (data.has(JSON_COUNTS)) {
                JSONObject counts = data.getJSONObject(JSON_COUNTS);
                setPostCount(counts.getInt(JSON_COUNTS_MEDIA));
                setFollowerCount(counts.getInt(JSON_COUNTS_FOLLOWER));
                setFollowedCount(counts.getInt(JSON_COUNTS_FOLLOWED));
            }
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getFullName() {
        return mFullName;
    }

    public void setFullName(String fullName) {
        mFullName = fullName;
    }

    public String getProfilePictureUrl() {
        return mProfilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        mProfilePictureUrl = profilePictureUrl;
    }

    public String getBio() {
        return mBio;
    }

    public void setBio(String bio) {
        mBio = bio;
    }

    public String getWebsiteUrl() {
        return mWebsiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        mWebsiteUrl = websiteUrl;
    }

    public int getPostCount() {
        return mPostCount;
    }

    public void setPostCount(int postCount) {
        mPostCount = postCount;
    }

    public int getFollowerCount() {
        return mFollowerCount;
    }

    public void setFollowerCount(int followerCount) {
        mFollowerCount = followerCount;
    }

    public int getFollowedCount() {
        return mFollowedCount;
    }

    public void setFollowedCount(int followedCount) {
        mFollowedCount = followedCount;
    }
}
