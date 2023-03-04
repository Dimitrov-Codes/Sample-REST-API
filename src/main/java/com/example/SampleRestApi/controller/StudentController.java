package com.example.SampleRestApi.controller;

import com.example.SampleRestApi.dto.StudentDTO;
import com.example.SampleRestApi.models.Student;
import com.example.SampleRestApi.repository.StudentRepository;
import com.example.SampleRestApi.service.StudentService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class StudentController {
    private final StudentRepository studentRepository;
    private final StudentService studentService;

    public StudentController(StudentRepository studentRepository, StudentService studentService) {
        this.studentRepository = studentRepository;
        this.studentService = studentService;
    }
//    "Adnan" == "Mice" //Compares the address where these strings are stored NOT THE RIGHT WAY

    //Representation State Transfer
    //GET
    @GetMapping("/getStudents")
    public List<StudentDTO> getStudents() {
        //Equivalent of SELECT * FROM Students; db.Students.find()
        return studentRepository.findAll()
                .stream()
                .map(studentService::convertStudentToDTO)
                .collect(Collectors.toList()); //Convert to Data Transfer Objects
    }

    @GetMapping("/getStudentById/{studentId}")
    public StudentDTO getStudentById(@PathVariable String studentId) {
        //Optional<Student>
        //get() && orElse() --> will return a Student Object if it is present else null
        Optional<Student> result = studentRepository.findById(studentId);
        return studentService.convertStudentToDTO(result.get());//Convert to Data Transfer Objects
    }
    @GetMapping("/getStudentsByGrade/{grade}")
    public List<StudentDTO> getStudentsByGrade(@PathVariable Integer grade){
        //Size will be 0 if no students are present hence an optional is not returned
        return studentRepository.findAllByGrade(grade)
                .stream()
                .map(studentService::convertStudentToDTO)
                .collect(Collectors.toList());//Convert to Data Transfer Objects
    }
    /**
     * FIX UPDATE STUDENT
     **/
    //PUT
    @PutMapping("/updateStudent")
    public StudentDTO updateStudent(@RequestBody StudentDTO studentDTO){
       Student updatedStudent = studentService.updateStudent(studentDTO);
       return studentService.convertStudentToDTO(updatedStudent);
    }

    //POST
    @PostMapping("/createStudent")
    public StudentDTO createStudent(@RequestBody StudentDTO studentDTO){ // studentDTO will contain only NAME and GRADE alone ID WILL BE NULL
        Student detachedStudent = new Student();
        detachedStudent.setName(studentDTO.getName());
        detachedStudent.setGrade(studentDTO.getGrade());
        Student savedStudent = studentRepository.save(detachedStudent);
        return studentService.convertStudentToDTO(savedStudent);

    }
    //DELETE
    @DeleteMapping("/deleteStudent/{id}")
    public void deleteStudentById(@PathVariable String id){
        studentRepository.deleteById(id);
    }
}
