package com.ntkduy1604.findarestaurant;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MealType01 extends AppCompatActivity {
    private String TAG = MainActivity.class.getSimpleName();

    private ProgressDialog progressDialog;

    private static String google_api_key = "AIzaSyDvKEyT_x8m26ErkqMtppH3tIpqRXm1JOc";

    private static String locationString = "Dallas+,Texas";

    // URL to get contacts JSON
    private static String url = "https://maps.googleapis.com/maps/api/geocode/json?address="
            + locationString
            + "&key="
            + google_api_key;

    //Create a ListView variable
    private ListView listView;

    //Add array of Words To MealType01
    ArrayList<Word> words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        words = new ArrayList<Word>();

        listView = (ListView) findViewById(R.id.word_list);

        new GetContacts().execute();
    }

    /**
     * Async task class to get json by making HTTP call
     */
    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            progressDialog = new ProgressDialog(MealType01.this);
            progressDialog.setMessage("Please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler httpHandler = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = httpHandler.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

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

                    String lat = locationJsonObj.getString("lat");
                    String lng = locationJsonObj.getString("lng");

                    words.add(new Word(lat, lng, R.drawable.number_nine));

                } catch (final JSONException jsonException) {
                    Log.e(TAG, "Json parsing error: " + jsonException.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + jsonException.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });
                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (progressDialog.isShowing())
                progressDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            // Create an ArrayAdapter variable with String data type
            WordAdapter itemsAdapter = new WordAdapter(MealType01.this, words, R.color.meal_type_01);
            listView.setAdapter(itemsAdapter);
        }
    }
}
