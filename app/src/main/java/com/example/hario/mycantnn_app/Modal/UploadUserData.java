package com.example.hario.mycantnn_app.Modal;

/**
 * Created by hario on 2/6/2018.
 */

public class UploadUserData {
    private String Name;
    private String Email;
    private String Contact;
    private String Image;
    private String Uuid;
    private String Canteen;
    private String UserName;

    public UploadUserData(String name, String email, String contact, String image, String User) {
        Name = name;
        Email = email;
        Contact = contact;
        Image = image;
        UserName = User;
    }

    public UploadUserData(String name, String email, String contact, String image, String uuid, String canteen) {
        Name = name;
        Email = email;
        Contact = contact;
        Image = image;
        Uuid = uuid;
        Canteen = canteen;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUuid() {
        return Uuid;
    }

    public void setUuid(String uuid) {

        Uuid = uuid;
    }

    public String getCanteen() {
        return Canteen;
    }

    public void setCanteen(String canteen) {
        Canteen = canteen;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
