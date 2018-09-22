package com.example.mkorpal.myapplication.Zwrot;

/**
 * Created by m.korpal on 05.09.2016.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mkorpal.myapplication.R;


public class ZwrotActivity extends Activity {
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
        setContentView(R.layout.wyplzwrotlinwe);

        //AKTUALNA DATA Z SYSTEMU
        /*
        Date = (TextView) findViewById(R.id.dateview);
        String Date= DateFormat.getDateTimeInstance().format(new Date());

        dateview.setText(Date);*/


    }
    protected void Rejestracja04(View view){
        Intent rej1=new Intent(this,ZwrotstartActivity.class);
        startActivity(rej1);
    }

    protected void Podglad04(View view){
        Intent pod1=new Intent(this,podgladActivityZwrot.class);
        startActivity(pod1);
    }
    protected void Eksportuj04(View view){


    }

}
