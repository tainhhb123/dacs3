package com.example.androidcosmetics1.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.example.androidcosmetics1.Home;
import com.example.androidcosmetics1.Model.Cart;
import com.example.androidcosmetics1.Model.Product;
import com.example.androidcosmetics1.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ProductDetails extends AppCompatActivity {

    Toolbar toolbar_details;
    ImageView img_details;
    TextView txtname, txtprice, txtdescribe;
    Spinner spinner;
    Button button_order;

    int id = 0;
    String Name_detail = "" ;
    int Price_detail = 0;
    String Image_detail = "";
    String Describe_detail = "";
    int Idpd = 0;
   


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        map();
        ActionToolBar();
        GetInformation();
        CatchEventSpinner();
        EventButton();

    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menucart:
                Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void EventButton() {
        button_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Home.arr_cart != null) {
                    if (Home.arr_cart.size() >0) {
                        int qtt = Integer.parseInt(spinner.getSelectedItem().toString());
                        boolean exists = false;
                        for (int i = 0; i <Home.arr_cart.size(); i++) {
                            if (Home.arr_cart.get(i).getIdpd() == id) {
                                Home.arr_cart.get(i).setQuantitysp(Home.arr_cart.get(i).getQuantitysp() + qtt );
                                if (Home.arr_cart.get(i).getQuantitysp() >= 10) {
                                    Home.arr_cart.get(i).setQuantitysp(10);
                                }
                                Home.arr_cart.get(i).setPricepd(Price_detail * Home.arr_cart.get(i).getQuantitysp());
                                exists = true;
                            }
                        }
                        if (exists == false) {
                            int quantity = Integer.parseInt(spinner.getSelectedItem().toString());
                            long newprice = quantity + Price_detail;
                            Home.arr_cart.add(new Cart(id, Name_detail, newprice, Image_detail, quantity));
                        }
                    } else {
                        int quantity = Integer.parseInt(spinner.getSelectedItem().toString());
                        long newprice = quantity + Price_detail;
                        Home.arr_cart.add(new Cart(id, Name_detail, newprice, Image_detail, quantity));
                    }
                    Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                    startActivity(intent);
                } else {
                    Home.arr_cart = new ArrayList<>();
                    int quantity = Integer.parseInt(spinner.getSelectedItem().toString());
                    long newprice = quantity + Price_detail;
                    Home.arr_cart.add(new Cart(id, Name_detail, newprice, Image_detail, quantity));
                    Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void CatchEventSpinner() {
        Integer[] quantity = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, quantity);
        spinner.setAdapter(arrayAdapter);

    }

    private void GetInformation() {


        Intent intent = getIntent();

        Product product = (Product) getIntent().getSerializableExtra("ProductInformation");

        id = product.getID();
        Name_detail = product.getProduct_name();
        Price_detail = product.getProduct_price();
        Image_detail = product.getProduct_image();
        Describe_detail = product.getProduct_describe();
        Idpd = product.getProduct_ID();
        txtname.setText(Name_detail);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtprice .setText("Price: " + decimalFormat.format(Price_detail) + " $");
        txtdescribe.setText(Describe_detail);
        Picasso.get().load(Image_detail)
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(img_details);


    }

    private void ActionToolBar() {
        setSupportActionBar(toolbar_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_details.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void map() {
        toolbar_details = (Toolbar) findViewById(R.id.toolbar_productdetails);
        img_details = (ImageView) findViewById(R.id.imgview_productdetails);
        txtname = (TextView) findViewById(R.id.textview_productdetails_name);
        txtprice = (TextView) findViewById(R.id.textview_productdetails_price);
        txtdescribe = (TextView) findViewById(R.id.textview_describe_pd);
        spinner = (Spinner) findViewById(R.id.spinner);
        button_order = (Button) findViewById(R.id.button_add_cart);

    }
}