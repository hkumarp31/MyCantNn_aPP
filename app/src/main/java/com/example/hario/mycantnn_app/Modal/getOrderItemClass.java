package com.example.hario.mycantnn_app.Modal;

/**
 * Created by Hemant Kumar on 2/8/2018.
 */

public class getOrderItemClass {
    private String Data;
    private int Count;
    private int Cost;
    private int Total;
    private String OrderID;

    public getOrderItemClass(String data, int count, int cost, int total, String orderID) {
        Data = data;
        Count = count;
        Cost = cost;
        Total = total;
        OrderID = orderID;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }

    public int getCost() {
        return Cost;
    }

    public void setCost(int cost) {
        Cost = cost;
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
