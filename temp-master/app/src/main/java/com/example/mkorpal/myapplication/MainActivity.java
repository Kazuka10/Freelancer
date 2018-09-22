package com.example.mkorpal.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mkorpal.myapplication.Wypozyczenie.WypozyczenieActivity;
import com.example.mkorpal.myapplication.Zwrot.ZwrotActivity;
import com.example.mkorpal.myapplication.przyjecie.*;
import com.example.mkorpal.myapplication.przyjecie.Products;

import java.util.ArrayList;

import static android.os.SystemClock.sleep;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView = null;
    Toolbar toolbar = null;
    private int STORAGE_PERMISSION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);

        if (isFirstRun) {
            startActivity(new Intent(MainActivity.this, Preshow.class));
            finish();
        }


        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                .putBoolean("isFirstRun", false).commit();

        MainFragment fragment = new MainFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Sprawdź pozwolenia aplikacji", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.parse("package:" + getPackageName()));
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intent);

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);

        //How to change elements in the header programatically
        View headerView = navigationView.getHeaderView(0);
        TextView emailText = (TextView) headerView.findViewById(R.id.email);
        emailText.setText("Nazwa@sklepu.com");

        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.parse("package:" + getPackageName()));
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_end) {
            //Set the fragment i\

            Toast.makeText(this, "Wychodzenie", Toast.LENGTH_SHORT).show();
            finish();
            System.exit(0);
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            //Set the fragment initially
            GalleryFragment fragment = new GalleryFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_slideshow) {
            MainFragment fragment = new MainFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_manage) {
            startActivity(new Intent(MainActivity.this, Preshow.class));
        } else if (id == R.id.nav_share) {
            KontaktFragment fragment = new KontaktFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
            Toast.makeText(this, "Zaloguj się", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_send) {
            InfoFragment fragment = new InfoFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void WydanieStart(View v){
        switch (v.getId()) {
            case R.id.button13: {    //WypozyczenieActivity - menu glowne Wydanie
                Intent intent = new Intent(getApplicationContext(),WydanieActivity.class);
                startActivity(intent);
                //FUNCKJA OTWIERANIA MENU WYDANIE
                break;
            }



            case R.id.button10:
            {
                Intent intent = new Intent(getApplicationContext(),PrzyjecieActivity.class);
                startActivity(intent);
                //FUNCKJA OTWIERANIA MENU przyjecie
                break;
            }

            case R.id.button8:
            {
                //FUNCKJA OTWIERANIA MENU slownikifinal
                String MESSAGE = "Ta funkcjonalność jest wyłączona ze względu na brak połączenia aplikacjji z systemem sklepowym (tryb online)";

                final String TITLE = "KONTYNUOWAC?";

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
                break;
            }
            case R.id.button7:
            {
                Intent intent = new Intent(getApplicationContext(),Webtransfer_opcje.class);
                startActivity(intent);
                //FUNCKJA OTWIERANIA MENU webtransfer
                break;
            }
            case R.id.button6:
            {
                Activity activity = this;
                InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

                //FUNCKJA OTWIERANIA MENU klawiatura ekranowa
                break;
            }

            case R.id.button3:
            {
                final com.example.mkorpal.myapplication.przyjecie.MyDBHandler dbHandler = new com.example.mkorpal.myapplication.przyjecie.MyDBHandler(getApplicationContext(), null, null, 1);
                ArrayList<com.example.mkorpal.myapplication.przyjecie.Products> userList = new ArrayList<>();

                final com.example.mkorpal.myapplication.MyDBHandler1 dbHandler2 = new com.example.mkorpal.myapplication.MyDBHandler1(getApplicationContext(), null, null, 1);
                ArrayList<com.example.mkorpal.myapplication.przyjecie.Products> nomelist = new ArrayList<>();
                Products user;
                // ArrayList<String> theList = new ArrayList<>();
                Cursor data = dbHandler.getListContents();
                Cursor data2 = dbHandler2.getListContents();
                int numRows = data.getCount();
                int numRows2 = data2.getCount();

                if (numRows == 0 && numRows2 == 0) {
                    Toast.makeText(getApplicationContext(), "Nic do usunięcia", Toast.LENGTH_SHORT).show();
                } else {
                    String MESSAGE = "To spowoduje usunięcie wszelkich danych zebranych w aplikacji";

                    final String TITLE = "KONTYNUOWAC?";
                    final String NEGATIVE_BUTTON = "Anuluj";
                    final String POSITIVE_BUTTON = "Ok";


                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage(MESSAGE)
                            .setTitle(TITLE);
                    builder.setPositiveButton(POSITIVE_BUTTON, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User clicked OK button
                            dbHandler.deleteTableAndCreateNew();
                            dbHandler2.deleteTableAndCreateNew();
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
                //FUNCKJA OTWIERANIA MENU usuwanie baz
                break;
            }

        }


    }

}
