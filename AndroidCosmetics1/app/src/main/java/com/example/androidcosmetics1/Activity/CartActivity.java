package com.example.androidcosmetics1.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.androidcosmetics1.Adapter.CartAdapter;
import com.example.androidcosmetics1.Home;
import com.example.androidcosmetics1.Model.Cart;
import com.example.androidcosmetics1.R;
import com.example.androidcosmetics1.util.CheckConnect;

import java.text.DecimalFormat;

public class CartActivity extends AppCompatActivity {

    ListView lvcart;
    TextView txtnotification;
    static TextView txttotalpayment;
    Button btnpayment, btncontinueshopping;
    Toolbar toolbarcart;
    CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        map();
        ActionToolBar();
        CheckData();
        EvenUltil();
        CatchOnItemListView();
        EventButton();
    }

    private void EventButton() {
        btncontinueshopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });
        btnpayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Home.arr_cart.size() >0) {
                    Intent intent = new Intent(getApplicationContext(), CustomerInformation.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Cart is null", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void CatchOnItemListView() {
        lvcart.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
                builder.setTitle("Product Deletion Confirmation");
                builder.setMessage("Are You Sure You Want To Delete This Product?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (Home.arr_cart.size() <= 0) {
                            txtnotification.setVisibility(View.VISIBLE);

                        } else {
                            Home.arr_cart.remove(position);
                            cartAdapter.notifyDataSetChanged();
                            EvenUltil();
                            if (Home.arr_cart.size() <= 0) {
                                txtnotification.setVisibility(View.VISIBLE);
                            } else {
                                txtnotification.setVisibility(View.INVISIBLE);
                                cartAdapter.notifyDataSetChanged();
                                EvenUltil();
                            }

                        }
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        cartAdapter.notifyDataSetChanged();
                        EvenUltil();
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    public static void EvenUltil() {
        long totalpayment = 0;
        for(int i = 0; i < Home.arr_cart.size(); i ++) {
            totalpayment += Home.arr_cart.get(i).getPricepd();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txttotalpayment.setText(decimalFormat.format(totalpayment) + " $");

    }

    private void CheckData() {
        if (Home.arr_cart.size() <=0) {
            cartAdapter.notifyDataSetChanged();
            txtnotification.setVisibility(View.VISIBLE);
            lvcart.setVisibility(View.INVISIBLE);
        } else {
            cartAdapter.notifyDataSetChanged();
            txtnotification.setVisibility(View.INVISIBLE);
            lvcart.setVisibility(View.VISIBLE);
        }
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbarcart);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarcart.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void map() {
        lvcart = (ListView) findViewById(R.id.listview_cart);
        txtnotification = (TextView) findViewById(R.id.textview_notification);
        txttotalpayment = (TextView) findViewById(R.id.textviewtotalpayment);
        btnpayment =(Button) findViewById(R.id.button_payment);
        btncontinueshopping = (Button) findViewById(R.id.button_continue_purchase);
        toolbarcart = (Toolbar) findViewById(R.id.toolbar_cart);
        cartAdapter = new CartAdapter(CartActivity.this, Home.arr_cart);

        lvcart.setAdapter(cartAdapter);


    }
}