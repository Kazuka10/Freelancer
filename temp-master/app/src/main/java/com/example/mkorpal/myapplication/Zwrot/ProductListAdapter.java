package com.example.mkorpal.myapplication.Zwrot;

/**
 * Created by m.korpal on 15.09.2016.
 */
/*public class ProductListAdapter extends BaseAdapter {

    private Context mContext;
    private List<Products> mProductList;

    public ProductListAdapter(Context mContext, List<Products> mProductList) {
        this.mContext = mContext;
        this.mProductList = mProductList;
    }

    @Override
    public int getCount() {
        return mProductList.size();
    }

    @Override
    public Object getItem(int position) {
        return mProductList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.list_adapter_view, null);
        TextView textName = (TextView) v.findViewById(R.id.textName);
        TextView textKod = (TextView) v.findViewById(R.id.textKod);
        TextView textIlosc = (TextView) v.findViewById(R.id.textIlosc);

        //set text
        textName.setText(mProductList.get(position).get_productname());
        textKod.setText(mProductList.get(position).get_productkod());
        textIlosc.setText(mProductList.get(position).get_productilosc());

        v.setTag(mProductList.get(position).get_id());
        return v;
    }
}*/
