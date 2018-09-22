package com.example.mkorpal.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by m.korpal on 19.09.2016.
 */
public class ThreeColumn_ListAdapter extends ArrayAdapter<Products> {

    private LayoutInflater mInflater;
    private ArrayList<Products> users;
    private int mViewResourceId;

public ThreeColumn_ListAdapter(Context context, int textViewResourceId,ArrayList<Products> users){
    super(context,textViewResourceId,users);
    this.users=users;
    mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    mViewResourceId = textViewResourceId;
}

        public View getView(int position, View convertView, ViewGroup parents){
            convertView = mInflater.inflate(mViewResourceId,null);

            Products user = users.get(position);

            if(user !=null){
                TextView _productname = (TextView) convertView.findViewById(R.id.records);
                TextView _productkod = (TextView) convertView.findViewById(R.id.kod);
                TextView _productilosc = (TextView) convertView.findViewById(R.id.ilosc);

                if (_productname != null) {
                    _productname.setText(user.get_productname());
                }
                if (_productkod != null) {
                    _productkod.setText((user.get_productkod()));
                }
                if (_productilosc != null) {
                    _productilosc.setText((user.get_productilosc()));
                }
            }
            return  convertView;
        }
}
