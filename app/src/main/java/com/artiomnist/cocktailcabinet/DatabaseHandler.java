package com.artiomnist.cocktailcabinet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 28/10/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    /**
     * Database Information
     */
    private static final String DATABASE_NAME="cocktailcabinet.db";
    private static final int SCHEMA_VERSION = 1;

    /**
     * Table Information
     */
    private static final String TABLE_WISHLIST = "wishlist";

    /**
     * Table meta Information
     */
    private static final String KEY_ID = "id";
    private static final String KEY_COCKTAIL = "cocktail";

    public DatabaseHandler(Context context) {
        super(context,DATABASE_NAME, null, SCHEMA_VERSION);
    }

    // Making the Tables
    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE_WISHLIST = "CREATE TABLE " + TABLE_WISHLIST + " ( " +  KEY_ID + " INTEGER PRIMARY KEY," + KEY_COCKTAIL + " TEXT" + ")";
        db.execSQL(CREATE_TABLE_WISHLIST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int old_version, int new_version) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WISHLIST);
        onCreate(db);
    }

    public void addCocktailtoWishlist(String cocktail) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_COCKTAIL, cocktail);

        db.insert(TABLE_WISHLIST, null, values);
        db.close();

    }

    public List<String> getWishlist() {
        List<String> wishlist = new ArrayList<String>();
        String query = "SELECT * FROM " + TABLE_WISHLIST;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);


        if (cursor.moveToFirst()) {
            do {
                String cocktail = cursor.getString(1);
                wishlist.add(cocktail);

            } while (cursor.moveToNext());
        }

        return wishlist;
    }

    public void deleteCocktail(String cocktail) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_WISHLIST, KEY_COCKTAIL + " = ?", new String[] {cocktail});
        db.close();
    }

}
