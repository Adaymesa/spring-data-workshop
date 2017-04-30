package com.thoughtworks.jpa.domain;


import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.time.LocalDate;

import static javax.persistence.EnumType.STRING;

@Entity
@Table(name = "professor")
@PrimaryKeyJoinColumn(name = "id",
        foreignKey = @ForeignKey(name = "professor_person_fk", value = ConstraintMode.CONSTRAINT))
public class Professor extends Person {
    @Column(name = "type", length = 9, nullable = false)
    @Enumerated(STRING)
    private Type type;

    Professor() {
        super();
    }

    public Professor(String firstName, String lastName, LocalDate birth, Type type) {
        super(firstName, lastName, birth);
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        ADJUNCT,
        VISITOR,
        ASSOCIATE
    }
}
