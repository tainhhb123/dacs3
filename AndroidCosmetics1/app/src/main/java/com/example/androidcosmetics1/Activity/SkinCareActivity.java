package com.example.androidcosmetics1.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.androidcosmetics1.Adapter.SkinCareAdapter;
import com.example.androidcosmetics1.Model.Product;
import com.example.androidcosmetics1.R;
import com.example.androidcosmetics1.util.CheckConnect;
import com.example.androidcosmetics1.util.Server;
import com.squareup.picasso.Request;
import com.android.volley.Request.Method;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SkinCareActivity extends AppCompatActivity {

    Toolbar toolbarsc;
    ListView lvsc;
    SkinCareAdapter skinCareAdapter;
    ArrayList<Product> arr_sc;
    int idsc = 0;
    int page = 1;
    View footerview ;
    boolean isLoading = false;
    boolean limitData = false;
    mHandler mHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skin_care);

        map();

        lvsc.setAdapter(skinCareAdapter);

        if (CheckConnect.haveNetworkConnection(getApplicationContext())) {
            GetIdCategory();
            ActionToolBar();
            GetData(1);
            LoadMoreData();
            
        }
        else {
            CheckConnect.ShowToast_Short(getApplicationContext(), "No intenet");
            finish();
        }
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

    private void LoadMoreData() {

        lvsc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), ProductDetails.class);
                intent.putExtra("ProductInformation", arr_sc.get(i));
                startActivity(intent);
            }
        });

        lvsc.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int FirstItem, int VisibleItem, int TotalItem) {
                if (FirstItem + VisibleItem == TotalItem && TotalItem != 0 && isLoading == false && limitData == false) {
                    isLoading = true;
                    ThreadData threadData = new ThreadData();
                    threadData.start();

                }
            }
        });
    }


    private void GetData(int Page) {
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
           String duongdan = Server.duongdanskincare;
            String url = duongdan.concat(String.valueOf(Page));
//            String url = "http://192.168.1.8/server/getproduct.php?page=" + String.valueOf(Page);
            StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    int id = 0;
                    String Namesc = "";
                    int Pricesc = 0 ;
                    String Imagesc = "";
                    String Describesc = "";
                    int IDpdsc;
                    if (response != null && response.length() != 2) {
                        lvsc.removeFooterView(footerview);
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i <jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                id = jsonObject.getInt("id");
                                Namesc = jsonObject.getString("pname");
                                Pricesc = jsonObject.getInt("pprice");
                                Imagesc = jsonObject.getString("pimage");
                                Describesc = jsonObject.getString("pdescribe");
                                IDpdsc = jsonObject.getInt("pid");
                                arr_sc.add(new Product(id,Namesc, Pricesc, Imagesc,Describesc, IDpdsc));
                                skinCareAdapter.notifyDataSetChanged();

                                Log.e("TanTai", "data: "+jsonObject);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        limitData = true;
                        lvsc.removeFooterView(footerview);
                        CheckConnect.ShowToast_Short(getApplicationContext(), "Data null");
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
                    HashMap<String, String> param = new HashMap<String, String>();
                    param.put("idproduct", String.valueOf(idsc));
                    return param;
                }
            };
            requestQueue.add(stringRequest);
        }

    private void ActionToolBar() {
        setSupportActionBar(toolbarsc);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarsc.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void GetIdCategory() {
        idsc = getIntent().getIntExtra("id", -1);
        Log.d("valuecategory", idsc+ "");
    }

    private void map() {



        toolbarsc = (Toolbar) findViewById(R.id.toolbar_skincare);
        lvsc = (ListView) findViewById(R.id.listview_skincare);
        arr_sc = new ArrayList<>();
        skinCareAdapter = new SkinCareAdapter(getApplicationContext(), arr_sc);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerview = inflater.inflate(R.layout.progressbar, null);
        mHandler = new mHandler();

        Log.e("TanTai", "arr_sc size: " + arr_sc.size());

    }
    public class mHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 0:
                    lvsc.addFooterView(footerview);
                    break;
                case 1:
                    GetData(++page);
                    isLoading = false;
                    break;
            }
            super.handleMessage(msg);
        }
    }
    public class ThreadData extends Thread{
        @Override
        public void run() {
            mHandler.sendEmptyMessage(0);
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message = mHandler.obtainMessage(1);
            mHandler.sendMessage(message);
            super.run();
        }
    }

}
//    private void GetData(int Page) {
//        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//        String duongdan = Server.duongdanskincare+String.valueOf(Page);
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                int id = 0;
//                String Namesc = "";
//                int Pricesc = 0 ;
//                String Imagesc = "";
//                String Describesc = "";
//                int IDpdsc;
//                if (response != null) {
//                    try {
//                        JSONArray jsonArray = new JSONArray(response);
//                        for (int i = 0; i <jsonArray.length(); i++) {
//                            JSONObject jsonObject = jsonArray.getJSONObject(i);
//                            id = jsonObject.getInt("id");
//                            Namesc = jsonObject.getString("pname");
//                            Pricesc = jsonObject.getInt("pprice");
//                            Imagesc = jsonObject.getString("pimage");
//                            Describesc = jsonObject.getString("pdescribe");
//                            IDpdsc = jsonObject.getInt("pid");
//                            arr_sc.add(new Product(id,Namesc, Pricesc, Imagesc,Describesc, IDpdsc ));
//                            skinCareAdapter.notifyDataSetChanged();
//
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        }){
//            @Nullable
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                HashMap<String, String> param = new HashMap<String, String>();
//                param.put("idproduct", String.valueOf(idsc));
//                return param;
//            }
//        };
//        requestQueue.add(stringRequest);
//    }