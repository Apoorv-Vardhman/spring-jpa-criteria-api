package com.apoorv.criteria.repository;

import com.apoorv.criteria.entities.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Apoorv Vardhman
 * @Github Apoorv-Vardhman
 * @LinkedIN apoorv-vardhman
 */
@Repository
public interface StudentRepository extends CrudRepository<Student,Long> {

}
