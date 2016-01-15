package com.emreeran.instagramclient.controllers;

import android.content.Context;

/**
 * Created by Emre Eran on 12/01/16.
 */
public class RelationshipController extends BaseController {
    public RelationshipController(Context context) {
        super(context);
    }

//    https://api.instagram.com/v1/users/self/follows?access_token=ACCESS-TOKEN
    public void getFollowed() {

    }

//    https://api.instagram.com/v1/users/self/followed-by?access_token=ACCESS-TOKEN
    public void getFollowers() {

    }

//    https://api.instagram.com/v1/users/self/requested-by?access_token=ACCESS-TOKEN
    public void getRequests() {

    }

//    https://api.instagram.com/v1/users/{user-id}/relationship?access_token=ACCESS-TOKEN
    public void getRelationship(int userId) {

    }


//    PARAMETERS
//    ACCESS_TOKEN	A valid access token.
//    ACTION	follow | unfollow | approve | ignore
//    https://api.instagram.com/v1/users/{user-id}/relationship?access_token=ACCESS-TOKEN
    public void setRelationship(int userId, int relationship) {

    }
}
