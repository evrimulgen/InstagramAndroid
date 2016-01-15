package com.emreeran.instagramclient.controllers;

import android.content.Context;

/**
 * Created by Emre Eran on 12/01/16.
 */
public class TagController extends BaseController{
    public TagController(Context context) {
        super(context);
    }

//    https://api.instagram.com/v1/tags/{tag-name}?access_token=ACCESS-TOKEN
    public void getTagInfo(String tagName) {

    }


//    PARAMETERS:
//    ACCESS_TOKEN	A valid access token.
//    COUNT	Count of tagged media to return.
//    MIN_TAG_ID	Return media before this min_tag_id.
//    MAX_TAG_ID	Return media after this max_tag_id.
//    https://api.instagram.com/v1/tags/{tag-name}/media/recent?access_token=ACCESS-TOKEN
    public void getRecentlyTagged(String tagName) {

    }

//    Without a leading #
//    https://api.instagram.com/v1/tags/search?q=PHRASE&access_token=ACCESS-TOKEN
    public void searchTagsByName(String phrase) {

    }
}
