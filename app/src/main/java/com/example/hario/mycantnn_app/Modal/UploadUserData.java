package com.example.hario.mycantnn_app.Modal;

/**
 * Created by hario on 2/6/2018.
 */

public class UploadUserData {
    private String Name;
    private String Email;
    private String Contact;
    private String Image;

    public UploadUserData(String name, String email, String contact, String image) {
        Name = name;
        Email = email;
        Contact = contact;
        Image = image;
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
