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
public class Review {
    private int id;
    private int userId;
    private int storeId;
    private int themeId;
    private int rating;
    private String content;
    private Timestamp createDate;
}
