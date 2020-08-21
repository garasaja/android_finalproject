package com.example.pproject.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Theme {
    private int id;
    private String name;
    private String intro;
    private String genre;
    private int level;
    private String timeLimit;
    private int rating;
    private int storeId;
    private String themeImg;
    private Timestamp createDate;
}
