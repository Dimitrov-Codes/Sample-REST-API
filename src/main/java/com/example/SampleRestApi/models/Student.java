package com.example.SampleRestApi.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;
@Data
@Document(collection = "Students")
public class Student {
    @Id
    private String id = UUID.randomUUID().toString(); //Universal Unique ID -> Automatic
    private String name;
    private Integer grade;

}
