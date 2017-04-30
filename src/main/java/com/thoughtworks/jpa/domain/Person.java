package com.thoughtworks.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "person",
        uniqueConstraints = {
                @UniqueConstraint(name = "ux_identification", columnNames = "identification")
        })
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "identification", length = 30, nullable = false, updatable = false)
    private String identification;
    @Column(name = "first_name", length = 100, nullable = false)
    private String firstName;
    @Column(name = "last_name", length = 100, nullable = false)
    private String lastName;
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate birth;

    Person() {
    }

    Person(String identification, String firstName, String lastName, LocalDate birth) {
        this.identification = identification;
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

    public String getIdentification() {
        return identification;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!obj.getClass().isAssignableFrom(this.getClass())) {
            return false;
        }

        final Person other = (Person) obj;
        return Objects.equals(identification, other.identification);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (identification != null ? identification.hashCode() : 0);
        return hash;
    }
}
