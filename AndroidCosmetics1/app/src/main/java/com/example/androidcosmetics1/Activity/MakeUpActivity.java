package com.example.androidcosmetics1.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.androidcosmetics1.Adapter.MakeUpAdapter;
import com.example.androidcosmetics1.Model.Product;
import com.example.androidcosmetics1.R;
import com.example.androidcosmetics1.util.CheckConnect;
import com.example.androidcosmetics1.util.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MakeUpActivity extends AppCompatActivity {

    Toolbar toolbarmu;
    ListView lvmu;
    MakeUpAdapter makeupAdapter;
    ArrayList<Product> arr_mu;
    int idmu = 0;
    int page = 1;
    View footerview ;
    boolean isLoading = false;
    boolean limitData = false;
    static mHandler mHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_up);

        map();

        lvmu.setAdapter(makeupAdapter);

        if (CheckConnect.haveNetworkConnection(getApplicationContext())) {

            GetIdCategory();
            ActionToolBar();
            GetData(page);
            LoadMoreData();
        } else {
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

        lvmu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), ProductDetails.class);
                intent.putExtra("ProductInformation", arr_mu.get(i));
                startActivity(intent);
            }
        });

        lvmu.setOnScrollListener(new AbsListView.OnScrollListener() {
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
        String duongdan = Server.duongdanmakeup;
        String url = duongdan.concat(String.valueOf(Page));
//            String url = "http://192.168.1.8/server/getproduct.php?page=" + String.valueOf(Page);
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id = 0;
                String Namemu = "";
                int Pricemu = 0 ;
                String Imagemu = "";
                String Describemu = "";
                int IDpdmu;
                if (response != null && response.length() != 2) {
                   lvmu.removeFooterView(footerview);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i <jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            Namemu = jsonObject.getString("pname");
                            Pricemu = jsonObject.getInt("pprice");
                            Imagemu = jsonObject.getString("pimage");
                            Describemu = jsonObject.getString("pdescribe");
                            IDpdmu = jsonObject.getInt("pid");
                            arr_mu.add(new Product(id,Namemu, Pricemu, Imagemu,Describemu, IDpdmu));
                            makeupAdapter.notifyDataSetChanged();

                            Log.e("TanTai", "data: "+jsonObject);

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    limitData = true;
                    lvmu.removeFooterView(footerview);
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
                param.put("idproduct", String.valueOf(idmu));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }


    private void ActionToolBar() {
        setSupportActionBar(toolbarmu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarmu.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void GetIdCategory() {
        idmu = getIntent().getIntExtra("id", -1);
//        Log.d("valuecategory", idmu+ "");
    }

    private void map() {


        toolbarmu = (Toolbar) findViewById(R.id.toolbar_makeup);
        lvmu = (ListView) findViewById(R.id.listview_makeup);
        arr_mu = new ArrayList<>();
        makeupAdapter = new MakeUpAdapter(getApplicationContext(), arr_mu);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerview = inflater.inflate(R.layout.progressbar, null);
        mHandler = new mHandler();


    }

    public class mHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 0:
                    lvmu.addFooterView(footerview);
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
            MakeUpActivity.mHandler.sendEmptyMessage(0);
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message = MakeUpActivity.mHandler.obtainMessage(1);
            MakeUpActivity.mHandler.sendMessage(message);
            super.run();
        }
    }
}

