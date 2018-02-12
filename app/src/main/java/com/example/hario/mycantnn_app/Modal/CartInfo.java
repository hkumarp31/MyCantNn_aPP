package com.example.hario.mycantnn_app.Modal;


public class CartInfo {
    private String image;
    private String name;
    private int Total;

    public CartInfo(String image, String name, int total) {
        this.image = image;
        this.name = name;
        this.Total = total;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int total) {
        Total = total;
    }


}
