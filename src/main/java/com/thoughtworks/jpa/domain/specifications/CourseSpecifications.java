package com.thoughtworks.jpa.domain.specifications;

import com.thoughtworks.jpa.domain.Course;
import com.thoughtworks.jpa.domain.Course_;
import com.thoughtworks.jpa.domain.Professor_;
import org.springframework.data.jpa.domain.Specification;

public class CourseSpecifications {
    public static Specification<Course> withFirstName(String firstName) {
        return (root, query, cb) -> cb.equal(root.get(Course_.professor).get(Professor_.firstName), firstName);
    }

    public static Specification<Course> withLastName(String lastName) {
        return (root, query, cb) -> cb.equal(root.get(Course_.professor).get(Professor_.lastName), lastName);
    }
}
