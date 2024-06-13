package com.example.CRUDJavaFX.Repo;

import com.example.CRUDJavaFX.models.CheckingAccount;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CheckingAccountRepo extends MongoRepository<CheckingAccount, String> {
    Optional<CheckingAccount> findCheckingAccountByIdOwner(String idOwner);
}
