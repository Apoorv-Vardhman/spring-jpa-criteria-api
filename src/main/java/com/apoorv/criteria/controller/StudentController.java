package com.apoorv.criteria.controller;

import com.apoorv.criteria.dto.StudentPageDTO;
import com.apoorv.criteria.entities.Student;
import com.apoorv.criteria.services.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Apoorv Vardhman
 * @Github Apoorv-Vardhman
 * @LinkedIN apoorv-vardhman
 */
@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public @ResponseBody ResponseEntity<Page<Student>> index(StudentPageDTO studentPageDTO)
    {
        return new ResponseEntity<>(this.studentService.getStudents(studentPageDTO), HttpStatus.OK);
    }

    @PostMapping
    public @ResponseBody ResponseEntity<Student> createStudent(@RequestBody Student student)
    {
        return new ResponseEntity<>(this.studentService.addStudent(student),HttpStatus.CREATED);
    }
}
