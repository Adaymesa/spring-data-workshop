package com.thoughtworks.jpa.repository;

import com.thoughtworks.jpa.domain.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long>{
    List<Professor> findByLastNameIgnoreCaseOrderById(String lastName);
}
