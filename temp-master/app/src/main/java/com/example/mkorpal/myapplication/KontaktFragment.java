package com.example.mkorpal.myapplication;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class KontaktFragment extends Fragment {


    public KontaktFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        Intent intent = new Intent(Intent.ACTION_VIEW);
        String subject = "Wsparcie aplikacji handel";
        String body = "Witam, " +
                "Potrzebuję pomocy z aplikacją. Proszę o kontakt";
        Uri data = Uri.parse("mailto:michi09@interia.pl?subject=" + subject + "&body=" + body);
        intent.setData(data);
        startActivity(intent);
        return inflater.inflate(R.layout.fragment_kontakt, container, false);
    }


}
