package com.example.mkorpal.myapplication.Zwrot;

/**
 * Created by m.korpal on 05.09.2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MyDBHandler extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 26;
    private static final String DATABASE_NAME = "productDBzwrot.db";
    public static final String TABLE_PRODUCTS = "products";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PRODUCTNAME = "productname";
    public static final String COLUMN_KOD = "productkod";
    public static final String COLUMN_ILOSC = "productilosc";

    //info o bazie przekazane do superklasy
    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_PRODUCTS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PRODUCTNAME + " TEXT," +
                COLUMN_KOD + " TEXT," +
                COLUMN_ILOSC + " TEXT);";

        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

    //Dodawanie nowego wiersza do bazy
    public boolean addProduct(Products product){
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


    //Usuniecie produktu z bazy danych
    public void deleteProduct(String productName){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_PRODUCTNAME + "=\"" + productName + "\";");
    }
    //Usuniecie produktu z bazy danych
    public void deleteProductkod(String productKod){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_KOD + "=\"" + productKod + "\";");
    }
    public void deleteFromList(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_KOD + "=\"" + id + "\";");
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

    public String databaseToString(){
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


}
