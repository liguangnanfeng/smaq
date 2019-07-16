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

    private Integer flag;

    public ImportPhoto() {
    }

    public ImportPhoto(Integer id, String url, String url1, Integer user_id, String name, String coordinate, Object object, String photo_color, Integer flag) {
        this.id = id;
        this.url = url;
        this.url1 = url1;
        this.user_id = user_id;
        this.name = name;
        this.coordinate = coordinate;
        this.object = object;
        this.photo_color = photo_color;
        this.flag = flag;
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

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
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
                ", flag=" + flag +
                '}';
    }
}
