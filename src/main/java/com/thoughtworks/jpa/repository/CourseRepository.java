package com.thoughtworks.jpa.repository;

import com.thoughtworks.jpa.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Override
    @Query("select distinct c from Course c left join fetch c.professor left join fetch c.students")
    List<Course> findAll();

    List<Course> findByProfessorLastName(String lastName);
}
