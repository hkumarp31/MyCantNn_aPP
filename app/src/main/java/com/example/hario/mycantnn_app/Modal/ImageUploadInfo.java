package com.example.hario.mycantnn_app.Modal;

/**
 * Created by Hemant Kumar on 2/3/2018.
 */

public class ImageUploadInfo {
    private String Data;
    private int count;
    private int cost;
    private int totalcost;
    private String image;

    public ImageUploadInfo(String data, int count, int cost, int totalcost, String image) {
        Data = data;
        this.count = count;
        this.cost = cost;
        this.totalcost = totalcost;
        this.image = image;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getTotalcost() {
        return totalcost;
    }

    public void setTotalcost(int totalcost) {
        this.totalcost = totalcost;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
