package com.emreeran.instagramclient.controllers;

import android.content.Context;

/**
 * Created by Emre Eran on 12/01/16.
 */
public class LikeController extends BaseController {
    public LikeController(Context context) {
        super(context);
    }

//    https://api.instagram.com/v1/media/{media-id}/likes?access_token=ACCESS-TOKEN
    public void getMediaLikes(int mediaId) {

    }

//    curl -F 'access_token=ACCESS-TOKEN' \
//    https://api.instagram.com/v1/media/{media-id}/likes
    public void likeMedia(int mediaId) {

    }

//    curl -X DELETE https://api.instagram.com/v1/media/{media-id}/likes?access_token=ACCESS-TOKEN
    public void unlikeMedia(int mediaId) {

    }
}
