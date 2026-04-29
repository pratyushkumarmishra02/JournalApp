package com.example.journalApp.controller;

import com.example.journalApp.entity.User;
import com.example.journalApp.service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserEntryService userEntryService;

    @GetMapping("/all-users")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User>  all = userEntryService.getAll();
        if(all!=null &&  !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-admin-user")
    public ResponseEntity<?> createAdmin(@RequestBody User newUser){
        userEntryService.saveAdmin(newUser);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
