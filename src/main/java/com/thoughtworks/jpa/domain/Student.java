package com.thoughtworks.jpa.domain;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "student")
@PrimaryKeyJoinColumn(name = "id",
        foreignKey = @ForeignKey(name = "student_person_fk", value = ConstraintMode.CONSTRAINT))
public class Student extends Person {
    @Column(name = "enrollment_date", nullable = false)
    private LocalDate enrollment;

    Student() {
        super();
    }

    public Student(String identification, String firstName, String lastName, LocalDate birth, LocalDate enrollment) {
        super(identification, firstName, lastName, birth);
        this.enrollment = enrollment;
    }

    public LocalDate getEnrollment() {
        return enrollment;
    }
}
