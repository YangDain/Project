package com.example.myapplication3;
        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.util.Log;

/**
 * 권혁동 튜터님 자료 참고
 */

public class R_DBHelper extends SQLiteOpenHelper {

    public R_DBHelper(Context context) {
        super(context, InfoRestaurant.DB_NAME, null, InfoRestaurant.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(InfoRestaurant.Restaurants.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(InfoRestaurant.Restaurants.DELETE_TABLE);
        onCreate(db);
    }

    public long insertRestaurantsByMethod(String name, String address, String call, String image) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(InfoRestaurant.Restaurants.KEY_NAME, name);
        values.put(InfoRestaurant.Restaurants.KEY_ADDRESS, address);
        values.put(InfoRestaurant.Restaurants.KEY_CALL, call);
        values.put(InfoRestaurant.Restaurants.KEY_IMAGE, image);

        return db.insert(InfoRestaurant.Restaurants.TABLE_NAME,null,values);
    }

    public Cursor getAllRestaurantsByMethod() {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(InfoRestaurant.Restaurants.TABLE_NAME,null,null,null,null,null,null);
    }

    public Cursor getAllRestaurants() {
        SQLiteDatabase db = getReadableDatabase();
        String [] projection = {
                InfoRestaurant.Restaurants._ID,
                InfoRestaurant.Restaurants.KEY_NAME,
                InfoRestaurant.Restaurants.KEY_ADDRESS,
                InfoRestaurant.Restaurants.KEY_CALL,
                InfoRestaurant.Restaurants.KEY_IMAGE
        };
        return db.query(InfoRestaurant.Restaurants.TABLE_NAME,  // 테이블이름
                projection,         // 프로젝션
                null,
                null,
                null,
                null,
                null);
    }

    public long deleteRestaurantByMethod(String _id) {
        SQLiteDatabase db = getWritableDatabase();

        String whereClause = InfoRestaurant.Restaurants._ID +" = ?";
        String[] whereArgs ={_id};
        return db.delete(InfoRestaurant.Restaurants.TABLE_NAME, whereClause, whereArgs);
    }

    public Cursor getRestaurantIDByName(String name){
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {InfoRestaurant.Restaurants._ID};
        String selection= InfoRestaurant.Restaurants.KEY_NAME + "=?";    //물어보기
        String[] selectionArgs ={name};
        return db.query(InfoRestaurant.Restaurants.TABLE_NAME,projection,selection,selectionArgs,null,null,null);
    }

}