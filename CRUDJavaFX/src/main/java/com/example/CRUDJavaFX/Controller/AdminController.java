package com.example.CRUDJavaFX.Controller;

import com.example.CRUDJavaFX.Repo.AdminRepo;
import com.example.CRUDJavaFX.models.Admin;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AdminController {

    final AdminRepo adminRepo;

    public AdminController(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }

    @GetMapping("/admin")
    public HttpStatus Authentical(
            @RequestParam String userName,
            @RequestParam String passWord)
    {
        Optional<Admin> admin=adminRepo.findByUserNameAndPassWord(userName, passWord);
        if(admin.isPresent())
        {
            return HttpStatus.OK;
        }
        return HttpStatus.NOT_FOUND;
    }
}
