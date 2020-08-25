package com.example.pproject.model.dto;

public class CommonRespDto<T> {
    private int statusCode; //1정상,-1실패,0변경안됨
    private T data;
}
