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
public class History {
    private int id;
    private int userId;
    private int themeId;
    private boolean isCompleted;
    private Timestamp done;
    private Timestamp createDate;
}
