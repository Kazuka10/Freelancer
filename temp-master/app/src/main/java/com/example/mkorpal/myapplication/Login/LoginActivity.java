package com.example.mkorpal.myapplication.Login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.example.mkorpal.myapplication.R;


/**
 * Created by miftakhul on 26/06/16.
 */
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

    }

    public void onButtonClick(View v){

        if(v.getId() == R.id.Blogin){
            EditText a = (EditText)findViewById(R.id.TFusername);
            String str = a.getText().toString();

            Intent i = new Intent(LoginActivity.this, Display.class);
            i.putExtra("Uzytkownik",str);
            startActivity(i);
        }
    }

}
