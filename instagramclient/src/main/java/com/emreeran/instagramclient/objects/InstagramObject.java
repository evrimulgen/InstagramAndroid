package com.emreeran.instagramclient.objects;

/**
 * Created by Emre Eran on 08/01/16.
 */
public abstract class InstagramObject {
    public static final String JSON_DATA = "data";
    public static final String JSON_META = "meta";
    public static final String JSON_CODE = "code";
    protected static final String JSON_ID = "id";

    protected String mId;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }
}
