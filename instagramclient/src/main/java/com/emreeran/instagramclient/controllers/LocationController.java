package com.emreeran.instagramclient.controllers;

import android.content.Context;

/**
 * Created by Emre Eran on 12/01/16.
 */
public class LocationController extends BaseController {
    public LocationController(Context context) {
        super(context);
    }

    //    https://api.instagram.com/v1/locations/{location-id}?access_token=ACCESS-TOKEN
    public void getLocation(int locationId) {

    }

    //    Parameters:
//    ACCESS_TOKEN	A valid access token.
//    MIN_ID	Return media before this min_id.
//    MAX_ID	Return media after this max_id.
//    https://api.instagram.com/v1/locations/{location-id}/media/recent?access_token=ACCESS-TOKEN
    public void getRecentsPostsInLocation(int locationId) {

    }

    //    Parameters:
//    DISTANCE	Default is 1000m (distance=1000), max distance is 5000.
//    FACEBOOK_PLACES_ID	Returns a location mapped off of a Facebook places id. If used, a Foursquare id and lat, lng are not required.
//    ACCESS_TOKEN	A valid access token.
//    FOURSQUARE_ID	Returns a location mapped off of a foursquare v1 api location id. If used, you are not required to use lat and lng.
//       Note that this method is deprecated; you should use the new foursquare IDs with V2 of their API.
//    LAT	Latitude of the center search coordinate. If used, lng is required.
//    LNG	Longitude of the center search coordinate. If used, lat is required.
//    FOURSQUARE_V2_ID	Returns a location mapped off of a foursquare v2 api location id. If used, you are not required to use lat and lng.
//    https://api.instagram.com/v1/locations/search?lat=48.858844&lng=2.294351&access_token=ACCESS-TOKEN
    public void searchLocationByCoordinates(long latitude, long longitude) {

    }
}
