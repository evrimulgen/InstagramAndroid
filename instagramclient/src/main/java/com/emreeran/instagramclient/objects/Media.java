package com.emreeran.instagramclient.objects;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Emre Eran on 08/01/16.
 */
public abstract class Media extends InstagramObject {

    private static final String TAG = Media.class.getSimpleName();

    private static final String JSON_USERS = "users_in_photo";
    private static final String JSON_FILTER = "filter";
    private static final String JSON_URL = "link";
    private static final String JSON_CAPTION = "caption";
    private static final String JSON_TAGS = "tags";
    private static final String JSON_COMMENTS = "comments";
    private static final String JSON_LIKES = "likes";
    private static final String JSON_COUNT = "count";
    private static final String JSON_USER = "user";
    private static final String JSON_CREATED_AT = "created_time";
    private static final String JSON_LOCATION = "location";
    private static final String JSON_IMAGES = "images";

    private int mCommentCount;
    private int mLikeCount;
    private String mCaption;
    private String mUrl;
    private ArrayList<TaggedUser> mTaggedUsers;
    private String mFilter;
    private String mCreatedAt;
    private ArrayList<String> mTags;
    private User mUser;
    private Location mLocation;
    private HashMap<Integer, MediaItem> mImages;

    public static <T extends Media> T mapFromJsonObject(JSONObject jsonObject, T object) {
        try {
            object.setUrl(jsonObject.getString(JSON_URL));
            object.setId(jsonObject.getString(JSON_ID));
            object.setCaption(jsonObject.getString(JSON_CAPTION));
            object.setCreatedAt(jsonObject.getString(JSON_CREATED_AT));
            object.setFilter(jsonObject.getString(JSON_FILTER));
            JSONObject comments = jsonObject.getJSONObject(JSON_COMMENTS);
            JSONObject likes = jsonObject.getJSONObject(JSON_LIKES);
            JSONObject location = jsonObject.getJSONObject(JSON_LOCATION);
            JSONObject user = jsonObject.getJSONObject(JSON_USER);

            object.setCommentCount(comments.getInt(JSON_COUNT));
            object.setLikeCount(likes.getInt(JSON_COUNT));
            object.setLocation(Location.mapFromJsonObject(location));
            object.setUser(new User(user));

            object.setTags((ArrayList<String>) jsonObject.get(JSON_TAGS));

            JSONArray users = jsonObject.getJSONArray(JSON_USERS);
            ArrayList<TaggedUser> userArray = new ArrayList<>();

            for (int i = 0; i < users.length(); i++) {
                JSONObject current = users.getJSONObject(i);
                TaggedUser taggedUser = TaggedUser.mapFromJsonObject(current);
                userArray.add(taggedUser);
            }

            object.setTaggedUsers(userArray);

            JSONObject images = jsonObject.getJSONObject(JSON_IMAGES);
            HashMap<Integer, MediaItem> imageMap = new HashMap<>();

            MediaItem lowResoultion = MediaItem.mapFromJsonObject(images.getJSONObject(MediaItem.JSON_LOW), MediaItem.RESOLUTION_LOW);
            MediaItem standardResolution = MediaItem.mapFromJsonObject(images.getJSONObject(MediaItem.JSON_STANDARD), MediaItem.RESOLUTION_STANDARD);
            MediaItem thumbnail = MediaItem.mapFromJsonObject(images.getJSONObject(MediaItem.JSON_THUMBNAIL), MediaItem.RESOLUTION_THUMBNAIL);
            imageMap.put(MediaItem.RESOLUTION_LOW, lowResoultion);
            imageMap.put(MediaItem.RESOLUTION_STANDARD, standardResolution);
            imageMap.put(MediaItem.RESOLUTION_THUMBNAIL, thumbnail);
            object.setImages(imageMap);
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage(), e);
        }

        return object;
    }

    public int getCommentCount() {
        return mCommentCount;
    }

    public void setCommentCount(int commentCount) {
        mCommentCount = commentCount;
    }

    public int getLikeCount() {
        return mLikeCount;
    }

    public void setLikeCount(int likeCount) {
        mLikeCount = likeCount;
    }

    public String getCaption() {
        return mCaption;
    }

    public void setCaption(String caption) {
        mCaption = caption;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public ArrayList<TaggedUser> getTaggedUsers() {
        return mTaggedUsers;
    }

    public void setTaggedUsers(ArrayList<TaggedUser> taggedUsers) {
        mTaggedUsers = taggedUsers;
    }

    public String getFilter() {
        return mFilter;
    }

    public void setFilter(String filter) {
        mFilter = filter;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public ArrayList<String> getTags() {
        return mTags;
    }

    public void setTags(ArrayList<String> tags) {
        mTags = tags;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public Location getLocation() {
        return mLocation;
    }

    public void setLocation(Location location) {
        mLocation = location;
    }

    public HashMap<Integer, MediaItem> getImages() {
        return mImages;
    }

    public void setImages(HashMap<Integer, MediaItem> images) {
        mImages = images;
    }
}
