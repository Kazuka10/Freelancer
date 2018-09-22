package com.example.mkorpal.myapplication.przyjecie;

/**
 * Created by m.korpal on 05.09.2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Set;


public class MyDBHandler extends SQLiteOpenHelper {
    private static final String TAG = "MyDBHandler1";
    private static final int DATABASE_VERSION = 33;
    private static final String DATABASE_NAME = "productDBprzyjecie.db";
    public static final String TABLE_PRODUCTS = "products";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PRODUCTNAME = "productname";
    public static final String COLUMN_KOD = "productkod";
    public static final String COLUMN_ILOSC = "productilosc";
    // public static final

    //info o bazie przekazane do superklasy
    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_PRODUCTS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PRODUCTNAME + " TEXT," +
                COLUMN_KOD + " TEXT UNIQUE," +
                COLUMN_ILOSC + " TEXT);";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

    //Dodawanie nowego wiersza do bazy
    public boolean addProduct(Products product) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCTNAME, product.get_productname());
        values.put(COLUMN_KOD, product.get_productkod());
        values.put(COLUMN_ILOSC, product.get_productilosc());
        SQLiteDatabase db = getWritableDatabase();
        long result = db.insert(TABLE_PRODUCTS, null, values);
        db.close();

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public void deleteTableAndCreateNew() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PRODUCTS);
        db.close();
    }

    public void update(Products product) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_PRODUCTNAME, product._productname);
        values.put(COLUMN_KOD, product._productkod);
        values.put(COLUMN_ILOSC, product._productilosc);
        Log.d("UPDATINGROW", "prodname:" + product._productname + " kod:" + product._productkod +
                " ilosc:" + product._productilosc + " id:" + String.valueOf(product._productid));
        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(TABLE_PRODUCTS, values, COLUMN_ID + "= ?", new String[]{String.valueOf(product._productid)});

        db.close(); // Closing database connection
    }

    //Usuniecie produktu z bazy danych
    public void deleteProduct(String productName) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_PRODUCTNAME + "=\"" + productName + "\";");
    }

    //Usuniecie produktu z bazy danych
    public void deleteProductkod(String productKod) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_KOD + "=\"" + productKod + "\";");
    }

    public void deleteFromList(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_ID + "=\"" + id + "\";");
    }

 /*   public void updateInformation(String old_kod, String new_kod, String new_name, String new_ilosc){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_KOD,new_kod);
        values.put(COLUMN_PRODUCTNAME,new_name);
        values.put(COLUMN_ILOSC,new_ilosc);
        String selection = COLUMN_KOD +  // dokocnzeczye https://youtu.be/g2x1lzBKB8M + https://youtu.be/nY2bYJyGty8


    }*/

    public void updateName(String newName, int id, String oldName) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "UPDATE " + TABLE_PRODUCTS + " SET " + COLUMN_PRODUCTNAME +
                " = '" + newName + "' WHERE " + COLUMN_ID + " = '" + id + "'" +
                " AND " + COLUMN_PRODUCTNAME + " = '" + oldName + "'";
        db.execSQL(query);
    }


    //Wyswietlenie bazy danych jako string
    public String kodToString() {
        String kodString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE 1";

        Cursor kodSet = db.rawQuery(query, null);
        kodSet.moveToFirst();

        while (!kodSet.isAfterLast()) {
            // wartosc null moze sie pojawic z uwagi na pusty konstruktor
            if (kodSet.getString(kodSet.getColumnIndex("productkod")) != null) {
                kodString += kodSet.getString(kodSet.getColumnIndex("productkod"));
                kodString += "\n";
            }
            kodSet.moveToNext();
        }
        db.close();
        return kodString;
    }

    public String iloscToString() {
        String iloscString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE 1";

        Cursor iloscSet = db.rawQuery(query, null);
        iloscSet.moveToFirst();

        while (!iloscSet.isAfterLast()) {
            // wartosc null moze sie pojawic z uwagi na pusty konstruktor
            if (iloscSet.getString(iloscSet.getColumnIndex("productilosc")) != null) {
                iloscString += iloscSet.getString(iloscSet.getColumnIndex("productilosc"));
                iloscString += "\n";
            }
            iloscSet.moveToNext();
        }
        db.close();
        return iloscString;
    }

    public String databaseToString() {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE 1";

        //kursor wskazuje indeks wiersza
        Cursor recordSet = db.rawQuery(query, null);
        //przesuneicie do peirwszego wiersza w bazie danych
        recordSet.moveToFirst();

        //ostatnia pozycja wskazana jest przez kursor znajdujacy sie po ostatnim wierszu
        while (!recordSet.isAfterLast()) {
            // wartosc null moze sie pojawic z uwagi na pusty konstruktor
            if (recordSet.getString(recordSet.getColumnIndex("productname")) != null) {
                dbString += recordSet.getString(recordSet.getColumnIndex("productname"));
                dbString += "\n";
            }
            recordSet.moveToNext();
        }
        db.close();
        return dbString;
    }

    public Cursor getListContents() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_PRODUCTS, null);
        return data;
    }

    public Cursor getItemID(String name) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT " + COLUMN_ID + " FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_PRODUCTNAME + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

}
