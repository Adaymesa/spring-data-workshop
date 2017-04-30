package com.thoughtworks.jpa.repository;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class CourseRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private CourseRepository repository;

    @Test
    @DatabaseSetup(value = "classpath:datasets/course/shouldReturnAllCourses.xml", type = DatabaseOperation.CLEAN_INSERT)
    public void shouldReturnAllCourses() {
        assertThat(repository.findAll()).isNotEmpty().hasSize(12);
    }
}