package com.example.CRUDJavaFX.Repo;

import com.example.CRUDJavaFX.models.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdminRepo extends MongoRepository<Admin, String> {
}
