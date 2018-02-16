package com.example.hario.mycantnn_app.Check.Card;

/**
 * Created by hario on 1/30/2018.
 */

public class statusinfo {
    private String image;
    private String name;
    private String id;
    private String price;
    private String quantity;

    public statusinfo(String image, String name, String id, String price, String quantity) {

        this.image = image;
        this.name = name;
        this.id = id;
        this.price = price;
        this.quantity = quantity;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
