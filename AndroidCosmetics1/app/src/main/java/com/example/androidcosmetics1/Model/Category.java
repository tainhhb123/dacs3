package com.example.androidcosmetics1.Model;

public class Category {
    public int id;
    public String product_name;
    public String product_image;

    public Category(int id, String product_name, String product_image) {
        this.id = id;
        this.product_name = product_name;
        this.product_image = product_image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }
}
