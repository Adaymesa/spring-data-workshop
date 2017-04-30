package com.thoughtworks.jpa.domain;


import com.google.common.collect.Lists;

import java.util.List;

public class Course {
    private Long id;
    private String name;
    private String code;
    private String description;
    private Professor professor;
    private List<Student> students;

    Course(){}

    public Course(Long id, String name, String code, String description,
                  Professor professor, List<Student> students) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.description = description;
        this.professor = professor;
        this.students = Lists.newArrayList(students);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public Professor getProfessor() {
        return professor;
    }

    public List<Student> getStudents() {
        return students;
    }
}
