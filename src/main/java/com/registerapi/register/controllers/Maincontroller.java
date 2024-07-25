package com.registerapi.register.controllers;

import com.registerapi.register.model.Student;
import com.registerapi.register.model.Course;
import com.registerapi.register.model.RegistrationInfo;
import com.registerapi.register.repository.CourseRepository;
import com.registerapi.register.repository.Studentsrepo;
import com.registerapi.register.repository.RegistrationInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(path = "api/v1/admin")

public class Maincontroller {
    private static final Logger logger = LoggerFactory.getLogger(Maincontroller.class);

    @Autowired
    private Studentsrepo studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private RegistrationInfoRepository registrationInfoRepository;

    // Student Management Endpoints
    @PostMapping("/addstudents")
    public ResponseEntity<String> addStudent(@RequestBody Student student) {
        logger.info("Received request to add student: {}", student);
        try {
            studentRepository.save(student);
            logger.info("Student saved successfully.");
            return ResponseEntity.status(HttpStatus.CREATED).body("Student added successfully.");
        } catch (Exception e) {
            logger.error("Error saving student: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding student.");
        }
    }

    @PutMapping("/students")
    public ResponseEntity<String> updateStudent(@RequestBody Student student) {
        logger.info("Received request to update student: {}", student);
        Student existingStudent = studentRepository.findById(student.getRegno()).orElse(null);
        if (existingStudent != null) {
            existingStudent.setName(student.getName());
            existingStudent.setAddress(student.getAddress());
            studentRepository.save(existingStudent);
            logger.info("Student updated successfully.");
            return ResponseEntity.ok("Student updated successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/students/{id}")

    public ResponseEntity<String> deleteStudent(@PathVariable Integer id) {
        logger.info("Received request to delete student with ID: {}", id);
        try {
            studentRepository.deleteById(id);
            logger.info("Student deleted successfully.");
            return ResponseEntity.ok("Student deleted successfully.");
        } catch (Exception e) {
            logger.error("Error deleting student: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting student.");
        }
    }


    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Integer id) {
        logger.info("Received request to fetch student with ID: {}", id);
        Student student = studentRepository.findById(id).orElse(null);
        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> fetchStudents() {
        logger.info("Received request to fetch all students.");
        List<Student> students = studentRepository.findAll();
        return ResponseEntity.ok(students);
    }

    // Course Management Endpoints
    @PostMapping("/courses")
    public ResponseEntity<String> addCourse(@RequestBody Course course) {
        logger.info("Received request to add course: {}", course);
        try {
            courseRepository.save(course);
            logger.info("Course saved successfully.");
            return ResponseEntity.status(HttpStatus.CREATED).body("Course added successfully.");
        } catch (Exception e) {
            logger.error("Error saving course: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding course.");
        }
    }

    @PutMapping("/courses")
    public ResponseEntity<String> updateCourse(@RequestBody Course course) {
        logger.info("Received request to update course: {}", course);
        Course existingCourse = courseRepository.findById(course.getId()).orElse(null);
        if (existingCourse != null) {
            existingCourse.setName(course.getName());
            existingCourse.setDescription(course.getDescription());
            courseRepository.save(existingCourse);
            logger.info("Course updated successfully.");
            return ResponseEntity.ok("Course updated successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable String id) {
        logger.info("Received request to delete course with ID: {}", id);
        try {
            courseRepository.deleteById(id);
            logger.info("Course deleted successfully.");
            return ResponseEntity.ok("Course deleted successfully.");
        } catch (Exception e) {
            logger.error("Error deleting course: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting course.");
        }
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable String id) {
        logger.info("Received request to fetch course with ID: {}", id);
        Course course = courseRepository.findById(id).orElse(null);
        if (course != null) {
            return ResponseEntity.ok(course);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> fetchCourses() {
        logger.info("Received request to fetch all courses.");
        List<Course> courses = courseRepository.findAll();
        return ResponseEntity.ok(courses);
    }

    // Registration Management Endpoints
    @GetMapping("/registrations")
    public ResponseEntity<List<RegistrationInfo>> fetchRegistrations() {
        logger.info("Received request to fetch all registrations.");
        List<RegistrationInfo> registrations = registrationInfoRepository.findAll();
        return ResponseEntity.ok(registrations);
    }

    @GetMapping("/registrations/{id}")
    public ResponseEntity<RegistrationInfo> getRegistration(@PathVariable String id) {
        logger.info("Received request to fetch registration with ID: {}", id);
        RegistrationInfo registrationInfo = registrationInfoRepository.findById(id).orElse(null);
        if (registrationInfo != null) {
            return ResponseEntity.ok(registrationInfo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}



/*@RestController//for declaring our API methods
@RequestMapping(path = "api/v1/students")
public class Maincontroller {
    private static final Logger logger = LoggerFactory.getLogger(Maincontroller.class);

    @Autowired
    private Studentsrepo studentrepo;//saving the variable student in the object of the student repo

    //model name students 4 type we are declaring variable object student,
    //coz we are mapping the jeson data to the data in the model student that also has 3
    //function that doesnot return anything,

    @PostMapping("/addstudents")
    public void addstudents(@RequestBody Student student) {

        logger.info("Received request to add student: {}", student);
        studentrepo.save(student);
        logger.info("Student saved successfully.");
    }
    //Get function,it is not void since it returns a list of students
    @GetMapping("/getstudents/{id}")
    public Student getstudents(@PathVariable Integer id) {
        logger.info("Received request to fetch student with ID: {}", id);
        return studentrepo.findById(id).orElse(null);
    }

    @GetMapping("/fetchstudents")
    public List<Student> fetchstudents() {
        logger.info("Received request to fetch all students.");
        return studentrepo.findAll();
    }

    @PutMapping("/updatestudents")//posting data in our mongo db campass
    public void upadtestudents(@RequestBody Student student) {
        logger.info("Received request to update student: {}", student);
        Student data = studentrepo.findById(student.getRegno()).orElse(null);
        if (data != null) {
            data.setName(student.getName());
            data.setAddress(student.getAddress());
            studentrepo.save(student);
            logger.info("Student updated successfully.");
        }
    }

    @DeleteMapping("/deletestudents")//deleting data from our mongo db campass
    public void deletestudents(@PathVariable Integer id) {
        //saving the variable student in the object of the student repo
        studentrepo.deleteById(id);
}*/











