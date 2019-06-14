package com.spring.web.model.request;

public class ImportPhoto {

    private Integer id;

    private String url;

    private Integer user_id;

    private String name;

    public ImportPhoto() {
    }

    public ImportPhoto(Integer id, String url, Integer user_id, String name) {
        this.id = id;
        this.url = url;
        this.user_id = user_id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
