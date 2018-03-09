package com.example.hario.mycantnn_app.Modal;

/**
 * Created by Hemant Kumar on 2/8/2018.
 */

public class getOrderItemClass {
    private String Image;
    private String Data;
    private int Count;
    private int price;
    private int Total;
    private String OrderID;

    public getOrderItemClass( int count, String data, String id, String image ,int price , int totalCost) {
        this.Count = count;
        this.Data = data;
        this.OrderID = id;
        this.Image=image;
        this.price = price;
        this.Total = totalCost;


    }
    public getOrderItemClass(){}

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

    public int getCount() {  return Count;  }

    public void setCount(int count) {
        Count = count;
    }

    public int getCost() {
        return price;
    }

    public void setCost(int cost) {
        price = cost;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int total) {
        Total = total;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }
}
