package com.example.CRUDJavaFX.Repo;

import com.example.CRUDJavaFX.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends MongoRepository<User,String> {
    Optional<User> findUserByPayeeAddress(String payeeAddress);
    List<User> findUserByPayeeAddressRegex(String payeeAddressRegex);
}
