package com.example.androidcosmetics1.Model;

import java.io.Serializable;

public class Product implements Serializable {
    public int ID;
    public String Product_name;
    public Integer Product_price;
    public String Product_image;
    public String Product_describe;
    public int Product_ID;

    public Product(int ID, String product_name, Integer product_price, String product_image, String product_describe, int product_ID) {
        this.ID = ID;
        Product_name = product_name;
        Product_price = product_price;
        Product_image = product_image;
        Product_describe = product_describe;
        this.Product_ID = product_ID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getProduct_name() {
        return Product_name;
    }

    public void setProduct_name(String product_name) {
        Product_name = product_name;
    }

    public Integer getProduct_price() {
        return Product_price;
    }

    public void setProduct_price(Integer product_price) {
        Product_price = product_price;
    }

    public String getProduct_image() {
        return Product_image;
    }

    public void setProduct_image(String product_image) {
        Product_image = product_image;
    }

    public String getProduct_describe() {
        return Product_describe;
    }

    public void setProduct_describe(String product_describe) {
        Product_describe = product_describe;
    }

    public int getProduct_ID() {
        return Product_ID;
    }

    public void setProduct_ID(int product_ID) {
        Product_ID = product_ID;
    }
}
