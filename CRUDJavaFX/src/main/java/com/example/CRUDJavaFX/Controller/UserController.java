package com.example.CRUDJavaFX.Controller;


import com.example.CRUDJavaFX.Repo.UserRepo;
import com.example.CRUDJavaFX.models.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
public class UserController {

    private final UserRepo userRepo;

    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
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


}
