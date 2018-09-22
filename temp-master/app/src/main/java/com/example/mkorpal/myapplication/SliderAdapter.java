package com.example.mkorpal.myapplication;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by michalpc on 08.08.2018.
 */

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    public int[] slide_images = {
            R.drawable.info,
            R.drawable.caution,
            R.drawable.switcher
    };

    public String[] slide_headings = {
            "INFORMACJA",
            "UDOSKONALENIE WERSJI",
            "POZWOLENIA"
    };
    public String[] slide_descs = {
            "Jest to pierwsza wstępna wersja aplikacji, w której większość funkcji została zaimplementowana. Działa wyłącznie w trybie offline",
            "Planowany jest dalszy rozwój aplikacj, między innymi moduł wymiany etykiet i organizowania struktury ekspozycji.",
            "Aby aplikacja działała prawidłowo, należy udzielić wymaganych pozwoleń"
    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (RelativeLayout) object;
    }

@Override
    public Object instantiateItem(ViewGroup container, int position){

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout,container, false);

    ImageView slideImageView = (ImageView) view.findViewById(R.id.slide_image);
    TextView slideHeading = (TextView) view.findViewById(R.id.slide_heading);
    TextView slideDescription = (TextView) view.findViewById(R.id.slide_description);

    slideImageView.setImageResource(slide_images[position]);
    slideHeading.setText(slide_headings[position]);
    slideDescription.setText(slide_descs[position]);

    container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object){

        container.removeView((RelativeLayout)object);
    }
}
