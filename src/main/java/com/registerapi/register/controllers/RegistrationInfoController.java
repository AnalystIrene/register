package com.registerapi.register.controllers;
import com.registerapi.register.model.RegistrationInfo;
import com.registerapi.register.repository.RegistrationInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(path = "api/v1/registrations")


public class RegistrationInfoController {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationInfoController.class);

    @Autowired
    private RegistrationInfoRepository registrationInfoRepository;

    @PostMapping("/addregistration")
    public ResponseEntity<String> addRegistration(@RequestBody RegistrationInfo registrationInfo) {
        logger.info("Received request to add registration: {}", registrationInfo);
        try {
            registrationInfoRepository.save(registrationInfo);
            logger.info("Registration saved successfully.");
            return ResponseEntity.status(HttpStatus.CREATED).body("Registration added successfully.");
        } catch (Exception e) {
            logger.error("Error saving registration: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding registration.");
        }
    }

    @GetMapping("/getregistration/{id}")
    public ResponseEntity<RegistrationInfo> getRegistration(@PathVariable String id) {
        logger.info("Received request to fetch registration with ID: {}", id);
        RegistrationInfo registrationInfo = registrationInfoRepository.findById(id).orElse(null);
        if (registrationInfo != null) {
            return ResponseEntity.ok(registrationInfo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/fetchregistrations")
    public ResponseEntity<List<RegistrationInfo>> fetchRegistrations() {
        logger.info("Received request to fetch all registrations.");
        List<RegistrationInfo> registrations = registrationInfoRepository.findAll();
        return ResponseEntity.ok(registrations);
    }

    @PutMapping("/updateregistration")
    public ResponseEntity<String> updateRegistration(@RequestBody RegistrationInfo registrationInfo) {
        logger.info("Received request to update registration: {}", registrationInfo);
        RegistrationInfo existingRegistration = registrationInfoRepository.findById(registrationInfo.getId()).orElse(null);
        if (existingRegistration != null) {
            existingRegistration.setStudentId(registrationInfo.getStudentId());
            existingRegistration.setCourseId(registrationInfo.getCourseId());
            existingRegistration.setRegistrationDate(registrationInfo.getRegistrationDate());
            registrationInfoRepository.save(existingRegistration);
            logger.info("Registration updated successfully.");
            return ResponseEntity.ok("Registration updated successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteregistration/{id}")
    public ResponseEntity<String> deleteRegistration(@PathVariable String id) {
        logger.info("Received request to delete registration with ID: {}", id);
        try {
            registrationInfoRepository.deleteById(id);
            logger.info("Registration deleted successfully.");
            return ResponseEntity.ok("Registration deleted successfully.");
        } catch (Exception e) {
            logger.error("Error deleting registration: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting registration.");
        }
    }
}
