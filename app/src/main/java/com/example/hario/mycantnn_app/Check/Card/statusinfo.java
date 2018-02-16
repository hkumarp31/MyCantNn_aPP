package com.example.hario.mycantnn_app.Check.Card;

/**
 * Created by hario on 1/30/2018.
 */

public class statusinfo {
    private String Image;
    private String Data;
    private String id;
    private int TotalCost;
    private int Count;
    private int Price;

    public statusinfo(String image, String data, String id, int totalcost, int count,int cost) {
        this.Image = image;
        this.Data = data;
        this.id = id;
       this.TotalCost = totalcost;
       this.Count = count;
       this.Price= cost;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTotalCost() {
        return TotalCost;
    }

    public void setTotalCost(int totalCost) {
        TotalCost = totalCost;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }
}
