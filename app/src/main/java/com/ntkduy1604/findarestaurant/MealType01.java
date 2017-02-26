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

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;


public class MealType01 extends AppCompatActivity {
    private String TAG = MainActivity.class.getSimpleName();

    private String mLatitude = "abc";
    private String mLongitude = "def";

    private ProgressDialog progressDialog;

    /**
     * Google API key
     */
    private static String google_api_key = "AIzaSyDvKEyT_x8m26ErkqMtppH3tIpqRXm1JOc";
    /**
     * Foursquare API key
     */
    private static String foursquare_client_id = "ZV1AQM0OCEU40QLOIT5AUQ0NGUGOUHWN3JXWGU2CVCQAFP14";
    private static String foursquare_client_secret = "DLJO4UP2NHIRNC4YNQRSNQZ0AZZBAFWGYY0AAYGX2553GERQ";

    private static String locationString = "Dallas+,Texas";

    // URL to get contacts JSON
    private static String googleUrl = "https://maps.googleapis.com/maps/api/geocode/json?address="
            + locationString;

    private static String foursquareUrl;

    //Create a ListView variable
    private ListView listView;

    //Add array of Words To MealType01
    ArrayList<Word> words;
    private Geocode abc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        words = new ArrayList<Word>();
        listView = (ListView) findViewById(R.id.word_list);

        new GetContacts().execute();
    }

    /**
     * Async task class to wait for other tasks to be finished
     */
    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            abc = new Geocode(googleUrl);
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            mLatitude = abc.getLatitude();
            mLongitude = abc.getLongitude();
            words.add(new Word(mLatitude, mLongitude, R.drawable.number_nine));

            foursquareUrl = "https://api.foursquare.com/v2/venues/search?client_id="
                    + foursquare_client_id
                    + "&client_secret=" + foursquare_client_secret
                    + "&v=20130815&ll=" + mLatitude + "," + mLongitude
                    + "&query=" + getString(R.string.meal_name_01);
            Log.e(TAG, foursquareUrl);

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            // Create an ArrayAdapter variable with String data type
            WordAdapter itemsAdapter = new WordAdapter(MealType01.this, words, R.color.meal_type_01);
            listView.setAdapter(itemsAdapter);
        }
    }
}
