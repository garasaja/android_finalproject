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
public class Favorite {
    private int id;
    private int userId;
    private int pickId;
    private String type;
    private Timestamp createDate;
}
