package com.example.hario.mycantnn_app.Check.Card;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hario on 1/30/2018.
 */

public class statusinfo implements Parcelable {

    public static final Creator<statusinfo> CREATOR = new Creator<statusinfo>() {
        @Override
        public statusinfo createFromParcel(Parcel in) {
            return new statusinfo(in);
        }

        @Override
        public statusinfo[] newArray(int size) {
            return new statusinfo[size];
        }
    };
    private String image;
    private String name;
    private String id;

    public statusinfo(String image, String name, String id) {
        this.image = image;
        this.name = name;
        this.id = id;
    }

    protected statusinfo(Parcel in) {
        image = in.readString();
        name = in.readString();
        id = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(image);
        parcel.writeString(name);
        parcel.writeString(id);
    }
}
