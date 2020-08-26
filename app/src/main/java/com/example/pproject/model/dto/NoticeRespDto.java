package com.example.pproject.model.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeRespDto {
    private int id;
    private String username;
    private String title;
    private String content;
    private int count;
    private Timestamp createDate;
}
