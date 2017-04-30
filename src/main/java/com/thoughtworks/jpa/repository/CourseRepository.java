package com.thoughtworks.jpa.repository;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thoughtworks.jpa.domain.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class CourseRepository {
    private Map<Long, Course> courses = Maps.newHashMap();

    @Autowired
    public CourseRepository(@Value("classpath:data.json") Resource dataSource,
                            ObjectMapper mapper) throws IOException {
        JsonFactory factory = new JsonFactory();
        List<Course> courses = mapper.readValue(factory.createParser(dataSource.getFile()),
                mapper.getTypeFactory().constructCollectionType(List.class, Course.class));
        for(Course course : courses) {
            this.courses.put(course.getId(), course);
        }
    }

    public Course findById(Long id) {
        return courses.get(id);
    }

    public List<Course> findAll() {
        return Lists.newArrayList(this.courses.values());
    }

}
