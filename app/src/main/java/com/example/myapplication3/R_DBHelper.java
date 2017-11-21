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

    public long insertRestaurantsByMethod(String name, String adress, String call, String image) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(InfoRestaurant.Restaurants.KEY_NAME, name);
        values.put(InfoRestaurant.Restaurants.KEY_ADDRESS, adress);
        values.put(InfoRestaurant.Restaurants.KEY_CALL, call);
        values.put(InfoRestaurant.Restaurants.KEY_IMAGE, image);

        return db.insert(InfoRestaurant.Restaurants.TABLE_NAME,null,values);
    }

    public Cursor getAllRestaurantsByMethod() {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(InfoRestaurant.Restaurants.TABLE_NAME,null,null,null,null,null,null);
    }

    public long deleteRestaurantsByMethod(String _id) {
        SQLiteDatabase db = getWritableDatabase();

        String whereClause = InfoRestaurant.Restaurants._ID +" = ?";
        String[] whereArgs ={_id};
        return db.delete(InfoRestaurant.Restaurants.TABLE_NAME, whereClause, whereArgs);
    }

    public long updateUserByMethod(String _id, String name, String address, String call, String image) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(InfoRestaurant.Restaurants.KEY_NAME, name);
        values.put(InfoRestaurant.Restaurants.KEY_ADDRESS, address);
        values.put(InfoRestaurant.Restaurants.KEY_CALL, call);
        values.put(InfoRestaurant.Restaurants.KEY_IMAGE, image);

        String whereClause = InfoRestaurant.Restaurants._ID +" = ?";
        String[] whereArgs ={_id};

        return db.update(InfoRestaurant.Restaurants.TABLE_NAME, values, whereClause, whereArgs);
    }


    public boolean duplicationCheck(String inputName, String inputAddress, String inputCall, String inputImage) {
        SQLiteDatabase db = getReadableDatabase();

        String query = String.format("SELECT %s, %s FROM %s WHERE %s = \"%s\" AND %s = \"%s\"",
                InfoRestaurant.Restaurants.KEY_NAME, inputName,
                InfoRestaurant.Restaurants.KEY_ADDRESS, inputAddress,
                InfoRestaurant.Restaurants.KEY_CALL, inputCall,
                InfoRestaurant.Restaurants.KEY_IMAGE, inputImage);

        Cursor result = db.rawQuery(query, null);

        Log.d("count result", String.format("%d", result.getCount()));

        if(result.getCount() > 0)
            return true;
        else
            return false;
    }

}