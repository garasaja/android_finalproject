package com.example.pproject.model.dto;

import com.example.pproject.model.Store;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreDetailRespDto {
    private int id;
    private Store store;
    private List<ThemeRespDto> themes;

    private List<ReviewRespDto> reviews;
}
