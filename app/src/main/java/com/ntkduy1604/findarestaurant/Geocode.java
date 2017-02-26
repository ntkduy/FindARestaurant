package com.ntkduy1604.findarestaurant;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by NTKDUY on 2/27/2017
 * for PIGGY HOUSE
 * you can contact me at: ntkduy1604@gmail.com
 */

public class Geocode {
    private String TAG = MealType01.class.getSimpleName();

    /**
     * Google API key
     */
    private static String google_api_key = "AIzaSyDvKEyT_x8m26ErkqMtppH3tIpqRXm1JOc";

    private String mUrl;
    private String mLatitude;
    private String mLongitude;

    public Geocode(String vUrl) {
        mUrl = vUrl;
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
    public class GetContacts extends AsyncTask<Void, Void, Void> {

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
                     * Get contact object
                     */
                    JSONObject jsonObject = new JSONObject(jsonStr);
                    // "results" is an array so it must be defined by JSONArray
                    JSONArray results = jsonObject.getJSONArray("results");
                    // Parse the data of the first element in "results" array
                    JSONObject firstResultsObj = results.getJSONObject(0);
                    // "geometry" is an object inside the first element of "results" array
                    JSONObject geometryJsonObj = firstResultsObj.getJSONObject("geometry");
                    // "location" is an object inside geometry
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
