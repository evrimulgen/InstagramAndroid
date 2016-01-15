package com.emreeran.instagramclient.controllers;

import android.content.Context;

/**
 * Created by Emre Eran on 12/01/16.
 */
public class CommentController extends BaseController {
    public CommentController(Context context) {
        super(context);
    }

//    https://api.instagram.com/v1/media/{media-id}}/comments?access_token=ACCESS-TOKEN
    public void getComments(int mediaId) {

    }

//    curl -F 'access_token=ACCESS-TOKEN' \
//            -F 'text=This+is+my+comment' \
//    https://api.instagram.com/v1/media/{media-id}/comments
    public void comment(int postId, String content) {

    }

//    curl -X DELETE https://api.instagram.com/v1/media/{media-id}/comments/{comment-id}?access_token=ACCESS-TOKEN
    public void removeComment(int mediaId, int commentId) {

    }
}
