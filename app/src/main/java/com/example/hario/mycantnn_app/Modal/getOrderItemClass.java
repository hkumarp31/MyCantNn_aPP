package com.example.hario.mycantnn_app.Modal;

/**
 * Created by Hemant Kumar on 2/8/2018.
 */

public class getOrderItemClass {
    private String image;
    private String data;
    private int totalCost;
    private int count;
    private int price;
    private String id;
    private String status;
    private String ImageUrl;
    private String user;
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public getOrderItemClass() {

    }
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public getOrderItemClass(String image, String data, int totalCost, int count, int price, String id, String Status) {

        this.image = image;
        this.data = data;
        this.totalCost = totalCost;
        this.count = count;
        this.price = price;
        this.id = id;
        this.status=Status;
    }
    public getOrderItemClass(String image, String data, int totalCost, int count, int price, String id, String Status,String user) {

        this.image = image;
        this.data = data;
        this.totalCost = totalCost;
        this.count = count;
        this.price = price;
        this.id = id;
        this.status=Status;
        this.user=user;
    }
    public getOrderItemClass(String image, String data, int totalCost, int count, int price, String id, String Status,String user,String key) {

        this.image = image;
        this.data = data;
        this.totalCost = totalCost;
        this.count = count;
        this.price = price;
        this.id = id;
        this.status=Status;
        this.user=user;
        this.key=key;
    }
    public getOrderItemClass( String data, int totalCost, int count, int price, String id, String Status) {

        this.data = data;
        this.totalCost = totalCost;
        this.count = count;
        this.price = price;
        this.id = id;
        this.status=Status;
    }
    public getOrderItemClass(String image,String data,String status){
        this.image=image;
        this.data=data;
        this.status=status;
    }
}
