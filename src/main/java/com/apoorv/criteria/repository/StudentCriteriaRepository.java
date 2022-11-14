package com.apoorv.criteria.repository;

import com.apoorv.criteria.dto.StudentPageDTO;
import com.apoorv.criteria.entities.Student;
import com.apoorv.criteria.utils.AppConstant;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Apoorv Vardhman
 * @Github Apoorv-Vardhman
 * @LinkedIN apoorv-vardhman
 */
@Repository
public class StudentCriteriaRepository {
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public StudentCriteriaRepository(EntityManager entityManager)
    {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Student> findAllWithFilter(StudentPageDTO studentPageDTO)
    {
        CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
        /*specify return type*/
        Root<Student> studentRoot = criteriaQuery.from(Student.class);
        /*search*/
        Predicate predicate = getSearchPredicate(studentRoot, studentPageDTO.getSearch());
        criteriaQuery.where(predicate);
        /*sort*/
        if(AppConstant.sortDirection.equals(Sort.Direction.ASC))
        {
            criteriaQuery.orderBy(criteriaBuilder.asc(studentRoot.get(AppConstant.sortBy)));
        }
        else
        {
            criteriaQuery.orderBy(criteriaBuilder.desc(studentRoot.get(AppConstant.sortBy)));
        }
        TypedQuery<Student> typedQuery= entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(studentPageDTO.getPageNumber()* studentPageDTO.getPageSize());
        typedQuery.setMaxResults(studentPageDTO.getPageSize());
        Pageable pageable = getPageable(studentPageDTO);
        long employeesCount = getStudentCount(predicate);
        return new PageImpl<>(typedQuery.getResultList(), pageable, employeesCount);
    }

    private long getStudentCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Student> countRoot = countQuery.from(Student.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }

    private Pageable getPageable(StudentPageDTO studentPageDTO) {
        Sort sort = Sort.by(AppConstant.sortDirection,AppConstant.sortBy);
        return PageRequest.of(studentPageDTO.getPageNumber(), studentPageDTO.getPageSize(),sort);
    }

    public Predicate getSearchPredicate(Root<Student> studentRoot,String search)
    {
        List<Predicate> predicates = new ArrayList<>();
        if(!search.equals(""))
        {
            predicates.add(
                    criteriaBuilder.like(studentRoot.get("firstName"),"%"+search+"%")
            );
            predicates.add(
                    criteriaBuilder.like(studentRoot.get("lastName"),"%"+search+"%")
            );
            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        }
        else
        {
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }
    }
}
