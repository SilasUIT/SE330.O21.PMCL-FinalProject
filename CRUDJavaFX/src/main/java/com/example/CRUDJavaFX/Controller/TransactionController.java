package com.example.CRUDJavaFX.Controller;

import com.example.CRUDJavaFX.Repo.TransactionRepo;
import com.example.CRUDJavaFX.Repo.UserRepo;
import com.example.CRUDJavaFX.models.Transaction;
import com.example.CRUDJavaFX.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    final TransactionRepo transactionRepo;
    final UserController userController;
    private final UserRepo userRepo;

    public TransactionController(TransactionRepo transactionRepo, UserController userController, UserRepo userRepo) {
        this.transactionRepo = transactionRepo;
        this.userController = userController;
        this.userRepo = userRepo;
    }

    @PostMapping("/")
    public ResponseEntity<String> addTransaction(@RequestBody Transaction transaction) {
        if(Objects.equals(transaction.getAddressSender(), transaction.getAddressReceiver())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("không thể chuyển cho chính bản thân");
        }
        Optional<User> userRecei=userRepo.findUserByPayeeAddress(transaction.getAddressReceiver());
        Optional<User> userSender=userRepo.findUserByPayeeAddress(transaction.getAddressSender());
        if(userRecei.isEmpty() || userSender.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("tài khoản không tồn tại");
        }
        String response=userController.Transaction(transaction.getAddressSender(),transaction.getAddressReceiver(), transaction.getAmount());
        if(Objects.equals(response, "Chuyển tiền thành công")) {
            LocalDate today = LocalDate.now();

            // Định dạng ngày theo định dạng "dd/MM/yyyy"
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formattedDate = today.format(formatter);
            transaction.setDate(formattedDate);
            transactionRepo.save(transaction);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Transaction>> getTransaction(@PathVariable String id) {
        List<Transaction> transactionRecei = transactionRepo.findAllByAddressSenderOrderByDateDesc(id);
        List<Transaction> transactionSen = transactionRepo.findAllByAddressReceiverOrderByDateDesc(id);
        List<Transaction> transactionObj = new ArrayList<>(transactionRecei);
        transactionObj.addAll(transactionSen);
        return ResponseEntity.ok(transactionObj);
    }

    @GetMapping("/latest_income")
    public ResponseEntity<?> getLastIncome(@RequestParam String id) {
        List<Transaction> transactionRecei = transactionRepo.findAllByAddressReceiverOrderByDateDesc(id);
        if(transactionRecei.isEmpty()){
            return ResponseEntity.ok("0");
        }
        return ResponseEntity.ok(transactionRecei.get(0).getAmount());
    }


    @GetMapping("/latest_expense")
    public ResponseEntity<?> getLastExpense(@RequestParam String id) {
        List<Transaction> transactionRecei = transactionRepo.findAllByAddressSenderOrderByDateDesc(id);
        if(transactionRecei.isEmpty()){
            return ResponseEntity.ok("0");
        }
        return ResponseEntity.ok(transactionRecei.get(0).getAmount());
    }
}
