package com.example.CRUDJavaFX.Controller;


import com.example.CRUDJavaFX.Repo.CheckingAccountRepo;
import com.example.CRUDJavaFX.Repo.SavingAccountRepo;
import com.example.CRUDJavaFX.Repo.TransactionRepo;
import com.example.CRUDJavaFX.Repo.UserRepo;
import com.example.CRUDJavaFX.models.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.security.SecureRandom;
import java.util.Random;


@RestController
public class UserController {

    private final UserRepo userRepo;
    private final CheckingAccountRepo checkingAccountRepo;
    private final SavingAccountRepo savingAccountRepo;
    private final TransactionRepo transactionRepo;

    public UserController(UserRepo userRepo, CheckingAccountRepo checkingAccountRepo, SavingAccountController savingAccountController, SavingAccountRepo savingAccountRepo, TransactionRepo transactionRepo) {
        this.userRepo = userRepo;
        this.checkingAccountRepo = checkingAccountRepo;

        this.savingAccountRepo = savingAccountRepo;
        this.transactionRepo = transactionRepo;
    }


    @GetMapping("/getAllUser")
    public ResponseEntity<List<User>> getAllUser() {
        try {
            List<User> users = new ArrayList<>(userRepo.findAll());
            if (!users.isEmpty()) {
                return new ResponseEntity<>(users, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/getUserByID/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        Optional<User> user = userRepo.findById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User userObj = userRepo.save(user);
        SavingAccountController savingAccountController =new SavingAccountController(savingAccountRepo);
        CheckingAccountController checkingAccountController =new CheckingAccountController(checkingAccountRepo);
        savingAccountController.addSavingAccount(user.getId(),0);
        checkingAccountController.addCheckingAccount(user.getId(),0);
        return new ResponseEntity<>(userObj, HttpStatus.OK);
    }

    @PostMapping("/updateUserById/{id}")
    public ResponseEntity<User> updateUser(@RequestBody String id, @RequestBody User newUserData) {
        Optional<User> user = userRepo.findById(id);
        if (user.isPresent()) {
            User updatedUser = user.get();
            updatedUser.setFirstName(new String(newUserData.getFirstName()));
            updatedUser.setLastName(new String(newUserData.getLastName()));
            updatedUser.setPassWord(new String(newUserData.getPassWord()));
            updatedUser.setPayeeAddress(new String(newUserData.getPayeeAddress()));
            updatedUser.setDateOfBirth(new String(newUserData.getDateOfBirth()));

            User userBoj = userRepo.save(updatedUser);
            return new ResponseEntity<>(userBoj, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable String id) {
        userRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getUserWithPayeeAndPassword")
    public ResponseEntity<?> getUserWithPayeeAndPassword(
            @RequestParam("payeeaddress") String payeeAdress,
            @RequestParam("password") String passWord) {

        Optional<User> user = userRepo.findUserByPayeeAddress(payeeAdress);
        if (user.isPresent() && user.get().getPassWord().equals(passWord)) {

            return ResponseEntity.ok(user.get());
        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/saving")
    public ResponseEntity<String> SavingMoney(
            @RequestParam String id,
            @RequestParam float amount) {
        CheckingAccountController checkingAccountController = new CheckingAccountController(checkingAccountRepo);
        SavingAccountController savingAccountController = new SavingAccountController(savingAccountRepo);
        if (checkingAccountController.CheckBalance(id, amount) != 0 || savingAccountController.CheckBalance(id, amount) != 0) {
            if (checkingAccountController.CheckBalance(id, amount) == -1 || savingAccountController.CheckBalance(id, amount) == -1) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("số dư không đủ}");
            }
            if (checkingAccountController.ChangeBalance(id, -amount) && savingAccountController.ChangeBalance(id, amount))
                return ResponseEntity.status(HttpStatus.OK).body("Thành công");
            return ResponseEntity.status(HttpStatus.OK).body("Có lỗi gì đó");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("không tìm thấy tài khoản");
    }


    public String Transaction(String idSender, String idReceiver, float amount) {
        Optional<User> userSender = userRepo.findUserByPayeeAddress(idSender);
        Optional<User> userReceiver = userRepo.findUserByPayeeAddress(idReceiver);
        CheckingAccountController checkingAccountController = new CheckingAccountController(checkingAccountRepo);
        SavingAccountController savingAccountController = new SavingAccountController(savingAccountRepo);

            if (amount <= 0)
                return "Số tiền không hợp lệ";
            else {
                if (checkingAccountController.ChangeBalance(userSender.get().getId(), -amount) && checkingAccountController.ChangeBalance(userReceiver.get().getId(), amount))
                {
                    return "Chuyển tiền thành công";
                }
                return "Chuyển tiền thất bại";
            }

    }


    @PostMapping("/addUserwithMoney")
    public ResponseEntity<?> addUserWithMoney(@RequestBody User user,@RequestParam float moneyCh,@RequestParam float moneySv) {
        Optional<User> use1=userRepo.findUserByPayeeAddress(user.getPayeeAddress());
        if(use1.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Địa chỉ ví này đã tồn tại");
        }
        if(user.getPayeeAddress()==null || !isValidPayeeAddress(user.getPassWord()))
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Địa chỉ ví có ít nhất 6 kí tự, 1 số, 1 chữ hoa và 1 kí từ đặc bie");
        }
        if(user.getPassWord()==null || !isValidPassword(user.getPassWord()))
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mật khẩu có ít nhất 6 kí tự, 1 số và 1 chữ hoa");
        }
        if(moneyCh<0 || moneySv<0)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Số tiền phải lớn hơn =0");
        }
        LocalDate today = LocalDate.now();

        // Định dạng ngày theo định dạng "dd/MM/yyyy"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = today.format(formatter);
        user.setDateOfBirth(formattedDate);
        User userObj = userRepo.save(user);
        SavingAccountController savingAccountController =new SavingAccountController(savingAccountRepo);
        CheckingAccountController checkingAccountController =new CheckingAccountController(checkingAccountRepo);
        savingAccountController.addSavingAccount(user.getId(),moneySv);
        checkingAccountController.addCheckingAccount(user.getId(),moneyCh);
        return new ResponseEntity<>(userObj, HttpStatus.OK);
    }
    @GetMapping("/User")
    public ResponseEntity<List<User>> SearchUserWithPayee(@RequestParam String payeeAddress)
    {
        List<User> users=userRepo.findUserByPayeeAddressRegex(payeeAddress);
        return new  ResponseEntity<>(users,HttpStatus.OK);
    }


    public User getUserByPayeeAddress(String payeeAddress)
    {
        Optional<User> user=userRepo.findUserByPayeeAddress(payeeAddress);
        return user.orElse(null);
    }


    private static final String PASSWORD_PATTERN =
            "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{6,}$";

    public static boolean isValidPassword(String password) {
        if (password == null) {
            return false;
        }
        return password.matches(PASSWORD_PATTERN);
    }

    @GetMapping("/password")
    public ResponseEntity<String> getValidPassWord(@RequestParam String password)
    {
        if(password==null)
        {
            return ResponseEntity.status(HttpStatus.OK).body("Mật khẩu không được trống");
        }
        if(!isValidPassword(password))
            return ResponseEntity.status(HttpStatus.OK).body("Mật khẩu phải có ít nhất 6 kí tự gồm chữ, số và chữ hoa");
        return ResponseEntity.status(HttpStatus.OK).body("Hợp lệ");
    }



    public boolean isValidPayeeAddress(String payeeAddress) {
        // Kiểm tra xem chuỗi có ít nhất 6 ký tự và chứa ít nhất một chữ hoa, một chữ thường,
        // một ký tự đặc biệt và một số
        return payeeAddress != null && payeeAddress.length() >= 6
                && payeeAddress.matches(".*[A-Z].*")
                && payeeAddress.matches(".*[a-z].*")
                && payeeAddress.matches(".*[0-9].*")
                && payeeAddress.matches(".*[!@#$%^&*()].*"); // Thêm các ký tự đặc biệt bạn muốn cho phép
    }
}
