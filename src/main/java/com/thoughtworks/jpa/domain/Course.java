package com.thoughtworks.jpa.domain;

import com.google.common.collect.Sets;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "course",
        uniqueConstraints = {
                @UniqueConstraint(name = "ux_course", columnNames = "code")
        })
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 100, nullable = false)
    private String name;
    @Column(name = "code", length = 8, nullable = false, updatable = false)
    private String code;
    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinTable(name = "teaches",
            joinColumns = {@JoinColumn(name = "course_id", nullable = false, referencedColumnName = "id",
                    foreignKey = @ForeignKey(ConstraintMode.PROVIDER_DEFAULT))},
            inverseJoinColumns = {@JoinColumn(name = "professor_id", nullable = false, referencedColumnName = "id",
                    foreignKey = @ForeignKey(ConstraintMode.PROVIDER_DEFAULT))})
    private Professor professor;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "enrolled",
            uniqueConstraints = {@UniqueConstraint(name = "ux_course_student", columnNames = {"course_id", "student_id"})},
            joinColumns = {@JoinColumn(name = "course_id", nullable = false, referencedColumnName = "id",
                    foreignKey = @ForeignKey(ConstraintMode.PROVIDER_DEFAULT))},
            inverseJoinColumns = {@JoinColumn(name = "student_id", nullable = false, referencedColumnName = "id",
                    foreignKey = @ForeignKey(ConstraintMode.PROVIDER_DEFAULT))})
    private Set<Student> students;

    Course() {
    }

    public Course(String name, String code, String description,
                  Professor professor, List<Student> students) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.professor = professor;
        this.students = Sets.newHashSet(students);
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

    public Set<Student> getStudents() {
        return students;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Course)) {
            return false;
        }

        final Course other = (Course) obj;
        return Objects.equals(code, other.code);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.code != null ? this.code.hashCode() : 0);
        return hash;
    }
}
