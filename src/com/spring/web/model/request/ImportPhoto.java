package com.spring.web.model.request;

public class ImportPhoto {

    private Integer id;

    private String url;

    private String url1;

    private Integer user_id;

    private String name;

    private String coordinate;

    private Object object;

    private String photo_color;


    public ImportPhoto() {
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

    public String getUrl1() {
        return url1;
    }

    public void setUrl1(String url1) {
        this.url1 = url1;
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

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getPhoto_color() {
        return photo_color;
    }

    public void setPhoto_color(String photo_color) {
        this.photo_color = photo_color;
    }

    @Override
    public String toString() {
        return "ImportPhoto{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", url1='" + url1 + '\'' +
                ", user_id=" + user_id +
                ", name='" + name + '\'' +
                ", coordinate='" + coordinate + '\'' +
                ", object=" + object +
                ", photo_color='" + photo_color + '\'' +
                '}';
    }
}
