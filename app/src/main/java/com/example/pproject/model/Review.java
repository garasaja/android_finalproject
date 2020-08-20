package com.example.pproject.model;

import java.sql.Timestamp;

public class Review {
    private int id;
    private int userId;
    private int storeId;
    private int themeId;
    private int rating;
    private String content;
    private Timestamp createDate;
}
