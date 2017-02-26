package com.ntkduy1604.findarestaurant;

/**
 * Created by NTKDUY on 2/26/2017
 * for PIGGY HOUSE
 * you can contact me at: ntkduy1604@gmail.com
 */

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * This class inherits all the properties of ArrayAdapter
 * but it will extend ArrayAdapter class to support double TextView
 * for a particular data type Word (custom class)
 */
public class WordAdapter extends ArrayAdapter<Word> {

    private int mImageResourceId;

    public WordAdapter (Activity context, ArrayList<Word> word, int vImageResourceId){
        super(context, 0, word);
        mImageResourceId = vImageResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        Word currentWord = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        // Get the version name from the current Word object and
        // set this text on the name TextView
        defaultTextView.setText(currentWord.getDefaultTranslation());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView miworkTextView = (TextView) listItemView.findViewById(R.id.miwok_list_view);
        // Get the version number from the current Word object and
        // set this text on the number TextView
        miworkTextView.setText(currentWord.getMiwokTranslation());

        // Find the ImageView in the list_item.xml layout with the ID image.
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image_view);
        // Check if an image is provided for this word or not
        if (currentWord.hasImage()) {
            // If an image is available, display the provided image based on the resource ID
            imageView.setImageResource(currentWord.getImageResourceId());
            // Make sure the view is visible
            imageView.setVisibility(View.VISIBLE);
        } else {
            // Otherwise hide the ImageView (set visibility to GONE)
            imageView.setVisibility(View.GONE);
        }

        View textContainer = (View) listItemView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(), mImageResourceId);
        textContainer.setBackgroundColor(color);

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }

}
