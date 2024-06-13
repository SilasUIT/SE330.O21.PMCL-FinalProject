package com.example.CRUDJavaFX.Repo;

import com.example.CRUDJavaFX.models.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AdminRepo extends MongoRepository<Admin, String> {
    Optional<Admin> findByUserNameAndPassWord(String userName,String passWord);
}
