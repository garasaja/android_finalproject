package com.example.pproject.model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Store {

    private int id;
    private String name;
    private String info;
    private int rating;
    private String homepage;
    private String location;
    private String mapLong;
    private String mapLat;
    private String storeImg;
    private Timestamp createDate;
}
