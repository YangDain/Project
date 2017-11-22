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

    public long insertMenuByMethod(String restaurant, String name, String price, String explain, String image) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(InfoMenu.Menu.KEY_RESTAURANT, restaurant);
        values.put(InfoMenu.Menu.KEY_NAME, name);
        values.put(InfoMenu.Menu.KEY_PRICE, price);
        values.put(InfoMenu.Menu.KEY_EXPLAIN, explain);
        values.put(InfoMenu.Menu.KEY_IMAGE, image);

        return db.insert(InfoMenu.Menu.TABLE_NAME,null,values);
    }

    public Cursor getAllMenuByMethod() {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(InfoMenu.Menu.TABLE_NAME,null,null,null,null,null,null);
    }

    public long deleteMenuByMethod(String _id) {
        SQLiteDatabase db = getWritableDatabase();

        String whereClause = InfoMenu.Menu._ID +" = ?";
        String[] whereArgs ={_id};
        return db.delete(InfoMenu.Menu.TABLE_NAME, whereClause, whereArgs);
    }

    public long updateMenuByMethod(String _id, String name, String price, String explain, String image) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(InfoMenu.Menu.KEY_NAME, name);
        values.put(InfoMenu.Menu.KEY_PRICE, price);
        values.put(InfoMenu.Menu.KEY_EXPLAIN, explain);
        values.put(InfoMenu.Menu.KEY_IMAGE, image);

        String whereClause = InfoMenu.Menu._ID +" = ?";
        String[] whereArgs ={_id};

        return db.update(InfoMenu.Menu.TABLE_NAME, values, whereClause, whereArgs);
    }


    public boolean duplicationCheck(String inputName, String inputPrice, String inputExplain, String inputImage) {
        SQLiteDatabase db = getReadableDatabase();

        String query = String.format("SELECT %s, %s FROM %s WHERE %s = \"%s\" AND %s = \"%s\"",
                InfoMenu.Menu.KEY_NAME, inputName,
                InfoMenu.Menu.KEY_PRICE, inputPrice,
                InfoMenu.Menu.KEY_EXPLAIN, inputExplain,
                InfoMenu.Menu.KEY_IMAGE, inputImage);

        Cursor result = db.rawQuery(query, null);

        Log.d("count result", String.format("%d", result.getCount()));

        if(result.getCount() > 0)
            return true;
        else
            return false;
    }

}