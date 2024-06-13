package com.example.CRUDJavaFX.Repo;

import com.example.CRUDJavaFX.models.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepo extends MongoRepository<Transaction, String> {
    List<Transaction> findAllByAddressSender(String id);
    List<Transaction> findAllByAddressReceiver(String id);

}
