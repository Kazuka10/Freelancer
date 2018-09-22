package com.example.mkorpal.myapplication.przyjecie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.mkorpal.myapplication.R;

public class EditDataActivity extends AppCompatActivity {

    private static final String TAG = "EditDataActivity";

    private ImageButton btnDelete;
    private ImageButton btnSave;

    private EditText editabable_item;
    private EditText nazwa_edit;
    private EditText ilosc_edit;
    MyDBHandler dbHandler;

    private String selectedName;
    private int selectedID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);
        Bundle myBundle = getIntent().getExtras();
        String kod_data = "";
        String nazwa_data = "";
        String ilosc_data = "";
        int id_data = -1;
        if (myBundle != null) {
            Log.i("KOD", "my bundle is not null");
            kod_data = myBundle.getString("kod");
            nazwa_data = myBundle.getString("name");
            ilosc_data = myBundle.getString("ilosc");
            id_data = myBundle.getInt("id");
            Log.i("KOD", "kod data is " + kod_data);
            Log.i("KOD", "nazwa data is " + nazwa_data);
            Log.i("KOD", "ilosc data is " + ilosc_data);
            Log.i("KOD", "id data is " + id_data);


        }

        btnSave = (ImageButton) findViewById(R.id.btnSave);
        btnDelete = (ImageButton) findViewById(R.id.btnDelete);
        editabable_item = (EditText) findViewById(R.id.editabable_item);
        nazwa_edit = (EditText) findViewById(R.id.user_Input_edycja);
        ilosc_edit = (EditText) findViewById(R.id.ilosc_Input_edycja);
        dbHandler = new MyDBHandler(this, null, null, 1);

        final int id_data_updated = id_data;
        editabable_item.setText(kod_data);
        nazwa_edit.setText(nazwa_data);
        ilosc_edit.setText(ilosc_data);
        // THIS IS METHOD
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String kod_data_updated = String.valueOf(editabable_item.getText());
                final String nazwa_data_updated = String.valueOf(nazwa_edit.getText());
                final String ilosc_data_updated = String.valueOf(ilosc_edit.getText());
                Log.d("EditedProd", "Zaktualizowano produkty  " + kod_data_updated +
                        nazwa_data_updated + ilosc_data_updated);
                Products modifiedProd = new Products(id_data_updated, nazwa_data_updated, kod_data_updated, ilosc_data_updated);
                dbHandler.update(modifiedProd);
//              if (!item.equals("")) {
//                    dbHandler.update(item, selectedID, selectedName);
//                } else {
//                    Toast.makeText(EditDataActivity.this, "Wprowadź jakaś wartość", Toast.LENGTH_SHORT).show();
//                }

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //call the delete from list method with id_data_updated which is the id passed from
                // he id PODGLAd activity as an extra and is not shown on the user interface.
                dbHandler.deleteFromList(id_data_updated);

            }
        });
    }
}
