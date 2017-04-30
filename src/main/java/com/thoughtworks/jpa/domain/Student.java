package com.thoughtworks.jpa.domain;

import java.time.LocalDate;

public class Student {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birth;
    private LocalDate enrollment;

    Student(){}

    public Student(Long id, String firstName, String lastName, LocalDate birth, LocalDate enrollment) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birth = birth;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public LocalDate getEnrollment() {
        return enrollment;
    }
}
