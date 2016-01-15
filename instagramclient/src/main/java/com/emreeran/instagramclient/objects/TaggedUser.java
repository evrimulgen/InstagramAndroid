package com.emreeran.instagramclient.objects;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Emre Eran on 08/01/16.
 */
public class TaggedUser {
    private static final String TAG = TaggedUser.class.getSimpleName();

    private static final String JSON_USER = "user";
    private static final String JSON_POSITION = "position";

    private User mUser;
    private Position mPosition;

    public static TaggedUser mapFromJsonObject(JSONObject jsonObject) {
        TaggedUser taggedUser = new TaggedUser();

        try {
            taggedUser.setPosition(Position.mapFromJsonObject(jsonObject.getJSONObject(JSON_POSITION)));
            taggedUser.setUser(new User(jsonObject.getJSONObject(JSON_USER)));
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage(), e);
        }

        return taggedUser;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public Position getPosition() {
        return mPosition;
    }

    public void setPosition(Position position) {
        mPosition = position;
    }

    public static class Position {
        private static final String JSON_X = "x";
        private static final String JSON_Y = "y";

        private int mX;
        private int mY;

        public static Position mapFromJsonObject(JSONObject jsonObject) {
            Position position = new Position();

            try {
                position.setX(jsonObject.getInt(JSON_X));
                position.setY(jsonObject.getInt(JSON_Y));
            } catch (JSONException e) {
                Log.e(TAG, e.getMessage(), e);
            }

            return  position;
        }

        public int getX() {
            return mX;
        }

        public void setX(int x) {
            mX = x;
        }

        public int getY() {
            return mY;
        }

        public void setY(int y) {
            mY = y;
        }
    }
}
