package com.example.pproject.model.dto;

import com.example.pproject.model.Store;
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
public class IndexRespDto {
    private int limit;
    private List<Store> stores;
    private List<Theme> themes;
}
