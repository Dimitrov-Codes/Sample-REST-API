package com.example.SampleRestApi.dto;

import lombok.Data;

import java.util.UUID;
@Data
public class StudentDTO {
    private String id = UUID.randomUUID().toString();
    private String name;
    private Integer grade;
}
