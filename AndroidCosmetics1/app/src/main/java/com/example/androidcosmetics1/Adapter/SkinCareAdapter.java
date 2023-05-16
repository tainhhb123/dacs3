package com.example.androidcosmetics1.Adapter;

import static android.os.Build.VERSION_CODES.R;

import android.content.Context;
import android.media.Image;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidcosmetics1.Model.Product;
import com.example.androidcosmetics1.R;
import com.example.androidcosmetics1.R.layout;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SkinCareAdapter extends BaseAdapter {

    Context context;
    ArrayList<Product> arrayskincare;

    public SkinCareAdapter(Context context, ArrayList<Product> arrayskincare) {
        this.context = context;
        this.arrayskincare = arrayskincare;
    }

    @Override
    public int getCount() {
        return arrayskincare.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayskincare.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder {
        public TextView txtnameskincare, txtpriceskincare, txtdescribeskincare;
        public ImageView imgskincare;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(com.example.androidcosmetics1.R.layout.row_skincare, null);
            viewHolder.txtnameskincare = view.findViewById(com.example.androidcosmetics1.R.id.textviewskincare);
            viewHolder.txtpriceskincare = view.findViewById(com.example.androidcosmetics1.R.id.textviewpriceskincare);
            viewHolder.txtdescribeskincare = view.findViewById(com.example.androidcosmetics1.R.id.textviewdescribeskincare);
            viewHolder.imgskincare = view.findViewById(com.example.androidcosmetics1.R.id.imageviewskincare);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Product product = (Product) getItem(i);
        viewHolder.txtnameskincare.setText(product.getProduct_name());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtpriceskincare.setText("Price : " + decimalFormat.format(product.getProduct_price())+ " $");
        viewHolder.txtdescribeskincare.setMaxLines(2);
        viewHolder.txtdescribeskincare.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtdescribeskincare.setText(product.getProduct_describe());
        Picasso.get().load(product.getProduct_image())
                .placeholder(com.example.androidcosmetics1.R.drawable.noimage)
                .error(com.example.androidcosmetics1.R.drawable.error)
                .into(viewHolder.imgskincare);
        return view;
    }
}
