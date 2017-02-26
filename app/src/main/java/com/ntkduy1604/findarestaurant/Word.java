package com.ntkduy1604.findarestaurant;

/**
 * Created by NTKDUY on 2/26/2017
 * for PIGGY HOUSE
 * you can contact me at: ntkduy1604@gmail.com
 */

/**
 * {@link Word} represents a vocabulary word that the user wants to learn.
 * It contains a default translation, a Miwok translation for that word and an image
 */
public class Word {
    /** Constant value that represents no image was provided for this word */
    private static final int NO_IMAGE_PROVIDED = -1;

    // Default translation for the word
    private String mDefaultTranslation;
    // Miwok translation for the word
    private String mMiwokTranslation;
    // Miwok icon imaging
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    // Constructor without image
    public Word(String vDefaultTranslation, String vMiwokTranslation) {
        mDefaultTranslation = vDefaultTranslation;
        mMiwokTranslation = vMiwokTranslation;
    }

    // Constructor with image
    public Word(String vDefaultTranslation, String vMiwokTranslation, int imageResourceId) {
        mDefaultTranslation = vDefaultTranslation;
        mMiwokTranslation = vMiwokTranslation;
        mImageResourceId = imageResourceId;
    }

    // Get the default translation of the word
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    // Get the Miwok translation of the word
    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    // Get the image resource ID
    public int getImageResourceId() {
        return mImageResourceId;
    }

    /**
     * Returns whether or not there is an image for this word.
     */
    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }
}
