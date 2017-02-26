package com.ntkduy1604.findarestaurant;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class MealType01 extends AppCompatActivity {
    //private String TAG = MainActivity.class.getSimpleName(); //For debugging purpose

    private static String locationString = "Dallas+,Texas";

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

        String mRestaurantName, mRestaurantAddress;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            abc = new Geocode(locationString);
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            String mLatitude = abc.getLatitude();
            String mLongitude = abc.getLongitude();

            // Foursquare API key
            String foursquare_client_id = "ZV1AQM0OCEU40QLOIT5AUQ0NGUGOUHWN3JXWGU2CVCQAFP14";
            String foursquare_client_secret = "DLJO4UP2NHIRNC4YNQRSNQZ0AZZBAFWGYY0AAYGX2553GERQ";

            // Create url to access Foursquare with client ID, client Secret, and longitude and latitude
            foursquareUrl = "https://api.foursquare.com/v2/venues/search?client_id="
                    + foursquare_client_id
                    + "&client_secret=" + foursquare_client_secret
                    + "&v=20130815&ll=" + mLatitude + "," + mLongitude
                    + "&query=" + getString(R.string.meal_name_01);

            HttpHandler httpHandler = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = httpHandler.makeServiceCall(foursquareUrl);

            if (jsonStr != null) {
                try {

                    JSONObject jsonObject = new JSONObject(jsonStr);
                    JSONObject response = jsonObject.getJSONObject("response");
                    JSONArray restaurantVenues = response.getJSONArray("venues");
                    JSONObject restaurantVenuesFirstElement = restaurantVenues.getJSONObject(0);

                    // Get restaurant's name
                    mRestaurantName = restaurantVenuesFirstElement.getString("name");

                    // Get restaurant's address
                    JSONObject restaurantLocation = restaurantVenuesFirstElement.getJSONObject("location");
                    JSONArray formattedAddress = restaurantLocation.getJSONArray("formattedAddress");

                    mRestaurantAddress = "";
                    for (int i = 0; i < formattedAddress.length(); i++)
                        mRestaurantAddress += formattedAddress.getString(i) + " ";
                } catch (final JSONException jsonException) {
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            words.add(new Word(mRestaurantName, mRestaurantAddress, R.drawable.number_nine));

            // Create an ArrayAdapter variable with String data type
            WordAdapter itemsAdapter = new WordAdapter(MealType01.this, words, R.color.meal_type_01);
            listView.setAdapter(itemsAdapter);
        }
    }
}
