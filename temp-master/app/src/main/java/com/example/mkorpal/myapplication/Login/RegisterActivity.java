package com.example.mkorpal.myapplication.Login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mkorpal.myapplication.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText et_name, et_password, et_passwordconfirm;
    Button regbtn;
    public String name, password, confirmpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        et_name = (EditText) findViewById(R.id.uzytkrejestr);
        et_password = (EditText) findViewById(R.id.haslorejestr);
        et_passwordconfirm = (EditText) findViewById(R.id.haslorejestr2);
        regbtn = (Button) findViewById(R.id.regbtn);
        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }
    public void register(){
        initialize();
        if(!validate()){
            Toast.makeText(this,"Rejestracja zakończona niepowodzeniem", Toast.LENGTH_SHORT).show();
        }
        else{
            onSignupSuccess();
        }
    }
    public void onSignupSuccess(){
        //TODO akcja po prawidłowej walidacji
    }

    public boolean validate(){
        boolean valid = true;
        if(name.isEmpty()||name.length()>20){
            et_name.setError("Wprowadz poprawną nazwę!");
            valid = false;
        }
        if(password.isEmpty()||password.length()<5){
            et_password.setError("Zbyt krótkie hasło!");
            valid = false;
        }
        if(password!=confirmpassword){
            et_passwordconfirm.setError("Różne hasła!");
            valid = false;
        }
        return valid;

    }
    public void initialize(){
        name = et_name.getText().toString().trim();
        password = et_password.getText().toString().trim();
        confirmpassword = et_passwordconfirm.getText().toString().trim();

    }
}
