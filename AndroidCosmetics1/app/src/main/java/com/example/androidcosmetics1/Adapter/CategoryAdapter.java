package com.example.androidcosmetics1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidcosmetics1.Model.Category;
import com.example.androidcosmetics1.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryAdapter extends BaseAdapter {

    ArrayList<Category> arrayListcategory;
    Context context;

    public CategoryAdapter(ArrayList<Category> arrayListcategory, Context context) {
        this.arrayListcategory = arrayListcategory;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayListcategory.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayListcategory.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class ViewHolder{
        TextView txtnamecategory;
        ImageView imgcategory;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_category, null);
            viewHolder.txtnamecategory = (TextView) view.findViewById(R.id.textview_category);
            viewHolder.imgcategory = (ImageView) view.findViewById(R.id.imageview_category);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();

        }
        Category category = (Category) getItem(i);
        viewHolder.txtnamecategory.setText(category.getProduct_name());
//            Picasso.with(context).load(category.getProduct_image())

        Picasso.get().load(category.getProduct_image())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHolder.imgcategory);

        return view;
    }
}
