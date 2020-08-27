package com.example.pproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    private Float location;
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

}
