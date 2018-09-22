package com.example.mkorpal.myapplication.Zwrot;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mkorpal.myapplication.R;

/**
 * Created by m.korpal on 07.09.2016.
 */
public class ZwrotstartActivity extends Activity {
    EditText userInput;
    EditText kodInput, iloscInput;
    TextView recordsTextView;
    MyDBHandler dbHandler;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter2;
    private static final int ZBAR_SCANNER_REQUEST = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podgl);
        Intent rej1 = getIntent();
        userInput = (EditText) findViewById(R.id.user_Input);
        kodInput = (EditText) findViewById(R.id.kod_Input);
        iloscInput = (EditText) findViewById(R.id.ilosc_Input);
        spinner = (Spinner) findViewById(R.id.spinner1);
        adapter2 = ArrayAdapter.createFromResource(this, R.array.pracownicy,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter2);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                Toast.makeText(getBaseContext(),parent.getItemAtPosition(position)+ " wybrany/a", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        dbHandler = new MyDBHandler(this, null, null, 1);
        //  printDatabase();
    }
    //wyswietlenie bazy
   /*public void printDatabase(){
        String dbString = dbHandler.databaseToString();
        recordsTextView.setText(dbString);

        userInput.setText("");
    }*/


    //wstawienie rekordu
    public void addButtonClicked(View view) {

        Products product = new Products(userInput.getText().toString(), kodInput.getText().toString(), iloscInput.getText().toString());


       /* String user = userInput.getText().toString();
        String kod = kodInput.getText().toString();
        String ilosc = iloscInput.getText().toString();*/

        if(TextUtils.isEmpty(product._productname)) {
            userInput.setError("Pole nazwa nie może być puste!");
            return;
        }
        if(TextUtils.isEmpty(product._productilosc)) {
            iloscInput.setError("Pole ilosc nie może być puste!");
            return;
        }
        if(TextUtils.isDigitsOnly(product._productkod)== false) {

            kodInput.setError("To pole wymaga cyfr!");
            return;
        }
        if(product._productname.length() != 0 && product._productkod.length() != 0 && product._productilosc.length() != 0){
            dbHandler.addProduct(product);
            userInput.setText("");
            kodInput.setText("");
            iloscInput.setText("");
            Context context = getApplicationContext();
            CharSequence text = "Zapisano pozycję";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(0, 0, 500);
            toast.show();
        }
        else{
            Toast.makeText(ZwrotstartActivity.this, "Musisz podać jakieś wartości",Toast.LENGTH_LONG).show();

        }
        //  printDatabase();

    }
    protected void SKANUJ(View view){
        if (isCameraAvailable()) {
            Intent intent = new Intent(this, ZBarScannerActivity.class);
            startActivityForResult(intent, ZBAR_SCANNER_REQUEST);
        } else {
            Toast.makeText(this, "Rear Facing Camera Unavailable", Toast.LENGTH_SHORT).show();
        }
    }

    // usuwanie rekordu
    public void deleteButtonClicked(View view) {
        String inputText2 = kodInput.getText().toString();
        String inputText = userInput.getText().toString();
        dbHandler.deleteProduct(inputText);
        dbHandler.deleteProductkod(inputText2);
        userInput.setText("");
        kodInput.setText("");
        iloscInput.setText("");
// OPTYMALIZACJA WSTAWIEN REKORDOW DO BAZY DANYCH
        // INSERT PER SECOND z 85 do 92k
        // http://stackoverflow.com/questions/1711631/improve-insert-per-second-performance-of-sqlite?rq=1

        if (inputText == null || inputText.isEmpty() || inputText ==" ") {
            Context context = getApplicationContext();
            CharSequence text = "Nic do usunięcia";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(0, 0, 500);
            toast.show();
        } else if(inputText != null){
            Context context = getApplicationContext();
            CharSequence text = "Usunięto pozycję";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(0, 0, 500);
            toast.show();

            // printDatabase();
        }
        else if (inputText2 != null && inputText == null){

                Context context = getApplicationContext();
                CharSequence text = "Usunięto pozycję";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.setGravity(0, 0, 500);
                toast.show();
            }
           else if(inputText2 == null && inputText == null){
            Context context = getApplicationContext();
            CharSequence text = "Nic do usunięcia";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(0, 0, 500);
            toast.show();

        }
    }
    public boolean isCameraAvailable() {
        PackageManager pm = getPackageManager();
        return pm.hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ZBAR_SCANNER_REQUEST:
                if (resultCode == RESULT_OK) {
                    kodInput.setText(""+data.getStringExtra(ZBarConstants.SCAN_RESULT));
                } else if(resultCode == RESULT_CANCELED && data != null) {
                    String error = data.getStringExtra(ZBarConstants.ERROR_INFO);
                    if(!TextUtils.isEmpty(error)) {
                        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }
}

