package com.example.mkorpal.myapplication;

import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mkorpal.myapplication.przyjecie.*;
import com.example.mkorpal.myapplication.przyjecie.Products;

import java.util.ArrayList;

public class Webtransfer_opcje extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webtransfer_opcje);

        final String MESSAGE = "To okno nie jest aktywne, gdyż na ten moment aplikacja działa tylko w trybie offline";

        final String TITLE = "KONTYNUOWAC?";

        final String POSITIVE_BUTTON = "Ok";


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(MESSAGE)
                .setTitle(TITLE);
        builder.setPositiveButton(POSITIVE_BUTTON, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                finish();

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

}}
