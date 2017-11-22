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

public class M_DBHelper extends SQLiteOpenHelper {

    public M_DBHelper(Context context) {
        super(context, InfoMenu.DB_NAME, null, InfoMenu.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(InfoMenu.Menu.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(InfoMenu.Menu.DELETE_TABLE);
        onCreate(db);
    }

    public long insertMenuByMethod(String image, String name, String price, String explain, String restaurant) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(InfoMenu.Menu.KEY_IMAGE, image);
        values.put(InfoMenu.Menu.KEY_NAME, name);
        values.put(InfoMenu.Menu.KEY_PRICE, price);
        values.put(InfoMenu.Menu.KEY_EXPLAIN, explain);
        values.put(InfoMenu.Menu.KEY_RESTAURANT, restaurant);

        return db.insert(InfoMenu.Menu.TABLE_NAME,null,values);
    }

    public long deleteMenuByMethod(String _id) {
        SQLiteDatabase db = getWritableDatabase();

        String whereClause = InfoMenu.Menu._ID +" = ?";
        String[] whereArgs ={_id};
        return db.delete(InfoMenu.Menu.TABLE_NAME, whereClause, whereArgs);
    }

    public Cursor getAllMenuByMethod() {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(InfoMenu.Menu.TABLE_NAME,null,null,null,null,null,null);
    }
}