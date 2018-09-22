package com.example.mkorpal.myapplication.przyjecie;

/**
 * Created by m.korpal on 05.09.2016.
 */

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mkorpal.myapplication.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class PrzyjecieActivity extends Activity {
// Declare references

    Button button;
    public Button button1;
    TextView dateview;



   /* EditText userInput;
    EditText kodInput, iloscInput;
    TextView recordsTextView;
    MyDBHandler1 dbHandler; */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wyplzwrotlinwe1);

        //AKTUALNA DATA Z SYSTEMU
        /*
        Date = (TextView) findViewById(R.id.dateview);
        String Date= DateFormat.getDateTimeInstance().format(new Date());

        dateview.setText(Date);*/
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(10);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TextView tdate = (TextView) findViewById(R.id.dateview);
                                long date = System.currentTimeMillis();
                                SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy\nhh:mm:ss");
                                String dateString = sdf.format(date);
                                tdate.setText(dateString);
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        t.start();
    }



    public void Rejestracja03(View view) {
        Intent rej1 = new Intent(this, PrzyjeciestartActivity.class);
        startActivity(rej1);
    }

    public void Podglad03(View view) {
        Intent pod1 = new Intent(this, podgladActivityprzyjeci.class);
        startActivity(pod1);
    }

    public void Eksportuj03(View view) {
        final String MESSAGE = "TO SPOWODUJE USUNIECIE LISTY PRODUKTOW";

        final String TITLE = "KONTYNUOWAC?";

        final String POSITIVE_BUTTON = "Ok";

        final String NEGATIVE_BUTTON = "Anuluj";

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(MESSAGE)
                .setTitle(TITLE);
        builder.setPositiveButton(POSITIVE_BUTTON, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                MyDBHandler dbHandler = new MyDBHandler(getApplicationContext(), null, null, 1);
                ArrayList<Products> userList = new ArrayList<>();
                Products user;
                // ArrayList<String> theList = new ArrayList<>();
                Cursor data = dbHandler.getListContents();
                int numRows = data.getCount();
                if (numRows == 0) {
                    Toast.makeText(getApplicationContext(), "Baza danch jest pusta", Toast.LENGTH_SHORT).show();
                } else {

                    while (data.moveToNext()) {
                        user = new Products(data.getInt(0), data.getString(1),
                                data.getString(2), data.getString(3));
                        userList.add(user);
                    }
                    //give the file a name in the variable filename .xml is not compulsory.

                    String filename = "somename.xml";

                    //call the method everytime this list is built to create new XML file from db.
                   boolean result= createExternalStoragePrivateFile(userList, filename);
                    if(result){
                    dbHandler.deleteTableAndCreateNew();
                    }

                }
            }
        });
        builder.setNegativeButton(NEGATIVE_BUTTON, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();


    }

    /**
     * The file directory is being logged in the with fileDir tag on D(debug).
     *
     * @param userList takes the list of products that will be displayed on the screeen in this
     *                 activity
     * @param fileName the file name is the name of xml file that the list will be converted into.
     *                 adding .xml to the end of the file is not compulsory.
     */
    boolean createExternalStoragePrivateFile(ArrayList<Products> userList, String fileName) {
        // Create a path where we will place our private file on external
        // storage.
        if (!fileName.endsWith(".xml")) {
            fileName += ".xml";
        }
        File file = new File(getExternalFilesDir(null), fileName);
        Log.d("fileDir", getExternalFilesDir(null).toString());

        try {
            String data = XmlUtility.serializeTable(userList);
            FileOutputStream os = new FileOutputStream(file);
            if (data != null) {
                os.write(data.getBytes());
            }
            os.close();
            return true;
        } catch (IOException e) {
            // Unable to create file, likely because external storage is
            // not currently mounted.
            Log.w("ExternalStorage", "Error writing " + file, e);
        }
        return false;
    }
}