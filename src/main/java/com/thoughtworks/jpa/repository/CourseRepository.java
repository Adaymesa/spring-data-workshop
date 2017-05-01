package com.thoughtworks.jpa.repository;

import com.thoughtworks.jpa.domain.Course;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.jpa.repository.EntityGraph.EntityGraphType.LOAD;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Override
    @EntityGraph(value = "Course.all", type = LOAD)
    List<Course> findAll();

    List<Course> findByProfessorLastName(String lastName);
}
