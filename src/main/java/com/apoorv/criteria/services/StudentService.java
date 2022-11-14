package com.apoorv.criteria.services;

import com.apoorv.criteria.dto.StudentPageDTO;
import com.apoorv.criteria.entities.Student;
import com.apoorv.criteria.repository.StudentCriteriaRepository;
import com.apoorv.criteria.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * @author Apoorv Vardhman
 * @Github Apoorv-Vardhman
 * @LinkedIN apoorv-vardhman
 */
@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentCriteriaRepository studentCriteriaRepository;

    public StudentService(StudentRepository studentRepository, StudentCriteriaRepository studentCriteriaRepository) {
        this.studentRepository = studentRepository;
        this.studentCriteriaRepository = studentCriteriaRepository;
    }

    public Page<Student> getStudents(StudentPageDTO studentPageDTO)
    {
        return studentCriteriaRepository.findAllWithFilter(studentPageDTO);
    }

    public Student addStudent(Student student){
        return studentRepository.save(student);
    }

}
