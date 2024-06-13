package com.example.CRUDJavaFX.Controller;

import com.example.CRUDJavaFX.Repo.TransactionRepo;
import com.example.CRUDJavaFX.models.Transaction;
import com.example.CRUDJavaFX.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    final TransactionRepo transactionRepo;
    final UserController userController;
    public TransactionController(TransactionRepo transactionRepo, UserController userController) {
        this.transactionRepo = transactionRepo;
        this.userController = userController;
    }

    @PostMapping("/")
    public ResponseEntity<String> addTransaction(@RequestBody Transaction transaction) {

        String response=userController.Transaction(transaction.getAddressSender(),transaction.getAddressReceiver(), transaction.getAmount());
        if(response=="{ \"Chuyển tiền thành công\"}") {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Transaction>> getTransaction(@PathVariable String id) {
        List<Transaction> transactionRecei = transactionRepo.findAllByAddressReceiver(id);
        List<Transaction> transactionSen = transactionRepo.findAllByAddressSender(id);

        List<Transaction> transactionObj = new ArrayList<>(transactionRecei);
        transactionObj.addAll(transactionSen);

        return ResponseEntity.ok(transactionObj);
    }

}
