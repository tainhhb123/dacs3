package com.example.androidcosmetics1.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.androidcosmetics1.Home;
import com.example.androidcosmetics1.R;
import com.example.androidcosmetics1.util.CheckConnect;
import com.example.androidcosmetics1.util.Server;
import com.squareup.picasso.Request;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CustomerInformation extends AppCompatActivity {

    EditText edtnamecustomer, edtphone, edtemail, edtaddress;
    Button btnconfirm, btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_information);
        map();
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        if (CheckConnect.haveNetworkConnection(getApplicationContext())){
            EventButton();
        } else {
            CheckConnect.ShowToast_Short(getApplicationContext(), "No internet");
        }
    }

    private void EventButton() {
        btnconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = edtnamecustomer.getText().toString().trim();
                final String phone = edtphone.getText().toString().trim();
                final String email = edtemail.getText().toString().trim();
                final String address = edtaddress.getText().toString().trim();
                if (name.length()>0 && phone.length()>0 && email.length()>0 && address.length()>0) {
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.POST, Server.duongdanorder, new Response.Listener<String>() {
                        @Override
                        public void onResponse(final String ordercode) {
                            Log.d("maorder", ordercode);
                            if (Integer.parseInt(ordercode) >0) {
                                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                                StringRequest request = new StringRequest(com.android.volley.Request.Method.POST, Server.duongdanorderdetails, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        if (response.equals("1")) {
                                            Home.arr_cart.clear();
                                            CheckConnect.ShowToast_Short(getApplicationContext(), "Add order data successfully");
                                            Intent intent = new Intent(getApplicationContext(), Home.class);
                                            startActivity(intent);
                                            Toast.makeText(getApplicationContext(), "Please continue order to shop", Toast.LENGTH_SHORT).show();
//                                            CheckConnect.ShowToast_Short(getApplicationContext(), "Please continue order to shop");
                                        } else {
                                            CheckConnect.ShowToast_Short(getApplicationContext(), "Cart data fail");
//                                            CheckConnect.ShowToast_Short(getApplicationContext(), "Cart data fail");

                                        }
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                }){
                                    @Nullable
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        JSONArray jsonArray = new JSONArray();
                                        for (int i = 0; i< Home.arr_cart.size(); i++) {
                                            JSONObject jsonObject = new JSONObject();
                                            try {
                                                jsonObject.put("ordercode", ordercode);
                                                jsonObject.put("productorder", Home.arr_cart.get(i).getIdpd());
                                                jsonObject.put("name", Home.arr_cart.get(i).getNamepd());
                                                jsonObject.put("price", Home.arr_cart.get(i).getPricepd());
                                                jsonObject.put("quantity", Home.arr_cart.get(i).getQuantitysp());

                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            jsonArray.put(jsonObject);
                                        }
                                        HashMap<String, String> hashMap = new HashMap<String, String>();
                                        hashMap.put("json", jsonArray.toString());
                                        return hashMap;
                                    }
                                };
                                queue.add(request);
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String, String> hashMap = new HashMap<String , String>();
                            hashMap.put("namecustomer", name);
                            hashMap.put("phonecustomer", phone);
                            hashMap.put("emailcustomer", email);
                            hashMap.put("addresscustomer", address);
                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);
                } else {
                    CheckConnect.ShowToast_Short(getApplicationContext(), "No Internet");
                }
            }
        });
    }

    private void map() {
        edtnamecustomer = (EditText) findViewById(R.id.edittextnamecustomer);
        edtemail = (EditText) findViewById(R.id.edittextemailcustomer);
        edtphone = (EditText) findViewById(R.id.edittextphonecustomer);
        edtaddress = (EditText) findViewById(R.id.edittextaddresscustomer);
        btnconfirm = (Button) findViewById(R.id.buttonconfirm);
        btnback = (Button) findViewById(R.id.buttonback);

    }
}