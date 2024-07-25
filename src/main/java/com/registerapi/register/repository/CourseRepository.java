package com.registerapi.register.repository;

import com.registerapi.register.model.Course;
import com.registerapi.register.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseRepository extends MongoRepository<Course, String> {
}
