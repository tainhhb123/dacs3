package com.example.androidcosmetics1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;


import com.example.androidcosmetics1.Activity.CartActivity;
import com.example.androidcosmetics1.Activity.ChatGPT;
import com.example.androidcosmetics1.Activity.ContactActivity;
import com.example.androidcosmetics1.Activity.InformationActivity;
import com.example.androidcosmetics1.Activity.MakeUpActivity;
import com.example.androidcosmetics1.Activity.SkinCareActivity;
import com.example.androidcosmetics1.Adapter.CategoryAdapter;
import com.example.androidcosmetics1.Adapter.ProductAdapter;
import com.example.androidcosmetics1.Model.Cart;
import com.example.androidcosmetics1.Model.Category;
import com.example.androidcosmetics1.Model.Product;
import com.example.androidcosmetics1.util.CheckConnect;
import com.example.androidcosmetics1.util.Server;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    Toolbar toolbar;
    ViewFlipper flipper;
    RecyclerView recyclerview;
    NavigationView navigationView;
    ListView listView;
    DrawerLayout drawerLayout;
    ArrayList<Category> arr_category;
    CategoryAdapter categoryAdapter;
    int id = 0;
    String name = "";
    String image = "";

    ArrayList<Product> arr_product;
    ProductAdapter productAdapter;

    public static ArrayList<Cart> arr_cart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

//        ipadd = (TextView) findViewById(R.id.ipad);
//        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
//        ipadd.setText(Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress())) ;
//        try {
//            ipadd = (TextView) findViewById(R.id.ipad);
//            WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
//            int ipAddress = wifiManager.getConnectionInfo().getIpAddress();
//            String ipString = InetAddress.getByAddress(
//                    ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(ipAddress).array()).getHostAddress();
//            ipadd.setText(ipString);
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }


        home();
        if (CheckConnect.haveNetworkConnection(getApplicationContext())) {
            ActionBar();
            ActionViewFlipper();
            GetCategoryData();
            GetNewProduct();
            CatchOnItemListView();
        } else {
            CheckConnect.ShowToast_Short(getApplicationContext(), "Please check the connection again...");
            finish();
        }


    }

    public void chat(View view) {
        Intent intent = new Intent(Home.this, ChatGPT.class);
        startActivity(intent);
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

    private void CatchOnItemListView() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        if (CheckConnect.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(Home.this, Home.class);
                            startActivity(intent);

                        } else {
                            CheckConnect.ShowToast_Short(getApplicationContext(), "Please check your connection again...");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        if (CheckConnect.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(Home.this, SkinCareActivity.class);
                            intent.putExtra("id", arr_category.get(i).getId());
                            startActivity(intent);



                        } else {
                            CheckConnect.ShowToast_Short(getApplicationContext(), "Please check your connection again...");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        if (CheckConnect.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(Home.this, MakeUpActivity.class);
                            intent.putExtra("id", arr_category.get(i).getId());
                            startActivity(intent);

                        } else {
                            CheckConnect.ShowToast_Short(getApplicationContext(), "Please check your connection again...");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        if (CheckConnect.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(Home.this, ContactActivity.class);
                            intent.putExtra("id", arr_category.get(i).getId());
                            startActivity(intent);

                        } else {
                            CheckConnect.ShowToast_Short(getApplicationContext(), "Please check your connection again...");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 4:
                        if (CheckConnect.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(Home.this, InformationActivity.class);
                            intent.putExtra("id", arr_category.get(i).getId());
                            startActivity(intent);

                        } else {
                            CheckConnect.ShowToast_Short(getApplicationContext(), "Please check your connection again...");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
            }
        });
    }


    private void GetNewProduct() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.duongdannewproduct, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    int ID = 0;
                    String Productname = "";
                    Integer Productprice = 0;
                    String Productimage = "";
                    String Productdescribe = "";
                    int IDproduct = 0;

                    for (int i = 0; i < response.length(); i ++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            ID = jsonObject.getInt("id");
                            Productname = jsonObject.getString("npname");
                            Productprice = jsonObject.getInt("npprice");
                            Productimage = jsonObject.getString("npimage");
                            Productdescribe = jsonObject.getString("npdescribe");
                            IDproduct = jsonObject.getInt("product_id");
                            arr_product.add(new Product(ID, Productname, Productprice, Productimage, Productdescribe, IDproduct));
                            productAdapter.notifyDataSetChanged();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);

    }

    private void GetCategoryData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.Duongdan, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                if (response != null) {
                    for (int i = 0 ; i < response.length(); i ++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            name = jsonObject.getString("name");
                            image = jsonObject.getString("image");
                            arr_category.add(new Category(id,name,image));
                            categoryAdapter.notifyDataSetChanged();
//                            Log.e("Test", "data: "+jsonObject);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    arr_category.add(3, new Category(0, "Contact", "https://symbols.vn/wp-content/uploads/2021/11/Bieu-tuong-contact-pho-bien.jpg"));
                    arr_category.add(4, new Category(0, "Information", "https://static.vecteezy.com/system/resources/previews/005/747/906/original/info-icon-template-information-icon-colorful-free-vector.jpg"));

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnect.ShowToast_Short(getApplicationContext(), error.toString());
            }
        });

        requestQueue.add(jsonArrayRequest);
    }

    private void ActionViewFlipper() {
        ArrayList<String> imageList = new ArrayList<>();
        imageList.add("https://artia.vn/wp-content/uploads/2020/11/my-pham-Skincare-1200x800.jpg");
        imageList.add("https://artia.vn/wp-content/uploads/2020/11/chup-anh-dep-cho-mau-quang-cao-my-pham.jpg");
        imageList.add("https://png.pngtree.com/thumb_back/fh260/back_our/20190623/ourmid/pngtree-new-announcement-on-the-beauty-makeup-taobao-image_239781.jpg");

        for(int i = 0 ; i < imageList.size(); i++){
            ImageView imageView = new ImageView(getApplicationContext());
//            Picasso.with(getApplicationContext()).load(adv_home.get(i)).into(imageView);
            Picasso.get().load(imageList.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            flipper.addView(imageView);
        }
//        for (String imageName : imageList) {
//            ImageView imageView = new ImageView(getApplicationContext());
//            Picasso picasso = Picasso.get();
//            picasso.load(getResources().getIdentifier(imageName, "drawable", getPackageName())).into(imageView);
//            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//            flipper.addView(imageView);
//        }
        flipper.setFlipInterval(3000);
        flipper.setAutoStart(true);

        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        flipper.setInAnimation(animation_slide_in);
        flipper.setOutAnimation(animation_slide_out);
    }

    private void ActionBar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_menu_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }


    private void home() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_home);
        flipper = (ViewFlipper) findViewById(R.id.flipper_home);
        recyclerview = (RecyclerView) findViewById(R.id.recycleview_home);
        navigationView = (NavigationView) findViewById(R.id.navigation_home);
        listView = (ListView) findViewById(R.id.listview_home);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        arr_category = new ArrayList<>();
        arr_category.add(0, new Category(0, "Main page", "https://cdn-icons-png.flaticon.com/512/25/25694.png"));

        categoryAdapter = new CategoryAdapter(arr_category, getApplicationContext());
        listView.setAdapter(categoryAdapter);

        arr_product = new ArrayList<>();
        productAdapter = new ProductAdapter(getApplicationContext(), arr_product);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        recyclerview.setAdapter(productAdapter);

        if(arr_cart != null) {

        } else {
            arr_cart = new ArrayList<>();
        }

    }
}