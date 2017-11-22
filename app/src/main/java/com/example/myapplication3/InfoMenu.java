package com.example.myapplication3;

import android.provider.BaseColumns;

public class InfoMenu {
    public static final String DB_NAME          = "menu.db";
    public static final int DATABASE_VERSION    = 1;
    private static final String TEXT_TYPE       = " TEXT";
    private static final String COMMA_SEP       = ",";

    private InfoMenu() {}

    public static class Menu implements BaseColumns {
        public static final String TABLE_NAME   = "Menu";
        public static final String KEY_RESTAURANT = "Restaurant";
        public static final String KEY_NAME     = "Name";
        public static final String KEY_PRICE    = "Price";
        public static final String KEY_EXPLAIN    = "Explain";
        public static final String KEY_IMAGE    = "Image";

        public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
                KEY_RESTAURANT + TEXT_TYPE + COMMA_SEP +
                KEY_NAME + TEXT_TYPE + COMMA_SEP +
                KEY_PRICE + TEXT_TYPE + COMMA_SEP +
                KEY_EXPLAIN + TEXT_TYPE + COMMA_SEP +
                KEY_IMAGE + TEXT_TYPE +" )";
        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}