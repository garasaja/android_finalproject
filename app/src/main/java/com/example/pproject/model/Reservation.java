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
public class Reservation {
    private int id;
    private Timestamp reserveDate;
    private int userId;
    private int storeId;
    private int themeId;
    private Timestamp createDate;
}
