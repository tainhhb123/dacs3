package com.example.androidcosmetics1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidcosmetics1.Activity.ProductDetails;
import com.example.androidcosmetics1.Model.Category;
import com.example.androidcosmetics1.Model.Product;
import com.example.androidcosmetics1.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ItemHolder> {

    Context context;
    ArrayList<Product> arrayproduct;

    public ProductAdapter(Context context, ArrayList<Product> arrayproduct) {
        this.context = context;
        this.arrayproduct = arrayproduct;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_newproduct,null);
        ItemHolder itemHolder = new ItemHolder(v);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Product product = arrayproduct.get(position);
        holder.txtproductname.setText(product.getProduct_name());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtproductprice.setText("Price : " + decimalFormat.format(product.getProduct_price())+ " $");
        Picasso.get().load(product.getProduct_image())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(holder.imgimageproduct);



    }

    @Override
    public int getItemCount() {
        return arrayproduct.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imgimageproduct;
        public TextView txtproductname, txtproductprice;


        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            imgimageproduct = (ImageView) itemView.findViewById(R.id.imageviewproduct);
            txtproductname = (TextView) itemView.findViewById(R.id.textviewnameproduct);
            txtproductprice = (TextView) itemView.findViewById(R.id.textviewpriceproduct);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Product clickedProduct = arrayproduct.get(position);
                Intent intent = new Intent( context, ProductDetails.class);
                intent.putExtra("ProductInformation", clickedProduct);
                context.startActivity(intent);
            }
        }
    }
}
