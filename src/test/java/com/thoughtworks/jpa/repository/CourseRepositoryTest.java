package com.thoughtworks.jpa.repository;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.google.common.collect.Sets;
import com.thoughtworks.jpa.domain.Course;
import com.thoughtworks.jpa.domain.Professor;
import com.thoughtworks.jpa.domain.Student;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import java.time.LocalDate;
import java.util.List;

import static com.github.springtestdbunit.assertion.DatabaseAssertionMode.NON_STRICT;
import static com.github.springtestdbunit.assertion.DatabaseAssertionMode.NON_STRICT_UNORDERED;
import static com.google.common.collect.Lists.newArrayList;
import static com.thoughtworks.jpa.domain.Professor.Type.ADJUNCT;
import static java.time.LocalDate.of;
import static org.assertj.core.api.Assertions.assertThat;

public class CourseRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private CourseRepository repository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Test
    @DatabaseSetup(value = "classpath:datasets/course/shouldReturnAllCourses.xml", type = DatabaseOperation.CLEAN_INSERT)
    public void shouldReturnAllCourses() {
        assertThat(repository.findAll()).isNotEmpty().hasSize(12);
    }

    @Test
    @ExpectedDatabase(value = "classpath:datasets/course/shouldPersist.xml", assertionMode = NON_STRICT_UNORDERED)
    public void shouldPersist() {
        List<Student> students = newArrayList(
                new Student("student_id1", "First", "Last", of(1983, 1, 3),
                        of(2000, 8, 1)),
                new Student("student_id2", "First", "Last", of(1983, 1, 3),
                        of(2000, 8, 1)));
        Professor professor = new Professor("professor_id", "First", "Last",
                of(1950, 2, 3), ADJUNCT);
        Course darkArts = new Course("Dark Arts", "MAGIC102", "Join the dark side",
                professor, students);
        repository.saveAndFlush(darkArts);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    @DatabaseSetup(value = "classpath:datasets/course/shouldThrowExceptionWhenCascadingWithDetachedEntity.xml",
            type = DatabaseOperation.CLEAN_INSERT)
    public void shouldThrowExceptionWhenCascadingWithDetachedEntity() {
        Professor detachedProfessor = this.professorRepository.findOne(1L);
        detachedEntity(detachedProfessor);
        Course course = new Course("Dark Arts", "MAG501", "Come to the Dark Side, we have cookies",
                detachedProfessor, Lists.emptyList());
        repository.saveAndFlush(course);
    }

    @Test
    @ExpectedDatabase(value = "classpath:datasets/course/shouldCascadeMergeAfterDetach.xml",
            assertionMode = NON_STRICT)
    public void shouldCascadeMergeAfterDetach() {
        Professor newProfessor = new Professor("prof_id","Tom", "Marvolo",
                of(1950, 1, 1), ADJUNCT);
        Student harry = new Student("student1", "Harry", "Potter", of(1983, 1, 1),
                of(2000, 8, 1));
        Student ron = new Student("student2", "Ron", "Weasly", of(1983, 1, 1),
                of(2000, 8, 1));
        Course course = new Course("Dark Arts", "MAG501", "Come to the Dark Side, we have cookies",
                newProfessor, Lists.newArrayList(harry, ron));
        repository.saveAndFlush(course);
        detachedEntity(course);
        course.setName("Dark Arts II");
        newProfessor.setFirstName("Severus");
        newProfessor.setLastName("Snape");
        repository.saveAndFlush(course);
    }
}