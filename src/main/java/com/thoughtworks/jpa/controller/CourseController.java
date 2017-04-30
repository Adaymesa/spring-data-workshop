package com.thoughtworks.jpa.controller;

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
    public List<com.thoughtworks.jpa.domain.Course> getCourses() {
        return this.repository.findAll();
    }

    @RequestMapping("/{id}")
    @ResponseBody
    public com.thoughtworks.jpa.domain.Course getCourse(@PathVariable("id") Long id) {
        return this.repository.findOne(id);
    }
}
