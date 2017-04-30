package com.thoughtworks.jpa.controller;

import com.thoughtworks.jpa.domain.Course;
import com.thoughtworks.jpa.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/courses", produces = {"application/json"})
public class CourseController {
    @Autowired
    private CourseRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Course> getCourses() {
        return this.repository.findAll();
    }

    @RequestMapping("/{id}")
    @ResponseBody
    public Course getCourse(@PathVariable("id") Long id) {
        return this.repository.findOne(id);
    }
}
