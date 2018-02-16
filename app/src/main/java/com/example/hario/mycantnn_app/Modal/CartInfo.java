package com.example.hario.mycantnn_app.Modal;


public class CartInfo {
    private String image;
    private String name;
    private int Total;
    private int Count;
    private int Price;

    public CartInfo(String image, String name, int count, int price, int total) {
        this.image = image;
        this.name = name;
        this.Total = total;
        this.Count = count;
        this.Price = price;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
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
