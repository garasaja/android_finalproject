package com.example.pproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Store {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("intro")
    @Expose
    private String intro;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("info")
    @Expose
    private String info;
    @SerializedName("rating")
    @Expose
    private Integer rating;
    @SerializedName("homepage")
    @Expose
    private String homepage;
    @SerializedName("location")
    @Expose
    private Object location;
    @SerializedName("mapLong")
    @Expose
    private Float mapLong;
    @SerializedName("mapLat")
    @Expose
    private Float mapLat;
    @SerializedName("storeImg")
    @Expose
    private String storeImg;
    @SerializedName("createDate")
    @Expose
    private String createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public Object getLocation() {
        return location;
    }

    public void setLocation(Object location) {
        this.location = location;
    }

    public Float getMapLong() {
        return mapLong;
    }

    public void setMapLong(Float mapLong) {
        this.mapLong = mapLong;
    }

    public Float getMapLat() {
        return mapLat;
    }

    public void setMapLat(Float mapLat) {
        this.mapLat = mapLat;
    }

    public String getStoreImg() {
        return storeImg;
    }

    public void setStoreImg(String storeImg) {
        this.storeImg = storeImg;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
