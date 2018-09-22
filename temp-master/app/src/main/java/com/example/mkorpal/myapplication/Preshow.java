package com.example.mkorpal.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Preshow extends AppCompatActivity {
    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;
    private SliderAdapter sliderAdapter;
    private Button mNextBtn;
    private Button mBackBtn;
    private Button mEndBtn;
    public CheckBox radioButton;
    private static final int REQUEST_GROUP_PERMISSION = 25;

    private TextView[] mDots;
    private int mCurrentPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_deploy);
         mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
         mDotLayout = (LinearLayout) findViewById(R.id.dotsLayout);
          sliderAdapter = new SliderAdapter(this);

          mSlideViewPager.setAdapter(sliderAdapter);
            mNextBtn = (Button) findViewById(R.id.nextBtn);
        mBackBtn = (Button) findViewById(R.id.prevBtn);
        mEndBtn = (Button) findViewById(R.id.finishBtn);
        radioButton = (CheckBox) findViewById(R.id.radiobtn);

        addDotsIndicator(0);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor = preferences.edit();
        radioButton.setChecked(preferences.getBoolean("isShowOnboarding", false));

        radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean("isShowOnboarding", radioButton.isChecked());
            }

        });

          mSlideViewPager.addOnPageChangeListener(viewListener);

          mNextBtn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                mSlideViewPager.setCurrentItem(mCurrentPage + 1);
              }
          });
          mBackBtn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  mSlideViewPager.setCurrentItem(mCurrentPage - 1);

              }
          });
          mEndBtn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {


                  Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                  Toast.makeText(Preshow.this, "Sprawdź pozwolenia aplikacji" +
                                  " klikając w ikonę poniżej"
                          , Toast.LENGTH_LONG).show();
                  startActivity(intent);
                  finish();

              }
          });

    }

    public void clearButton(View view){
        radioButton.setChecked(false);
    }
    public void addDotsIndicator(int position){

        mDots = new TextView[3];
        mDotLayout.removeAllViews();

        for(int i = 0; i < mDots.length; i++){
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));

            mDotLayout.addView(mDots[i]);
        }
        if(mDots.length > 0){
            mDots[position].setTextColor(getResources().getColor(R.color.white));
        }
    }
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int i) {
addDotsIndicator(i);
mCurrentPage = i;

if(i == 0){
    mNextBtn.setEnabled(true);
    mBackBtn.setEnabled(false);
    mBackBtn.setVisibility(View.INVISIBLE);
    mEndBtn.setVisibility(View.INVISIBLE);

    mNextBtn.setText("DALEJ");
    mBackBtn.setText("");
} else if(i == mDots.length -1){
    mNextBtn.setEnabled(true);
    mBackBtn.setEnabled(true);
    mBackBtn.setVisibility(View.VISIBLE);
    mEndBtn.setVisibility(View.VISIBLE);
    mNextBtn.setText("");
    mBackBtn.setText("WSTECZ");
}else{
    mNextBtn.setEnabled(true);
    mBackBtn.setEnabled(true);
    mBackBtn.setVisibility(View.VISIBLE);
    mEndBtn.setVisibility(View.INVISIBLE);

    mNextBtn.setText("DALEJ");
    mBackBtn.setText("WSTECZ");
}
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
