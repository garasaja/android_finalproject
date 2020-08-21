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
public class NoticeBoard {
    private int id;
    private int userId;
    private String title;
    private String content;
    private int count;
    private Timestamp createDate;
}
