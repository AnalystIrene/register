package com.registerapi.register.repository;

import com.registerapi.register.model.RegistrationInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RegistrationInfoRepository extends MongoRepository<RegistrationInfo, String> {
}
