package com.example.mkorpal.myapplication;

/**
 * Created by m.korpal on 05.09.2016.
 */
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;


public class WydanieActivity extends Activity {
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
        setContentView(R.layout.wyplzwrotlinwe3);

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
                        Thread.sleep(1000);
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


    public void Rejestracja001(View view){
        Intent rej1=new Intent(this,WydaniestartActivity.class);
        startActivity(rej1);

    }

    public void Podglad001(View view){
        Intent pod1=new Intent(this,podgladActivity.class);
        startActivity(pod1);
    }
    public void Eksportuj001(View view){
        String MESSAGE = "WYMAGANE JEST POŁĄCZENIE Z SYSTEMEM SKLEPOWYM ORAZ OBSŁUGA DRUKARKI ETYKIET. " +
                "DRUK NIE ZOSTANIE ZREALIZOWANY!";

        final String TITLE = "OSTRZEŻENIE";

        final String POSITIVE_BUTTON = "Ok";


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(MESSAGE)
                .setTitle(TITLE);
        builder.setPositiveButton(POSITIVE_BUTTON, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                onResume();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }

}
