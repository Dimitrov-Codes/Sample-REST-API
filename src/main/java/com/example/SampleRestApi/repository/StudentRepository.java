package com.example.SampleRestApi.repository;

import com.example.SampleRestApi.models.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StudentRepository extends MongoRepository<Student, String> {
    List<Student> findAllByGrade(Integer grade); //Custom find Method

}
