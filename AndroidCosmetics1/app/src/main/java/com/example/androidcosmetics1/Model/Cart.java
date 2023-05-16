package com.example.androidcosmetics1.Model;

public class Cart {
    public int idpd;
    public String namepd;
    public long pricepd;
    public String imagepd;
    public int quantitysp;

    public Cart(int idpd, String namepd, long pricepd, String imagepd, int quantitysp) {
        this.idpd = idpd;
        this.namepd = namepd;
        this.pricepd = pricepd;
        this.imagepd = imagepd;
        this.quantitysp = quantitysp;
    }

    public int getIdpd() {
        return idpd;
    }

    public void setIdpd(int idpd) {
        this.idpd = idpd;
    }

    public String getNamepd() {
        return namepd;
    }

    public void setNamepd(String namepd) {
        this.namepd = namepd;
    }

    public long getPricepd() {
        return pricepd;
    }

    public void setPricepd(long pricepd) {
        this.pricepd = pricepd;
    }

    public String getImagepd() {
        return imagepd;
    }

    public void setImagepd(String imagepd) {
        this.imagepd = imagepd;
    }

    public int getQuantitysp() {
        return quantitysp;
    }

    public void setQuantitysp(int quantitysp) {
        this.quantitysp = quantitysp;
    }
}
