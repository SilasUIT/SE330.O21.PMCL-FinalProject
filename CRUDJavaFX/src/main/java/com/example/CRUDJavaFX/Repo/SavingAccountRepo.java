package com.example.CRUDJavaFX.Repo;

import com.example.CRUDJavaFX.models.SavingAccount;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SavingAccountRepo extends MongoRepository<SavingAccount, String> {
    Optional<SavingAccount> findSavingAccountByIdOwner(String idOwner);
}
