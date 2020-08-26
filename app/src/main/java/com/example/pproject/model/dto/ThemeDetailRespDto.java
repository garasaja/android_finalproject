package com.example.pproject.model.dto;

import com.example.pproject.model.Theme;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ThemeDetailRespDto {
    private int id;
    private Theme theme;
    private String storeName;
    private List<ThemeRespDto> themes;

    private List<ReviewRespDto> reviews;
}
