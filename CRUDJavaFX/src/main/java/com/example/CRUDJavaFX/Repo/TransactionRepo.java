package com.example.CRUDJavaFX.Repo;

import com.example.CRUDJavaFX.models.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransactionRepo extends MongoRepository<Transaction, String> {
    List<Transaction> findAllByAddressSenderOrderByDateDesc(String id);
    List<Transaction> findAllByAddressReceiverOrderByDateDesc(String id);
    List<Transaction> findTransactionsByAddressSenderRegex(String id);
    List<Transaction> findTransactionsByAddressReceiverRegex(String id);
}
