package com.example.myapplication3;

import android.provider.BaseColumns;

/**
 * Created by B10730 on 2017-11-21.
 */

public class InfoRestaurant {
    public static final String DB_NAME          = "restaurants.db";
    public static final int DATABASE_VERSION    = 1;
    private static final String TEXT_TYPE       = " TEXT";
    private static final String COMMA_SEP       = ",";

    private InfoRestaurant() {}

    public static class Restaurants implements BaseColumns {
        public static final String TABLE_NAME   = "Restaurants";
        public static final String KEY_NAME     = "Name";
        public static final String KEY_ADDRESS    = "Address";
        public static final String KEY_CALL    = "Call";
        public static final String KEY_IMAGE    = "Image";

        public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
                KEY_NAME + TEXT_TYPE + COMMA_SEP +
                KEY_ADDRESS + TEXT_TYPE + COMMA_SEP +
                KEY_CALL + TEXT_TYPE + COMMA_SEP +
                KEY_IMAGE + TEXT_TYPE +" )";
        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}