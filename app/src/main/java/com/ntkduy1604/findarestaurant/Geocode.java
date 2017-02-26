package com.ntkduy1604.findarestaurant;

import android.os.AsyncTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by NTKDUY on 2/27/2017
 * for PIGGY HOUSE
 * you can contact me at: ntkduy1604@gmail.com
 */

public class Geocode {
    private String TAG = MealType01.class.getSimpleName();


    //Google API key
    private static String google_api_key = "AIzaSyDvKEyT_x8m26ErkqMtppH3tIpqRXm1JOc";

    private String mUrl;
    private String mLatitude;
    private String mLongitude;

    public Geocode(String locationString) {
        String googleUrl = "https://maps.googleapis.com/maps/api/geocode/json?address=";
        mUrl = googleUrl + locationString;
        // Create url with Google API key
        mUrl += "&key=" + google_api_key;
        new GetContacts().execute();
    }

    public String getLatitude() {
        return mLatitude;
    }

    public String getLongitude() {
        return mLongitude;
    }

    /**
     * Async task class to get json by making HTTP call
     */
    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler httpHandler = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = httpHandler.makeServiceCall(mUrl);

            if (jsonStr != null) {
                try {
                    /**
                     * Get latitude & longitude from geocode Google
                     */
                    JSONObject jsonObject = new JSONObject(jsonStr);
                    JSONArray results = jsonObject.getJSONArray("results");
                    JSONObject firstResultsObj = results.getJSONObject(0);
                    JSONObject geometryJsonObj = firstResultsObj.getJSONObject("geometry");
                    JSONObject locationJsonObj = geometryJsonObj.getJSONObject("location");

                    mLatitude = locationJsonObj.getString("lat");
                    mLongitude = locationJsonObj.getString("lng");

                } catch (final JSONException jsonException) {
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }
    }
}
