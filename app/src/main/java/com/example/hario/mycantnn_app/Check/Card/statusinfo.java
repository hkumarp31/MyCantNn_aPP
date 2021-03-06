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
    private String status;
    private String user;
    private String key;
    private String dateandtime;

    public statusinfo(String dateandtime) {
        this.dateandtime = dateandtime;
    }

    public String getDateandtime() {
        return dateandtime;
    }

    public void setDateandtime(String dateandtime) {
        this.dateandtime = dateandtime;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public statusinfo(String image, String data, String id, int totalcost, int count, int cost, String status,String user, String key) {
        this.Image = image;
        this.Data = data;
        this.id = id;
       this.TotalCost = totalcost;
       this.Count = count;
       this.Price= cost;
        this.status = status;
        this.user=user;
        this.key=key;
    }
    public statusinfo(String image, String data, String id, int totalcost, int count, int cost, String status,String user, String key,String dateandtime) {
        this.Image = image;
        this.Data = data;
        this.id = id;
        this.TotalCost = totalcost;
        this.Count = count;
        this.Price= cost;
        this.status = status;
        this.user=user;
        this.key=key;
        this.dateandtime=dateandtime;
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
