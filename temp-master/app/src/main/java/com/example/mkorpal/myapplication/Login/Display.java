package com.example.mkorpal.myapplication.Login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mkorpal.myapplication.R;
public class Display extends AppCompatActivity {

    /*private ProgressBar spinner;
    spinner=(ProgressBar)findViewById(R.id.progressBar1);*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        String username = getIntent().getStringExtra("Uzytkownik");

        TextView tv = (TextView)findViewById(R.id.TFusername);
        tv.setText(username);

    }
}
