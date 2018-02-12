package com.example.hario.mycantnn_app.Modal;

/**
 * Created by Hariom gupta on 1/31/2018.
 */

public class RecyclerItemInfo {
    private String Image;
    private String Data;
    private int Cost;
    private int Count;
    private int Total;

    public RecyclerItemInfo() {
    }

    public RecyclerItemInfo(String image, String title, int price, int count, int total) {
        Image = image;
        Data = title;
        Cost = price;
        Count = count;
        Total = total;
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

    public void setData(String title) {
        Data = title;
    }

    public int getCost() {
        return Cost;
    }

    public void setCost(int price) {
        Cost = price;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int total) {
        Total = total;
    }
}
