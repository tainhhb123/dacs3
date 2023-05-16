package com.example.androidcosmetics1.Adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidcosmetics1.Model.Product;
import com.example.androidcosmetics1.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MakeUpAdapter extends BaseAdapter {
    Context context;
    ArrayList<Product> arraymakeup;

    public MakeUpAdapter(Context context, ArrayList<Product> arraymakeup) {
        this.context = context;
        this.arraymakeup = arraymakeup;
    }

    @Override
    public int getCount() {
        return arraymakeup.size();
    }

    @Override
    public Object getItem(int i) {
        return arraymakeup.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder {
        public TextView txtnamemakeup, txtpricemakeup, txtdescribemakeup;
        public ImageView imgmakeup;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MakeUpAdapter.ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new MakeUpAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.row_makeup, null);
            viewHolder.txtnamemakeup = view.findViewById(R.id.textviewmakeup);
            viewHolder.txtpricemakeup = view.findViewById(R.id.textviewpricemakeup);
            viewHolder.txtdescribemakeup= view.findViewById(R.id.textviewdescribemakeup);
            viewHolder.imgmakeup = view.findViewById(R.id.imageviewmakeup);
            view.setTag(viewHolder);
        } else {
            viewHolder = (MakeUpAdapter.ViewHolder) view.getTag();
        }
        Product product = (Product) getItem(i);
        viewHolder.txtnamemakeup.setText(product.getProduct_name());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtpricemakeup.setText("Price : " + decimalFormat.format(product.getProduct_price())+ " $");
        viewHolder.txtdescribemakeup.setMaxLines(2);
        viewHolder.txtdescribemakeup.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtdescribemakeup.setText(product.getProduct_describe());
        Picasso.get().load(product.getProduct_image())
                .placeholder(com.example.androidcosmetics1.R.drawable.noimage)
                .error(com.example.androidcosmetics1.R.drawable.error)
                .into(viewHolder.imgmakeup);
        return view;
    }
}
