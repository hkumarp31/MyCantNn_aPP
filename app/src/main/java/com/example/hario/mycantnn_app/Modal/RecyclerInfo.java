package com.example.hario.mycantnn_app.Modal;

import android.os.Parcel;
import android.os.Parcelable;


public class RecyclerInfo implements Parcelable {
    public static final Creator<RecyclerInfo> CREATOR = new Creator<RecyclerInfo>() {
        @Override
        public RecyclerInfo createFromParcel(Parcel in) {
            return new RecyclerInfo(in);
        }

        @Override
        public RecyclerInfo[] newArray(int size) {
            return new RecyclerInfo[size];
        }
    };
    private String Data;
    private int Count;
    private int Cost;
    private int TotalCost;
    private String Image;

    public RecyclerInfo() {
    }

    public RecyclerInfo(String image, String data, int count, int cost, int totalCost) {
        Image = image;
        Data = data;
        Count = count;
        Cost = cost;
        TotalCost = totalCost;
    }

    protected RecyclerInfo(Parcel in) {
        Data = in.readString();
        Count = in.readInt();
        Cost = in.readInt();
        TotalCost = in.readInt();
        Image = in.readString();
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public int getCost() {
        return Cost;
    }

    public void setCost(int cost) {
        Cost = cost;
    }

    public int getTotalCost() {
        return TotalCost;
    }

    public void setTotalCost(int totalCost) {
        TotalCost = totalCost;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Data);
        parcel.writeInt(Count);
        parcel.writeInt(Cost);
        parcel.writeInt(TotalCost);
        parcel.writeString(Image);
    }
}
