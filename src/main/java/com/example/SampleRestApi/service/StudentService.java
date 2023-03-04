package com.example.SampleRestApi.service;

import com.example.SampleRestApi.dto.StudentDTO;
import com.example.SampleRestApi.models.Student;
import com.example.SampleRestApi.repository.StudentRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;


@Service
public class StudentService {
    private final StudentRepository studentRepository;

    //Dependency Injection
    StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student convertDTOToStudent(StudentDTO studentDTO) {
        Student detached = new Student();
        detached.setId(studentDTO.getId());
        detached.setName(studentDTO.getName());
        detached.setGrade(studentDTO.getGrade());
        return detached;
    }

    public StudentDTO convertStudentToDTO(Student student) {
        StudentDTO detached = new StudentDTO();
        detached.setId(student.getId());
        detached.setName(student.getName());
        detached.setGrade(student.getGrade());
        return detached;
    }

}
