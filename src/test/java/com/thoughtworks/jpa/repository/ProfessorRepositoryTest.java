package com.thoughtworks.jpa.repository;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.thoughtworks.jpa.domain.Professor;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.thoughtworks.jpa.domain.Professor.Type.ADJUNCT;
import static java.time.LocalDate.of;
import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    @DatabaseSetup(value = "classpath:datasets/professor/shouldFindProfessorByLastNameIgnoreCase.xml")
    public void shouldFindProfessorByLastNameIgnoreCase() {
        List<Professor> professors = repository.findByLastNameIgnoreCaseOrderById("snape");
        assertThat(professors).isNotEmpty().hasSize(2);
        assertThat(professors.get(0).getId()).isEqualTo(2);
        assertThat(professors.get(1).getId()).isEqualTo(3);
    }

}