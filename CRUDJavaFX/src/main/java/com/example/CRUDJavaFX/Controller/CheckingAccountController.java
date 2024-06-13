package com.example.CRUDJavaFX.Controller;

import com.example.CRUDJavaFX.Repo.CheckingAccountRepo;
import com.example.CRUDJavaFX.models.CheckingAccount;
import com.example.CRUDJavaFX.models.SavingAccount;
import com.example.CRUDJavaFX.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/checking_account")
public class CheckingAccountController {
    public final CheckingAccountRepo checkingAccountRepo;

    public CheckingAccountController(CheckingAccountRepo checkingAccountRepo) {
        this.checkingAccountRepo = checkingAccountRepo;
    }

    @GetMapping("/")
    public ResponseEntity<List<CheckingAccount>> getAllUser() {
        try {

            List<CheckingAccount> checkingAccounts = new ArrayList<>(checkingAccountRepo.findAll());
            if (!checkingAccounts.isEmpty()) {
                return new ResponseEntity<>(checkingAccounts, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CheckingAccount> getCheckingAccount(@PathVariable String id) {
        Optional<CheckingAccount> checkingAccount = checkingAccountRepo.findCheckingAccountByIdOwner(id);
        if (checkingAccount.isPresent()) {
            return new ResponseEntity<>(checkingAccount.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public boolean ChangeBalance(String id, float amount) {
        try {
            Optional<CheckingAccount> checkingAccount = checkingAccountRepo.findCheckingAccountByIdOwner(id);
            float newAmount = checkingAccount.get().getBalance() + amount;
            CheckingAccount newCheckingAccount = checkingAccount.get();
            newCheckingAccount.setBalance(newAmount);
            checkingAccountRepo.save(newCheckingAccount);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public int CheckBalance(String id, float amount) {
        Optional<CheckingAccount> checkingAccount = checkingAccountRepo.findCheckingAccountByIdOwner(id);
        if (checkingAccount.isPresent()) {
            float newAmount = checkingAccount.get().getBalance() - amount;
            if (newAmount < 0)
                return -1;
            return 1;
        }
        return 0;
    }

    private static final String DIGITS = "0123456789";
    private static final SecureRandom random = new SecureRandom();

    public String generateRandomString() {
        StringBuilder sb = new StringBuilder(12);
        for (int i = 0; i < 12; i++) {
            int index = random.nextInt(DIGITS.length());
            sb.append(DIGITS.charAt(index));
        }
        return sb.toString();
    }


    public void addCheckingAccount(String Id, float money) {
        CheckingAccount checkingAccount = new CheckingAccount(Id, generateRandomString(), 200000, money);
        checkingAccountRepo.save(checkingAccount);
    }

    @GetMapping("addBalance")
    public ResponseEntity<HttpStatus> addBalance(
            @RequestParam String IdOwner,
            @RequestParam float amount) {
        Optional<CheckingAccount> checkingAccount = checkingAccountRepo.findCheckingAccountByIdOwner(IdOwner);
        if (checkingAccount.isPresent()) {
            CheckingAccount newCheckingAcount = checkingAccount.get();
            newCheckingAcount.setBalance(newCheckingAcount.getBalance() + amount);
            checkingAccountRepo.save(newCheckingAcount);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
