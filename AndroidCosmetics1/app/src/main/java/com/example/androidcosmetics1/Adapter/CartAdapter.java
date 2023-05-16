package com.example.androidcosmetics1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.androidcosmetics1.Activity.CartActivity;
import com.example.androidcosmetics1.Home;
import com.example.androidcosmetics1.Model.Cart;
import com.example.androidcosmetics1.R;
import com.squareup.picasso.Picasso;


import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartAdapter extends BaseAdapter {

    Context context;
    ArrayList<Cart> arr_cart;

    public CartAdapter(Context context, ArrayList<Cart> arr_cart) {
        this.context = context;
        this.arr_cart = arr_cart;
    }

    @Override
    public int getCount() {
        return arr_cart.size();
    }

    @Override
    public Object getItem(int i) {
        return arr_cart.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        public TextView txtnamecart, txtpricecart;
        public ImageView imgcart;
        public Button btnminus, btnnvalues, btnplus;


    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
//         ViewHolder viewHolder = null;
        final ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.row_cart, null);
            viewHolder.txtnamecart = (TextView) view.findViewById(R.id.textview_name_cart);
            viewHolder.txtpricecart = (TextView) view.findViewById(R.id.textview_price_cart);
            viewHolder.imgcart = (ImageView) view.findViewById(R.id.imageview_cart);
            viewHolder.btnminus = (Button) view.findViewById(R.id.buttonminus);
            viewHolder.btnnvalues = (Button) view.findViewById(R.id.buttonvalues);
            viewHolder.btnplus = (Button) view.findViewById(R.id.buttonplus);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Cart cart = (Cart) getItem(i);
        viewHolder.txtnamecart.setText(cart.getNamepd());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtpricecart.setText(decimalFormat.format(cart.getPricepd())+ "$");

        Picasso.get().load(cart.getImagepd())
                .placeholder(com.example.androidcosmetics1.R.drawable.noimage)
                .error(com.example.androidcosmetics1.R.drawable.error)
                .into(viewHolder.imgcart);

        viewHolder.btnnvalues.setText(cart.getQuantitysp() + "");
        int qtt = Integer.parseInt(viewHolder.btnnvalues.getText().toString());
        if (qtt >= 10) {
            viewHolder.btnplus.setVisibility(View.INVISIBLE);
            viewHolder.btnminus.setVisibility(View.VISIBLE);
        } else if (qtt <=1){
            viewHolder.btnminus.setVisibility(View.INVISIBLE);
        } else if (qtt >=1) {
            viewHolder.btnminus.setVisibility(View.VISIBLE);
            viewHolder.btnplus.setVisibility(View.VISIBLE);
        }
        viewHolder.btnplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int qttnew = Integer.parseInt(viewHolder.btnnvalues.getText().toString())+ 1;
                int qttpresent = Home.arr_cart.get(i).getQuantitysp();
                long pricepresent = Home.arr_cart.get(i).getPricepd();
                Home.arr_cart.get(i).setQuantitysp(qttnew);
                long newprice = (pricepresent * qttnew) / qttpresent;
                Home.arr_cart.get(i).setPricepd(newprice);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                viewHolder.txtpricecart.setText(decimalFormat.format(newprice) + "$");
                CartActivity.EvenUltil();
                if (qttnew > 9) {
                    viewHolder.btnplus.setVisibility(View.INVISIBLE);
                    viewHolder.btnminus.setVisibility(View.VISIBLE);
                    viewHolder.btnnvalues.setText((String.valueOf(qttnew)));
                } else {
                    viewHolder.btnminus.setVisibility(View.VISIBLE);
                    viewHolder.btnplus.setVisibility(View.VISIBLE);
                    viewHolder.btnnvalues.setText(String.valueOf(qttnew));
                }

            }
        });
        viewHolder.btnminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int qttnew = Integer.parseInt(viewHolder.btnnvalues.getText().toString()) -1;
                int qttpresent = Home.arr_cart.get(i).getQuantitysp();
                long pricepresent = Home.arr_cart.get(i).getPricepd();
                Home.arr_cart.get(i).setQuantitysp(qttnew);
                long newprice = (pricepresent * qttnew) / qttpresent;
                Home.arr_cart.get(i).setPricepd(newprice);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                viewHolder.txtpricecart.setText(decimalFormat.format(newprice) + "$");
                CartActivity.EvenUltil();
                if (qttnew < 2) {
                    viewHolder.btnminus.setVisibility(View.INVISIBLE);
                    viewHolder.btnplus.setVisibility(View.VISIBLE);
                    viewHolder.btnnvalues.setText((String.valueOf(qttnew)));
                } else {
                    viewHolder.btnminus.setVisibility(View.VISIBLE);
                    viewHolder.btnplus.setVisibility(View.VISIBLE);
                    viewHolder.btnnvalues.setText(String.valueOf(qttnew));
                }
            }
        });
        return view;
    }
}
