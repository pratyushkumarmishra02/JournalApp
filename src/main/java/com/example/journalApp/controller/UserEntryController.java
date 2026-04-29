package com.example.journalApp.controller;

import com.example.journalApp.entity.User;
import com.example.journalApp.repository.UserEntryRepository;
import com.example.journalApp.service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserEntryController {

    @Autowired
    private UserEntryService userEntryService;
    @Autowired
    private UserEntryRepository  userEntryRepository;

    /*@GetMapping
    public ResponseEntity<?> getAll(){
        try{
            List<User> users = userEntryService.getAll();
            return new ResponseEntity<>(users, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/



    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User myUser){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User userInDB = userEntryService.findUserByUsername(username);
        try{
            if(userInDB != null){
                userInDB.setUsername(myUser.getUsername());
                userInDB.setPassword(myUser.getPassword());
                userEntryService.saveNewUser(userInDB);
                return new ResponseEntity<>(userInDB, HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestBody User myUser){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userEntryRepository.deleteByUsername(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
