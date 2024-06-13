package com.example.CRUDJavaFX.Controller;


import com.example.CRUDJavaFX.Repo.SavingAccountRepo;
import com.example.CRUDJavaFX.models.SavingAccount;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/saving_account")
public class SavingAccountController {
    private final SavingAccountRepo savingAccountRepo;

    public SavingAccountController(SavingAccountRepo savingAccountRepo) {
        this.savingAccountRepo = savingAccountRepo;
    }

    @GetMapping("/")
    public ResponseEntity<List<SavingAccount>> getAllUser() {
        try {

            List<SavingAccount> savingAccounts = new ArrayList<>(savingAccountRepo.findAll());
            if (!savingAccounts.isEmpty()) {
                return new ResponseEntity<>(savingAccounts, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SavingAccount> getCheckingAccount(@PathVariable String id) {
        Optional<SavingAccount> savingAccount = savingAccountRepo.findSavingAccountByIdOwner(id);
        if (savingAccount.isPresent()) {
            return new ResponseEntity<>(savingAccount.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public boolean ChangeBalance(String id, float amount) {
        try {
            Optional<SavingAccount> savingAccount = savingAccountRepo.findSavingAccountByIdOwner(id);
            float newAmount = savingAccount.get().getBalance() + amount;
            SavingAccount newSavingAccount = savingAccount.get();
            newSavingAccount.setBalance(newAmount);
            savingAccountRepo.save(newSavingAccount);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public int CheckBalance(String id, float amount) {
        Optional<SavingAccount> savingAccount = savingAccountRepo.findSavingAccountByIdOwner(id);
        if (savingAccount.isPresent()) {
            float newAmount = savingAccount.get().getBalance() + amount;
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


    public void addSavingAccount(String Id,float money){
        SavingAccount savingAccount=new SavingAccount(Id,generateRandomString(),200000,money);
        savingAccountRepo.save(savingAccount);
    }

}



