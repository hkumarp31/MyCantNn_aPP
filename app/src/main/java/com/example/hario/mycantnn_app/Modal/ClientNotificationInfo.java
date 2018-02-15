package com.example.hario.mycantnn_app.Modal;

public class ClientNotificationInfo{

    private int ClientNotiImage;
    private String ClientNotiTitle;
    private String ClientNotiStatus;

    public ClientNotificationInfo(int clientNotiImage, String clientNotiTitle, String clientNotiStatus) {
        ClientNotiImage = clientNotiImage;
        ClientNotiTitle = clientNotiTitle;
        ClientNotiStatus = clientNotiStatus;
    }

    public int getClientNotiImage() {
        return ClientNotiImage;
    }

    public void setClientNotiImage(int clientNotiImage) {
        ClientNotiImage = clientNotiImage;
    }

    public String getClientNotiTitle() {
        return ClientNotiTitle;
    }

    public void setClientNotiTitle(String clientNotiTitle) {
        ClientNotiTitle = clientNotiTitle;
    }

    public String getClientNotiStatus() {
        return ClientNotiStatus;
    }

    public void setClientNotiStatus(String clientNotiStatus) {
        ClientNotiStatus = clientNotiStatus;
    }
}
