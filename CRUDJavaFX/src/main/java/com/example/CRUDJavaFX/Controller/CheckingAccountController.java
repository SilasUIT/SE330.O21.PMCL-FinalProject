package com.example.CRUDJavaFX.Controller;

import com.example.CRUDJavaFX.Repo.CheckingAccountRepo;
import com.example.CRUDJavaFX.models.CheckingAccount;
import com.example.CRUDJavaFX.models.SavingAccount;
import com.example.CRUDJavaFX.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        }
        catch (Exception e){
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
}
