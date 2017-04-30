package com.thoughtworks.jpa.repository;

import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.thoughtworks.jpa.domain.Professor;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.thoughtworks.jpa.domain.Professor.Type.ADJUNCT;
import static java.time.LocalDate.of;

public class ProfessorRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private ProfessorRepository repository;

    @Test
    @ExpectedDatabase(value = "classpath:datasets/professor/shouldPersist.xml")
    public void shouldPersist() {
        Professor professor = new Professor("identification", "First Name",
                "Last Name", of(1983, 01, 03), ADJUNCT);
        repository.save(professor);
    }

}