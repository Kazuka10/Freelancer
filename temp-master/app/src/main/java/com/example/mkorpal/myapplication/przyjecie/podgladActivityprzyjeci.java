package com.example.mkorpal.myapplication.przyjecie;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mkorpal.myapplication.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by m.korpal on 08.09.2016.
 */
public class podgladActivityprzyjeci extends AppCompatActivity {

    private static final String TAG = "podgladActivityprzyjeci";
    MyDBHandler dbHandler;
    ArrayList<Products> userList;
    Products user;
    TextView recordstextView;
    TextView ilosctextView;
    TextView kodtextView;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.viewcontents_layout);
        final Intent podglad = getIntent();
        ListView listView = (ListView) findViewById(R.id.listView);
        ImageButton btn;
        ImageButton btn2;
        btn = (ImageButton) findViewById(R.id.Usun);
        btn2 = (ImageButton) findViewById(R.id.Edytuj);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(podgladActivityprzyjeci.this, "Kliknieto przycisk usun", Toast.LENGTH_SHORT).show();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(podgladActivityprzyjeci.this, "Kliknieto przycisk edytuj", Toast.LENGTH_SHORT).show();
            }
        });

        dbHandler = new MyDBHandler(this, null, null, 1);
        userList = new ArrayList<>();

        // ArrayList<String> theList = new ArrayList<>();
        Cursor data = dbHandler.getListContents();
        int numRows = data.getCount();
        if (numRows == 0) {
            Toast.makeText(podgladActivityprzyjeci.this, "Baza danch jest pusta", Toast.LENGTH_SHORT).show();
        } else {

            while (data.moveToNext()) {
                user = new Products(data.getInt(0), data.getString(1), data.getString(2), data.getString(3));
                userList.add(user);
                //       theList.add(data.getString(1));
                //        ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList);
                //      listView.setAdapter(listAdapter);

            }

            final ThreeColumn_ListAdapter adapter = new ThreeColumn_ListAdapter(this, R.layout.list_adapter_view, userList);
            listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                    Products prod = (Products) parent.getItemAtPosition(position);

                    Log.i("TESTcLick", "correct selection" + prod._productname);
           /*    try {
                   final Locale.Category category = (Locale.Category) parent.getItemAtPosition(position);

                   AlertDialog.Builder alertDialogBuilder = new
               } */                       // DO UKONCZENIA http://stackoverflow.com/questions/37728392/android-how-can-i-delete-an-item-from-a-listview-that-is-saved-in-a-sqlite-data


                    //  dbHandler.deleteFromList(position);
                    // userList.remove(position);            //usuwanie tylko z itemlist bez interakcji na baze danych
                    //  adapter.notifyDataSetChanged();


                    //  Log.d(TAG, "onItemClick: Kliknieto na " + name);

                    Intent intent = new Intent(podgladActivityprzyjeci.this, EditDataActivity.class);
                    intent.putExtra("kod", prod._productkod);
                    intent.putExtra("name", prod._productname);
                    intent.putExtra("ilosc", prod._productilosc);
                    intent.putExtra("id", prod._productid);

                    startActivity(intent);


                    Toast.makeText(getBaseContext(), parent.getItemIdAtPosition(position) + " pozycja jest wybrana", Toast.LENGTH_SHORT).show();
                    view.setSelected(true);



                }
            });

        }

     /*   recordstextView = (TextView) findViewById(R.id.records);
        kodtextView = (TextView) findViewById(R.id.kod);
        ilosctextView = (TextView) findViewById(R.id.ilosc); */

        if (data.getCount() == 0) {
            Context context = getApplicationContext();
            CharSequence text = "Baza danych jest pusta*";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(0, 0, 500);
            toast.show();
            ;
        } else {
            while (data.moveToNext()) {
             /*   theList.add(data.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);
                listView.setAdapter(listAdapter);*/
            }
        }


        //   printDatabase();
    }
//wyswietlenie z bazy
  /*  public void printDatabase(){
        String dbkod = dbHandler.kodToString();
        String dbString = dbHandler.databaseToString();
        String dbilosc = dbHandler.iloscToString();
        recordstextView.setText(dbString);
        ilosctextView.setText(dbilosc);

        kodtextView.setText(dbkod);

    }*/

    // DO USUWANIA POZ"YCJI Z ITEMLIST


}
