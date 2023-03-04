package com.example.SampleRestApi.service;

import com.example.SampleRestApi.dto.StudentDTO;
import com.example.SampleRestApi.models.Student;
import com.example.SampleRestApi.repository.StudentRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;


@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final MongoTemplate mongoOperation;
    //Dependency Injection
    StudentService(StudentRepository studentRepository, MongoTemplate mongoOperation){
        this.studentRepository = studentRepository;
        this.mongoOperation = mongoOperation;
    }
    public Student convertDTOToStudent(StudentDTO studentDTO){
        Student detached = new Student();
        detached.setId(studentDTO.getId());
        detached.setName(studentDTO.getName());
        detached.setGrade(studentDTO.getGrade());
        return detached;
    }
    public StudentDTO convertStudentToDTO(Student student){
        StudentDTO detached = new StudentDTO();
        detached.setId(student.getId());
        detached.setName(student.getName());
        detached.setGrade(student.getGrade());
        return detached;
    }
    public Student updateStudent(StudentDTO studentDTO){
        Query query = (new Query()).addCriteria(Criteria.where("_id").is(studentDTO.getId())); //db.find({$eq: {"_id": "<Example>"}})
        Student detachedStudent = convertDTOToStudent(studentDTO);
        Update update = new Update().set("$.", detachedStudent); //db.find(<Document>).updateFirst({"$eq": {"$": <Student>}})
        mongoOperation.updateFirst(query, update, Student.class);
        return detachedStudent;

    }

}
