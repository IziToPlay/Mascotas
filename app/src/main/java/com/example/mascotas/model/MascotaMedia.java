package com.example.mascotas.model;

public class MascotaMedia {
    private long id;
    private int caption;
    private String url;

    public MascotaMedia(long id, int caption, String url) {
        this.id = id;
        this.caption = caption;
        this.url = url;
    }

    public MascotaMedia() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCaption() {
        return caption;
    }

    public void setCaption(int caption) {
        this.caption = caption;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
